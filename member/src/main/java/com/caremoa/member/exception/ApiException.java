package com.caremoa.member.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
* @packageName    : com.caremoa.member.exception
* @fileName       : ApiException.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : Application 처리중 오류에 대한 Exception Class
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Getter
public class ApiException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
