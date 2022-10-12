package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.exception.BookStoreException;
import com.example.book.model.BookData;
import com.example.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookData insert(BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
        log.info("Book record inserted successfully");
        return bookRepository.save(bookData);
    }
    @Override
    public List<BookData> getAllBooks() {
        List<BookData> listOfBooks = bookRepository.findAll();
        return listOfBooks;
    }

    @Override
    public Optional<BookData> getBooksById(int id) {
        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            return bookData;
        } else {
            throw new BookStoreException("Exception With id" + id + "Does Not Exist!!");
        }
    }


    @Override
    public List<BookData> getBookByName(String bookName) {
        List<BookData> findBook = bookRepository.findBookByName(bookName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(" Details For Provided Book Is Not Found");
        }
        return findBook;
    }

    @Override
    public List<BookData> getBookByAuthorName(String authorName) {
        List<BookData> findBook = bookRepository.findBookByAuthorName(authorName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(" Book Author Name Is Not Found");
        }
        return findBook;
    }


    @Override
    public BookData updateBooksById(int id, BookDTO bookDTO) {

        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            BookData updateData = new BookData(id, bookDTO);
            bookRepository.save(updateData);
            return updateData;
        } else {
            throw new BookStoreException("BookData Record Does Not Found");
        }
    }

    @Override
    public BookData updateBooksByQuantity(Integer id, int quantity) {
        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            bookData.get().setQuantity(quantity);
            bookRepository.save(bookData.get());
            return bookData.get();
        } else {
            throw new BookStoreException("BookData Record Does Not Found");
        }
    }

    @Override
    public List<BookData> sortBookDataAsc() {
        return bookRepository.sortBookDataAsc();
    }

    @Override
    public List<BookData> sortBookDataDesc() {
        return bookRepository.sortBookDataDesc();
    }

    @Override
    public BookData deleteBookData(int id) {
        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            bookRepository.deleteById(id);
            return bookData.get();
        } else {
            throw new BookStoreException("Book Record Does Not Found");
        }
    }
}
