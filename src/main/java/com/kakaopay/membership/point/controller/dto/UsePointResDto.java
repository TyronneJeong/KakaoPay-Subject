package com.kakaopay.membership.point.controller.dto;

import com.kakaopay.membership.point.service.dto.UsePointOutDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UsePointResDto {
    private Integer storeId;
    private String storeName;
    private String barcode;
    private String workTypeCd;
    private Timestamp tranDate;
    private Integer tranUserId;
    private String tranUserName;
    private String inOutDvCd;
    private Integer tranPoint;
    private Integer totalPoint;

    public static UsePointResDto fromOutDto(UsePointOutDto inDto) {
        return UsePointResDto.builder()
                .storeId(inDto.getStoreId())
                .storeName(inDto.getStoreName())
                .barcode(inDto.getBarcode())
                .workTypeCd(inDto.getWorkTypeCd())
                .tranDate(inDto.getTranDate())
                .tranUserId(inDto.getTranUserId())
                .tranUserName(inDto.getTranUserName())
                .inOutDvCd(inDto.getInOutDvCd())
                .tranPoint(inDto.getTranPoint())
                .totalPoint(inDto.getTotalPoint())
                .build();
    }
}
