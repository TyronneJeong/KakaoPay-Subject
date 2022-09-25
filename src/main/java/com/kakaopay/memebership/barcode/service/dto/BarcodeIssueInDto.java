package com.kakaopay.memebership.barcode.service.dto;

import com.kakaopay.memebership.barcode.controller.dto.BarcodeIssueReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueInDto {
    private String userId;

    public static BarcodeIssueInDto fromReqDto(BarcodeIssueReqDto reqDto) {
        return new BarcodeIssueInDto(reqDto.getUserId());
    }
}
