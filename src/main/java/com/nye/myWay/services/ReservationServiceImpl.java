package com.nye.myWay.services;

import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.projections.ReservedBookProjection;
import com.nye.myWay.dto.reservedBookDTO.ReservedBookDTO;
import com.nye.myWay.entities.ApplicationUser;
import com.nye.myWay.entities.Book;
import com.nye.myWay.entities.Reservation;
import com.nye.myWay.exception.*;
import com.nye.myWay.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

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
    public BookResponseUserDTO createReservation(Long bookId, Principal principal) throws BookNotFoundException, UserNotFoundException, AvailableBookInStockException, DuplicateReservationException {
        Book reservedBook = bookService.getBook(bookId);
        ApplicationUser applicationuser = userService.getUserByPrincipal(principal);
        if (reservedBook.getQuantity() == 0) {

            Reservation reservation = new Reservation();
            reservation.setApplicationUser(applicationuser);
            reservation.setCreatedAt(LocalDateTime.now());
            reservation.setBook(reservedBook);

            List<Reservation> existingReservations = new ArrayList<>(reservationRepository.findAll());
            for (Reservation item : existingReservations) {
                if (item.equals(reservation)) {
                    System.out.println(item.getId());
                    throw new DuplicateReservationException();
                }
            }
            reservationRepository.save(reservation);
            BookResponseUserDTO bookResponseUserDTO = modelMapper.map(reservedBook, BookResponseUserDTO.class);
            return bookResponseUserDTO;
            }
            throw new AvailableBookInStockException();
    }

    @Override
    public List<ReservedBookDTO> getAllReservedBooksByUser(Principal principal) throws UserNotFoundException {
        ApplicationUser applicationUser = userService.getUserByPrincipal(principal);
        Long userId = applicationUser.getId();
        List<ReservedBookProjection> reservedBookProjectionList = reservationRepository.getAllReservedBooksByUser(userId);
        List<ReservedBookDTO> reservedBookDTOList = new ArrayList<>();
        for (ReservedBookProjection projection : reservedBookProjectionList) {
            ReservedBookDTO reservedBookDTO = new ReservedBookDTO();
            //because projection return Book (getBook()) it must convert DTO:
            reservedBookDTO.setBookResponseUserDTO(modelMapper.map(projection.getBook(), BookResponseUserDTO.class));
            reservedBookDTO.setCreatedAt(projection.getCreatedAt());
            reservedBookDTOList.add(reservedBookDTO);
        }
        return reservedBookDTOList;
    }

    @Override
    // TODO: 2024. 07. 09. : if user buy a reserved book, it must be deleted from reservation list
    public BookResponseUserDTO deleteOneReservation(Long reservationId, Principal principal) throws UserNotFoundException, ReservedBookNotFoundException {
        ApplicationUser applicationUser = userService.getUserByPrincipal(principal);
        Reservation reservation = reservationRepository.findReservedBookById(reservationId).orElseThrow(ReservedBookNotFoundException::new);

        if (!reservation.getApplicationUser().getId().equals(applicationUser.getId())) {
            throw new ReservedBookNotFoundException();
        }
        BookResponseUserDTO reservedBook = modelMapper.map(reservation, BookResponseUserDTO.class);
        reservationRepository.delete(reservation);
        return reservedBook;
    }
}
