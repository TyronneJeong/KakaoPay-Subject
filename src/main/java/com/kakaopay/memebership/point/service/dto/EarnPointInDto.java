package com.kakaopay.memebership.point.service.dto;

import com.kakaopay.memebership.barcode.controller.dto.BarcodeIssueReqDto;
import com.kakaopay.memebership.barcode.service.dto.BarcodeIssueInDto;
import com.kakaopay.memebership.point.controller.dto.EarnPointReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointInDto {
    private Integer barcode;
    private Integer gainPoint;
    private String storeId;
    private String userId;

    public static EarnPointInDto fromReqDto(EarnPointReqDto reqDto) {
        return EarnPointInDto.builder()
                .barcode(reqDto.getBarcode())
                .gainPoint(reqDto.getGainPoint())
                .storeId(reqDto.getStoreId())
                .userId(reqDto.getUserId())
                .build();
    }
}
