package com.nye.myWay.services;

import com.nye.myWay.dto.reservedBookDTO.AdminInformationReservedBookDataDTO;
import com.nye.myWay.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Map;

public interface AdminReservationService {

    Map<String, List<AdminInformationReservedBookDataDTO>> getReservationsByAuthor(String author) throws AuthorNotFoundException;
}
