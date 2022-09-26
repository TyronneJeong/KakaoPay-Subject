package com.kakaopay.membership.barcode.service.dto;

import com.kakaopay.membership.barcode.controller.dto.BarcodeIssueReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueInDto {
    private Integer userId;
    public static BarcodeIssueInDto fromReqDto(BarcodeIssueReqDto reqDto) {
        return new BarcodeIssueInDto(reqDto.getUserId());
    }
}
