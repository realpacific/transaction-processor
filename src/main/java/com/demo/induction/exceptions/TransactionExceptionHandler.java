package com.demo.induction.exceptions;

import com.demo.induction.entity.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnsupportedExtensionException.class)
    protected ResponseEntity<Object> handleUnsupportedException(Exception e, WebRequest request) {
        BaseResponse<String> response = new BaseResponse<>(null, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFound.class)
    protected ResponseEntity<Object> handleTransactionNotFound(Exception e, WebRequest request) {
        BaseResponse<String> response = new BaseResponse<>(null, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
