package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.exception.AvailableBookInStockException;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.UserNotFoundException;

import java.security.Principal;

public interface ReservationService {

    BookResponseUserDTO createReservation(Long bookId, Principal principal) throws BookNotFoundException, UserNotFoundException, AvailableBookInStockException;
}
