package com.kakaopay.memebership.dto;


import lombok.*;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BarcodeIssueDto {
    private String barcode;
    private String userId;
}
