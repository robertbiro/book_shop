package com.nye.myWay.repositories;

import com.nye.myWay.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //@Query("Select cart FROM Cart cart JOIN cart.books WHERE books.id = ?1" )
    //my solution: @Query("Select cart FROM Cart cart JOIN Book book WHERE book.id = ?1") -> it doesn't contain the connection between the Cart and Book: one-to-many
    //List<Cart> findByBooksId(Long bookId);
}
