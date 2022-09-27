package com.kakaopay.membership.history.controller.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryReqDto {
    private String barcode;
    private Timestamp fromDate;
    private Timestamp toDate;
}
