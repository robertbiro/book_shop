package com.nye.myWay.dto.projections;

import java.time.LocalDateTime;

public interface AdminReservedBookProjection {

    String getUsername();
    String getEmail();
    String getIsbn();
    String getTitle();
    LocalDateTime getCreatedAt();
}
