package com.nye.myWay.services;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.exception.BookNotFoundException;


public interface BookService {

    BookDTO addBook(BookDTO  bookDTO);
    BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookNotFoundException;
    BookDTO deleteBook(Long bookId) throws BookNotFoundException;

}
