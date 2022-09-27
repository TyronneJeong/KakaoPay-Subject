package com.kakaopay.membership.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NO_REMAINED_POINT(HttpStatus.BAD_REQUEST, "No remained the point."),
    NOT_REGISTERED_STORE_ID(HttpStatus.BAD_REQUEST, "Not registered store id. Please contact administrator."),
    NOT_REGISTERED_BARCODE_NUMBER(HttpStatus.BAD_REQUEST, "Not registered barcode number. Please contact administrator."),
    USER_INFO_DOES_NOT_EXISTS(HttpStatus.BAD_REQUEST, "User info does not exists. Please contact administrator."),
    NOT_ALLOWED_USER_ID(HttpStatus.BAD_REQUEST, "Not allowed user id. Please check your account."),
    ;
    private HttpStatus status;
    private String message;
}
