package com.kakaopay.memebership.global.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class BarcodeUtils {

    public static Integer makeBarcode(String seed){
        return Integer.valueOf(1234567890);
    }

    public static boolean validateBarcode(){
        return true;
    }
}
