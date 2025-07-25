package com.fiuza.fiap.customer.application.handlers;

import com.fiuza.fiap.customer.core.dto.errors.AlreadyExistExceptionDto;
import com.fiuza.fiap.customer.core.dto.errors.InternalServerErrorDto;
import com.fiuza.fiap.customer.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.fiap.customer.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.customer.core.exceptions.InternalServerError;
import com.fiuza.fiap.customer.core.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDto> handlerNotFoundException(
            NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value())
                .body(new NotFoundExceptionDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<InternalServerErrorDto> handlerInternalServerErrorException(
            InternalServerError exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value())
                .body(new InternalServerErrorDto(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<AlreadyExistExceptionDto> handlerBadRequestException(
            AlreadyExistException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value())
                .body(new AlreadyExistExceptionDto(exception.getMessage(), status.value()));
    }
}
