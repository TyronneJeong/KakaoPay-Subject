package com.kakaopay.membership.barcode.controller.dto;

import com.kakaopay.membership.barcode.service.dto.BarcodeIssueOutDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueResDto {
    private String barcode;
    private long ownderId;

    public static BarcodeIssueResDto fromOutDto(BarcodeIssueOutDto inDto) {
        return BarcodeIssueResDto
                .builder()
                .barcode(inDto.getBarcode())
                .ownderId(inDto.getOwnerId())
                .build();
    }
}
