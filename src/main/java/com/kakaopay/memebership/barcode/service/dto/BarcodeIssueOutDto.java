package com.kakaopay.memebership.barcode.service.dto;

import com.kakaopay.memebership.barcode.repository.entity.BarcodeEntity;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarcodeIssueOutDto {
    private Integer barcode;
    private String ownerId;

    public static BarcodeIssueOutDto fromEntity(BarcodeEntity entity) {
        return new BarcodeIssueOutDto(entity.getBarcode(), entity.getOwnerId());
    }
}
