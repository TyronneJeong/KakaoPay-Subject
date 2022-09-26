package com.kakaopay.membership.barcode.controller.dto;

import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueResDto {
    private String barcode;
    private Integer ownderId;

    public static BarcodeIssueResDto fromOutDto(BarcodeIssueOutDto inDto) {
        return BarcodeIssueResDto
                .builder()
                .barcode(inDto.getBarcode())
                .ownderId(inDto.getOwnerId())
                .build();
    }
}
