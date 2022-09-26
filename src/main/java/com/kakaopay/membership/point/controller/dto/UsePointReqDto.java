package com.kakaopay.membership.point.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointReqDto {
    private String barcode;
    private Number usePoint;
    private Integer storeId;
    private String userId;
}
