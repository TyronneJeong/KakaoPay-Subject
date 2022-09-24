package com.kakaopay.memebership.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PayPointDto {
    private String barcode;
    private String userId;
    private String requestPoint;

}
