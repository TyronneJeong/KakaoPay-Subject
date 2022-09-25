package com.kakaopay.memebership.barcode.controller.dto;

import com.kakaopay.memebership.barcode.service.dto.BarcodeIssueOutDto;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueResDto {
    private Integer barcode;
    private String ownderId;

    public static BarcodeIssueResDto fromOutDto(BarcodeIssueOutDto inDto) {
        return BarcodeIssueResDto
                .builder()
                .barcode(inDto.getBarcode())
                .ownderId(inDto.getOwnerId())
                .build();
    }
}
