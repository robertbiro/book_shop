package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseAdminDTO;
import com.nye.myWay.entities.Book;
import com.nye.myWay.exception.BookNotFoundException;
import org.springframework.data.domain.Page;


public interface BookService {

    //CRUD - admin
    BookResponseAdminDTO addBook(BookResponseAdminDTO bookResponseAdminDTO);
    BookResponseAdminDTO updateBook(Long bookId, BookResponseAdminDTO bookResponseAdminDTO) throws BookNotFoundException;
    BookResponseAdminDTO deleteBook(Long bookId) throws BookNotFoundException;

    //---------------------------------------------------------------------------------------
    //pagination - permitAll
    Page<BookResponseAdminDTO> getFilteredBook(Integer page, Integer size, String direction, String orderBy) throws BookNotFoundException;

    //---------------------------------------------------------------------------------------
    //Operation with Cart - user
    int getReachableIssue(Long bookId) throws BookNotFoundException;
    boolean isBookAvailable(Long bookId, int quantity) throws BookNotFoundException;
    Book getBook(Long bookId) throws BookNotFoundException;

}
