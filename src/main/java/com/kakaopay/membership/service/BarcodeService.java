package com.kakaopay.membership.service;

import com.kakaopay.membership.service.dto.BarcodeIssueInDto;
import com.kakaopay.membership.service.dto.BarcodeIssueOutDto;

public interface BarcodeService {
    BarcodeIssueOutDto registerBarcode(BarcodeIssueInDto inDto);
}
