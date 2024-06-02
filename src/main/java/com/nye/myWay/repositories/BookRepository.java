package com.nye.myWay.repositories;

import com.nye.myWay.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select book From Book book Where book.isbn = ?1")
    Optional<Book> findByIsbn(String isbn);

}
