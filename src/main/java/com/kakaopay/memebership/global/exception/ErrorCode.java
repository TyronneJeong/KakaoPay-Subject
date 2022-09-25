package com.kakaopay.memebership.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    NO_REMAINED_POINT(HttpStatus.BAD_REQUEST, "doesn't remained the point."),
    NOT_FOUND_STORE_NAME(HttpStatus.NOT_FOUND, "doesn't remained the point."),

    ;
    private HttpStatus status;
    private String message;
}