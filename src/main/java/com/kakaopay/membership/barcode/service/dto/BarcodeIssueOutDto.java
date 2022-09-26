package com.kakaopay.membership.barcode.service.dto;

import com.kakaopay.membership.barcode.entity.Barcode;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarcodeIssueOutDto {
    private String barcode;
    private Integer ownerId;
    public static BarcodeIssueOutDto fromEntity(Barcode entity) {
        return new BarcodeIssueOutDto(entity.getBarcode(), entity.getOwnerId());
    }
}
