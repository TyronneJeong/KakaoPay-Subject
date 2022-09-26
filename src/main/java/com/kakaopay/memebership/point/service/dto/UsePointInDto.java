package com.kakaopay.memebership.point.service.dto;

import com.kakaopay.memebership.point.controller.dto.EarnPointReqDto;
import com.kakaopay.memebership.point.controller.dto.UsePointReqDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointInDto {
    private Integer barcode;
    private Number usePoint;
    private Integer storeId;
    private String userId;

    public static UsePointInDto fromReqDto(UsePointReqDto reqDto) {
        return UsePointInDto.builder()
                .barcode(reqDto.getBarcode())
                .usePoint(reqDto.getUsePoint())
                .storeId(reqDto.getStoreId())
                .userId(reqDto.getUserId())
                .build();
    }
}
