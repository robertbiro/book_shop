package com.nye.myWay.repositories;

import com.nye.myWay.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
   //chatGPT
    /*
    many-to-one miatt kell itt JOIN
    * JOIN c.books (b): Ez a JOIN művelet azt jelenti, hogy a Cart entitás (c) és a Book entitások (b)
    * közötti kapcsolatot hozzuk létre.
    * A c.books a Cart entitás books listáját jelöli, melyben minden egyes elem egy Book entitás.
    * Szükség van JOIN-re, hogy elérjük a több kapcsolódó entitást.
    */

    @Query("SELECT c FROM Cart c WHERE c.applicationUser.id = ?1")
    Cart findCartByUserId(Long userId);

}
