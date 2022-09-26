package com.kakaopay.membership.barcode.service.dto;

import com.kakaopay.membership.barcode.entity.Barcode;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarcodeIssueOutDto {
    private String barcode;
    private long ownerId;
    public static BarcodeIssueOutDto fromEntity(Barcode entity) {
        return new BarcodeIssueOutDto(entity.getBarcode(), entity.getOwnerId());
    }
}
