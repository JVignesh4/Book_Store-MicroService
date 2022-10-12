package com.example.order.exception;

import com.example.order.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> errorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
        List<String> errMessage = errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request", errMessage);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> handleCustomException(BookException bookStoreException) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request", bookStoreException.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
