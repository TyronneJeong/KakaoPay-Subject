package com.kakaopay.membership.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UsePointReqDto {
    private String barcode;
    private Integer storeId;
    private Integer userId;
    private Integer usePoint;
}
