package com.example.FlightsApplication.handler;

import com.example.FlightsApplication.controller.Controller;
import com.example.FlightsApplication.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {Controller.class})
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDto> handleValidationFailure(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setDescription(ex.getMessage());
        log.info("HandleException {}", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }
}