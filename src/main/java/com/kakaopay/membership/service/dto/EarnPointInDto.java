package com.kakaopay.membership.service.dto;

import com.kakaopay.membership.controller.dto.EarnPointReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointInDto {
    private String barcode;
    private Integer storeId;
    private Integer userId;
    private Integer earnPoint;

    public static EarnPointInDto fromReqDto(EarnPointReqDto reqDto) {
        return EarnPointInDto.builder()
                .barcode(reqDto.getBarcode())
                .earnPoint(reqDto.getEarnPoint())
                .storeId(reqDto.getStoreId())
                .userId(reqDto.getUserId())
                .build();
    }
}
