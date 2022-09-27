package com.kakaopay.membership.history.service.impl;

import com.kakaopay.membership.history.entity.History;
import com.kakaopay.membership.history.repository.HistoryRepository;
import com.kakaopay.membership.history.service.HistoryService;
import com.kakaopay.membership.history.service.dto.HistoryInDto;
import com.kakaopay.membership.history.service.dto.HistoryOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public List<HistoryOutDto> getHistory(HistoryInDto inDto) {
        log.debug("▶▶ retriveHistory start >> : " + inDto.toString());
        List<History> historyList = historyRepository.findAllByBarcodeCreatedAtBetween(inDto.getBarcode(), inDto.getToDate(), inDto.getToDate());
        List<HistoryOutDto> historyOutDtos = new ArrayList<>();
        for(History vo : historyList){
            historyOutDtos.add(HistoryOutDto.fromEntity(vo));
        }
        return historyOutDtos;
    }
}
