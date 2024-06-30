package com.nye.myWay.repositories;

import com.nye.myWay.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT SUM(ci.quantity) FROM CartItem ci WHERE ci.bookId = ?1")
    Integer countBooksInCartsWithSameBookId(Long bookId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.bookId = ?2")
    Optional<CartItem> findItemByCartIdAndBookId(Long cartId, Long bookId);

    @Query("SELECT ci.id FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.bookId = ?2")
    Long findItemIdByCartIdAndBookId(Long cartId, Long bookId);
    @Query("SELECT ci.bookId FROM CartItem ci JOIN ci.cart c WHERE c.applicationUser.id = ?1")
    List<Long> findAllBookIdByUserId(Long userId);
}
