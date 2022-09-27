package com.kakaopay.membership.service.dto;

import com.kakaopay.membership.entity.Barcode;
import lombok.*;

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
