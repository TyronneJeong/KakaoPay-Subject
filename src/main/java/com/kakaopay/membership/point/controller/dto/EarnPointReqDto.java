package com.kakaopay.membership.point.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EarnPointReqDto {
    private String barcode;
    private Integer gainPoint;
    private String storeId;
    private String userId;
}
