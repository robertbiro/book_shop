package com.nye.myWay.services;

import com.nye.myWay.dto.projections.AdminReservedBookProjection;
import com.nye.myWay.dto.reservedBookDTO.AdminInformationReservedBookDataDTO;
import com.nye.myWay.exception.AuthorNotFoundException;
import com.nye.myWay.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminReservationServiceImpl implements AdminReservationService{

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Map<String, List<AdminInformationReservedBookDataDTO>> getReservationsByAuthor(String author) throws AuthorNotFoundException {
        List<AdminReservedBookProjection> adminReservedBookProjections = reservationRepository.findReservationByAuthor(author);
        if (adminReservedBookProjections.isEmpty()) {
            throw new AuthorNotFoundException();
        } else {
            Map<String, List<AdminInformationReservedBookDataDTO>> userAndBookMap = new HashMap<>();

            for (AdminReservedBookProjection projection : adminReservedBookProjections) {
                String username = projection.getUsername();
                String email = projection.getEmail();
                String key = username + "-" + email;

                AdminInformationReservedBookDataDTO bookDto = new AdminInformationReservedBookDataDTO();
                bookDto.setIsbn(projection.getIsbn());
                bookDto.setTitle(projection.getTitle());
                bookDto.setCreatedAt(projection.getCreatedAt());

                if (!userAndBookMap.containsKey(key)) {
                    List<AdminInformationReservedBookDataDTO> bookList = new ArrayList<>();
                    bookList.add(bookDto);
                    userAndBookMap.put(key, bookList);
                }
                else {
                    userAndBookMap.get(key).add(bookDto);
                }
            }
            return userAndBookMap;
        }
    }
}
