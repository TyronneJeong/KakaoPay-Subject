package com.kakaopay.membership.point.service.dto;

import com.kakaopay.membership.point.controller.dto.UsePointReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointInDto {
    private String barcode;
    private Integer storeId;
    private Integer userId;
    private Integer usePoint;

    public static UsePointInDto fromReqDto(UsePointReqDto reqDto) {
        return UsePointInDto.builder()
                .barcode(reqDto.getBarcode())
                .usePoint(reqDto.getUsePoint())
                .storeId(reqDto.getStoreId())
                .userId(reqDto.getUserId())
                .build();
    }
}
