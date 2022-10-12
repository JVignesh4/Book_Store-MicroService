package com.example.book.service;


import com.example.book.dto.BookDTO;
import com.example.book.model.BookData;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    BookData insert(BookDTO bookDTO);

    List<BookData> getAllBooks();

    Optional<BookData> getBooksById(int id);

    List<BookData> getBookByName(String bookName);

    List<BookData> getBookByAuthorName(String authorName);

    BookData updateBooksById(int id, BookDTO bookDTO);

    BookData updateBooksByQuantity(Integer id, int quantity);

    List<BookData> sortBookDataAsc();

    List<BookData> sortBookDataDesc();

    BookData deleteBookData(int id);
}
