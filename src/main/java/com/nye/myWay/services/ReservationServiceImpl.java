package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Reservation;
import com.nye.myWay.exception.AvailableBookInStockException;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BookResponseUserDTO createReservation(Long bookId, Principal principal) throws BookNotFoundException, UserNotFoundException, AvailableBookInStockException {
        Book reservedBook = bookService.getBook(bookId);
        ApplicationUser applicationuser = userService.getUserByPrincipal(principal);
        if(reservedBook.getQuantity() == 0) {
            Reservation reservation = new Reservation();
            reservation.setBook(reservedBook);
            reservation.setApplicationUser(applicationuser);
            reservation.setCreatedAt(LocalDateTime.now());
            reservationRepository.save(reservation);
            BookResponseUserDTO bookResponseUserDTO = modelMapper.map(reservedBook, BookResponseUserDTO.class);
            return bookResponseUserDTO;
        } else {
            throw new AvailableBookInStockException();
        }
    }
}
