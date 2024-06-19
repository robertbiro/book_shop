package com.nye.myWay.services;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.exception.BookNotFoundException;
import org.springframework.data.domain.Page;


public interface BookService {

    //CRUD - admin
    BookDTO addBook(BookDTO  bookDTO);
    BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookNotFoundException;
    BookDTO deleteBook(Long bookId) throws BookNotFoundException;

    //---------------------------------------------------------------------------------------
    //pagination - permitAll
    Page<BookDTO> getFilteredBook(Integer page, Integer size, String direction, String orderBy) throws BookNotFoundException;

    //---------------------------------------------------------------------------------------
    //Operation with Cart - user
    int getReachableIssue(Long bookId) throws BookNotFoundException;
    boolean isBookAvailable(Long bookId, int quantity) throws BookNotFoundException;

}
