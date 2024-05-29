package com.nye.myWay.services;

import com.nye.myWay.dto.BookDTO;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.stereotype.Service;


public interface BookService {

    BookDTO saveGoal(BookDTO  bookDTO, Authentication authentication);

}
