package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.Barcode;
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

    public Barcode issueBarcode(Member member) throws Exception {
        return null;
    }

    public Boolean validateBarcode(Member member) throws Exception {
        return null;
    }

    public Boolean shareBarcode(Member member) throws Exception {
        return null;
    }
}
