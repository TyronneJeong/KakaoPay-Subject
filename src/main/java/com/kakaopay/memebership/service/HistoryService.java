package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.HistoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class HistoryService {

    public List<HistoryDto> getHistory(HistoryDto historyDto){
        // 바코드 번호 확인
        // 입력 기간 정보 확인
        // 입력 기간 거래 내역 조회
        // 리턴
        return null;
    }
}
