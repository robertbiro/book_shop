package com.nye.myWay.controller;

import com.nye.myWay.dto.cartDTOs.CartResponseUserOneBookDTO;
import com.nye.myWay.dto.cartItemDTOs.BookResponseUserDTO;
import com.nye.myWay.dto.reservedBookDTO.ReservedBookDTO;
import com.nye.myWay.dto.reservedBookDTO.ReservedBookResponseUserAllBookDTO;
import com.nye.myWay.exception.AvailableBookInStockException;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.exception.UserNotFoundException;
import com.nye.myWay.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/reservation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addBookToReservation(@PathVariable("id") Long bookId, Principal principal) throws UserNotFoundException, AvailableBookInStockException, BookNotFoundException {
        try {
            BookResponseUserDTO bookResponseUserDTO = reservationService.createReservation(bookId, principal);
            CartResponseUserOneBookDTO cartResponseUserOneBookDTO = new CartResponseUserOneBookDTO("Book is reserved successfully", bookResponseUserDTO);
            return ResponseEntity.ok(cartResponseUserOneBookDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
    @GetMapping("/getAllReservedBook")
    public ResponseEntity<?> getAllReservedBook(Principal principal) {
        try {
            List<ReservedBookDTO> reservedBookDTOList = reservationService.getAllReservedBooksByUser(principal);
            //my soution:
            //ReservedBookResponseUserAllBookDTO reservedBookResponseUserAllBookDTO = new ReservedBookResponseUserAllBookDTO<>();
            /*a ReservedBookResponseUserAllBookDTO data mezője egy List<ReservedBookDTO> típusú objektumot tartalmaz.
            Így a fordító ellenőrizheti a típusokat fordítási időben, és biztosíthatja a típusbiztonságot.
            * */
            ReservedBookResponseUserAllBookDTO<List<ReservedBookDTO>> reservedBookResponseUserAllBookDTO = new ReservedBookResponseUserAllBookDTO<>();
            if (reservedBookDTOList.isEmpty()) {
                reservedBookResponseUserAllBookDTO.setMessage("You have not reserved book!");
                reservedBookResponseUserAllBookDTO.setData(null);
            } else {
                reservedBookResponseUserAllBookDTO.setMessage("Your reserved book(s):");
                reservedBookResponseUserAllBookDTO.setData(reservedBookDTOList);
            }
            return ResponseEntity.ok(reservedBookResponseUserAllBookDTO);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
