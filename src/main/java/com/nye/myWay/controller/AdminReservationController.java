package com.nye.myWay.controller;

import com.nye.myWay.dto.reservedBookDTO.AdminInformationReservedBookDataDTO;
import com.nye.myWay.exception.AuthorNotFoundException;
import com.nye.myWay.exception.MyWayException;
import com.nye.myWay.services.AdminReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/reservation")
@CrossOrigin("*")
public class AdminReservationController {

    @Autowired
    private AdminReservationService adminReservationService;

    @GetMapping("/getByAuthor")
    public ResponseEntity<?> getAllReservedBookByAuthor(@RequestParam String author)  {
        try {
            Map<String, List<AdminInformationReservedBookDataDTO>> userAndBookMap = adminReservationService.getReservationsByAuthor(author);
            return ResponseEntity.ok(userAndBookMap);
        } catch (MyWayException myWayException) {
            return ResponseEntity.status(myWayException.getStatus()).body(myWayException.getMessage());
        }
    }
}
