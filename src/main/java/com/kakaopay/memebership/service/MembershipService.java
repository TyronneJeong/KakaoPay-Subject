package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MembershipService {

    public String issueBarcode(String userId) {
        return null;
    }

    public Boolean validateBarcode(Member member) {
        return null;
    }

    public Boolean shareBarcode(Member member) {
        return null;
    }
}
