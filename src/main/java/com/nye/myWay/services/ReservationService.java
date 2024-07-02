package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.projections.ReservedBookProjection;
import com.nye.myWay.dto.reservedBookDTO.ReservedBookDTO;
import com.nye.myWay.exception.*;

import java.security.Principal;
import java.util.List;

public interface ReservationService {

    BookResponseUserDTO createReservation(Long bookId, Principal principal) throws BookNotFoundException, UserNotFoundException, AvailableBookInStockException;
    List<ReservedBookDTO> getAllReservedBooksByUser(Principal principal) throws UserNotFoundException;
}
