package com.kakaopay.membership.controller.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryReqDto {
    private String barcode;
    private LocalDate fromDate;
    private LocalDate toDate;
}
