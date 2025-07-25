package com.fiuza.fiap.customer.application;

import com.fiuza.fiap.customer.application.handlers.ControllerExceptionHandler;
import com.fiuza.fiap.customer.core.dto.errors.AlreadyExistExceptionDto;
import com.fiuza.fiap.customer.core.dto.errors.InternalServerErrorDto;
import com.fiuza.fiap.customer.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.fiap.customer.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.customer.core.exceptions.InternalServerError;
import com.fiuza.fiap.customer.core.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void shouldHandleNotFoundException() {
        NotFoundException ex = new NotFoundException("Usuário não encontrado");

        ResponseEntity<NotFoundExceptionDto> response = handler.handlerNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado", response.getBody().message());
        assertEquals(404, response.getBody().status());
    }

    @Test
    void shouldHandleInternalServerErrorException() {
        InternalServerError ex = new InternalServerError("Erro interno inesperado");

        ResponseEntity<InternalServerErrorDto> response = handler.handlerInternalServerErrorException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno inesperado", response.getBody().message());
        assertEquals(500, response.getBody().status());
    }

    @Test
    void shouldHandleAlreadyExistException() {
        AlreadyExistException ex = new AlreadyExistException("CPF já está em uso");

        ResponseEntity<AlreadyExistExceptionDto> response = handler.handlerBadRequestException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CPF já está em uso", response.getBody().message());
        assertEquals(400, response.getBody().status());
    }
}
