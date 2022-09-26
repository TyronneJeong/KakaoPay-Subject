package com.kakaopay.membership.global.util;

import org.springframework.stereotype.Component;

@Component
public class BarcodeUtils {

    public static String makeBarcode(long seed){
        return "1020304050";
    }

    public static boolean validateBarcode(){
        return true;
    }
}
