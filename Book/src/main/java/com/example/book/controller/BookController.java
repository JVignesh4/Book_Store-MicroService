package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.dto.ResponseDTO;
import com.example.book.model.BookData;
import com.example.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Book registered successfully !", bookService.insert(bookdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }


    @GetMapping("/getallbooks")
    public ResponseEntity<ResponseDTO> getAllBookRecords() {
        List<BookData> newBook = bookService.getAllBooks();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @GetMapping("/getbook/{bookName}")
    public ResponseEntity<ResponseDTO> getRecordByBookName(@PathVariable String bookName) {
        List<BookData> newBook = bookService.getBookByName(bookName);
        ResponseDTO dto = new ResponseDTO("Record for particular book retrieved successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @GetMapping("/getbook/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable int id) {
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", bookService.getBooksById(id));
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @PutMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable int id, @Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Record updated successfully !", bookService.updateBooksById(id, bookdto));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteBookRecord(@PathVariable int id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", bookService.deleteBookData(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }


    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> sortRecordAsc() {
        List<BookData> newBook = bookService.sortBookDataAsc();
        ResponseDTO dto = new ResponseDTO("Records for book sorted in ascending order successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> sortRecordDesc() {
        List<BookData> newBook = bookService.sortBookDataDesc();
        ResponseDTO dto = new ResponseDTO("Records for book sorted in descending order successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int id, @RequestParam int quantity) {
        BookData newBook = bookService.updateBooksByQuantity(id, quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/authorName/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable String authorName) {
        List<BookData> bookData = bookService.getBookByAuthorName(authorName);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For AuthorName Successfully", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}