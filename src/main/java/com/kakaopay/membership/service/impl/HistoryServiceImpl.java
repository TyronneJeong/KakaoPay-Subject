package com.kakaopay.membership.service.impl;

import com.kakaopay.membership.entity.Barcode;
import com.kakaopay.membership.entity.History;
import com.kakaopay.membership.repository.HistoryRepository;
import com.kakaopay.membership.service.HistoryService;
import com.kakaopay.membership.service.dto.HistoryInDto;
import com.kakaopay.membership.service.dto.HistoryOutDto;
import com.kakaopay.membership.util.CachedDataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final CachedDataUtils cachedDataUtils;

    @Override
    public List<HistoryOutDto> getHistory(HistoryInDto inDto) {
        log.debug("▶▶ retriveHistory start >> : " + inDto.toString());
        Optional<Barcode> optionalBarcode = cachedDataUtils.getBarcode(inDto.getBarcode());
        List<History> histories = historyRepository.findAllByBarcodeAndApprovedAtBetweenOrderByApprovedAtDesc(
                inDto.getBarcode(),
                LocalDateTime.of(inDto.getFromDate(), LocalTime.of(0, 0, 1)),
                LocalDateTime.of(inDto.getToDate(), LocalTime.of(23, 59, 59)));
        List<HistoryOutDto> historyOutDtos = new ArrayList<>();
        histories.forEach(e -> historyOutDtos.add(HistoryOutDto.fromEntity(e)));
        return historyOutDtos;
    }
}
