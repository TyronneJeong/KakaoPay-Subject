package com.kakaopay.membership.history.service.dto;

import com.kakaopay.membership.history.controller.dto.HistoryReqDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class HistoryInDto {
    private String barcode;
    private LocalDate fromDate;
    private LocalDate toDate;

    public static HistoryInDto fromReqDto(HistoryReqDto inDto) {
        return HistoryInDto.builder()
                .barcode(inDto.getBarcode())
                .fromDate(inDto.getFromDate())
                .toDate(inDto.getToDate())
                .build();
    }
}
