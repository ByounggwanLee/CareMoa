package com.caremoa.member.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
	 private final HttpStatus code;

     public HttpStatus getCode() {
         return code;
     }

     public ApiException(HttpStatus code) {
         this.code = code;
     }

     public ApiException(HttpStatus code, String message) {
         super(message);
         this.code = code;
     }
}
