package com.kakaopay.membership.history.controller.dto;

import com.kakaopay.membership.history.service.dto.HistoryOutDto;
import com.kakaopay.membership.point.controller.dto.UsePointResDto;
import com.kakaopay.membership.point.service.dto.UsePointOutDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class HistoryResDto {
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

    public static HistoryResDto fromOutDto(HistoryOutDto inDto) {
        return HistoryResDto.builder()
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
