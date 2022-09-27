package com.kakaopay.membership.point.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class EarnPointReqDto {
    private String barcode;
    private Integer earnPoint;
    private Integer storeId;
    private Integer userId;
}
