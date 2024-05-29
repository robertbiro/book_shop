package com.nye.myWay.repositories;

import com.nye.myWay.entities.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderedBook, Long> {
}
