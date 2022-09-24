package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.Barcode;
import com.kakaopay.memebership.dto.Member;
import org.springframework.stereotype.Service;

@Service
public interface BarcodeService {

    /**
     * issueBarcode
     * 바코드를 발급 한다.
     * argument : Member
     * return : Barcode
     */
    public Barcode issueBarcode(Member member) throws Exception;

    /**
     * checkBarcode
     * 바코드의 발급 여부를 확인한다.
     * argument : Barcode
     * return : Boolean
     */
    public Boolean validateBarcode(Member member) throws Exception;

    /**
     * shareBarcode
     * 바코드 사용 가능 유저 를 추가한다.
     * argument : Barcode
     * return : Boolean
     */
    public Boolean shareBarcode(Member member) throws Exception;
}
