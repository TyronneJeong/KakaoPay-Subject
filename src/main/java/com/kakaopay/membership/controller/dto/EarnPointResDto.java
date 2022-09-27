package com.kakaopay.membership.controller.dto;

import com.kakaopay.membership.service.dto.EarnPointOutDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class EarnPointResDto {
    private Integer storeId;
    private String storeName;
    private String barcode;
    private String workTypeCd;
    private LocalDateTime approvedAt;
    private Integer tranUserId;
    private String tranUserName;
    private String inOutDvCd;
    private Integer tranPoint;
    private Integer totalPoint;

    public static EarnPointResDto fromOutDto(EarnPointOutDto inDto) {
        return new EarnPointResDto().builder()
                .storeId(inDto.getStoreId())
                .storeName(inDto.getStoreName())
                .barcode(inDto.getBarcode())
                .workTypeCd(inDto.getWorkTypeCd())
                .approvedAt(inDto.getApprovedAt())
                .tranUserId(inDto.getTranUserId())
                .tranUserName(inDto.getTranUserName())
                .inOutDvCd(inDto.getInOutDvCd())
                .tranPoint(inDto.getTranPoint())
                .totalPoint(inDto.getTotalPoint())
                .build();
    }
}
