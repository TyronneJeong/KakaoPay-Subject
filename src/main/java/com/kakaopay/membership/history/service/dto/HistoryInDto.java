package com.kakaopay.membership.history.service.dto;

import com.kakaopay.membership.history.controller.dto.HistoryReqDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class HistoryInDto {
    private String barcode;
    private Timestamp fromDate;
    private Timestamp toDate;

    public static HistoryInDto fromReqDto(HistoryReqDto inDto){
        return HistoryInDto.builder()
                .barcode(inDto.getBarcode())
                .fromDate(inDto.getFromDate())
                .toDate(inDto.getToDate())
                .build();
    }
}
