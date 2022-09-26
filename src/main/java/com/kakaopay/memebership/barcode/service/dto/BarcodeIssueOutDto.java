package com.kakaopay.memebership.barcode.service.dto;

import com.kakaopay.memebership.barcode.repository.entity.Barcode;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarcodeIssueOutDto {
    private Integer barcode;
    private String ownerId;

    public static BarcodeIssueOutDto fromEntity(Barcode entity) {
        return new BarcodeIssueOutDto(entity.getBarcode(), entity.getOwnerId());
    }
}
