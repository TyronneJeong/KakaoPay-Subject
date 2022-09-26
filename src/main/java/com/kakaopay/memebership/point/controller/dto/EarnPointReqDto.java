package com.kakaopay.memebership.point.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointReqDto {
    private Integer barcode;
    private Integer gainPoint;
    private String storeId;
    private String userId;
}
