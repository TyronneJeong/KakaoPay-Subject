package com.kakaopay.membership.history.service.dto;

import com.kakaopay.membership.history.entity.History;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class HistoryOutDto {
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

    public static HistoryOutDto fromEntity(History inDto) {
        return HistoryOutDto.builder()
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
