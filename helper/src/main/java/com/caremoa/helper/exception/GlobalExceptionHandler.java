package com.caremoa.helper.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "com.caremoa.helper.controller")
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // RuntimeException 처리
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleCustomException(ApiException e) {
        return handleExceptionInternal(e.getCode(),e.getMessage());
    }

    // IllegalArgumentException 에러 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        return handleExceptionInternal(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // @Valid 어노테이션으로 넘어오는 에러 처리
    @Override
    public ResponseEntity<Object> handleBindException(
            BindException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.warn("handleIllegalArgument", e);
        return handleExceptionInternal(e, HttpStatus.BAD_REQUEST);
    }

    // 대부분의 에러 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        return handleExceptionInternal(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // RuntimeException과 대부분의 에러 처리 메세지를 보내기 위한 메소드
    private ResponseEntity<Object> handleExceptionInternal(HttpStatus errorCode) {
        return ResponseEntity.status(errorCode)
                .body(makeErrorResponse(errorCode));
    }

    // 코드 가독성을 위해 에러 처리 메세지를 만드는 메소드 분리
    private ErrorResponse makeErrorResponse(HttpStatus errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.toString())
                .message(errorCode.getReasonPhrase())
                .build();
    }

    private ResponseEntity<Object> handleExceptionInternal(HttpStatus errorCode, String message) {
        return ResponseEntity.status(errorCode)
                .body(makeErrorResponse(errorCode, message));
    }

    // 코드 가독성을 위해 에러 처리 메세지를 만드는 메소드 분리
    private ErrorResponse makeErrorResponse(HttpStatus errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }

    // @Valid 어노테이션으로 넘어오는 에러 처리 메세지를 보내기 위한 메소드
    private ResponseEntity<Object> handleExceptionInternal(BindException e, HttpStatus errorCode) {
        return ResponseEntity.status(errorCode)
                .body(makeErrorResponse(e, errorCode));
    }

    // 코드 가독성을 위해 에러 처리 메세지를 만드는 메소드 분리
    private ErrorResponse makeErrorResponse(BindException e, HttpStatus errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getReasonPhrase())
                .errors(validationErrorList)
                .build();
    }
}
