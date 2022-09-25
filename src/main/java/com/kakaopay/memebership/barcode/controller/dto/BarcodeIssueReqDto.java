package com.kakaopay.memebership.barcode.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class BarcodeIssueReqDto {
    private String userId;
}