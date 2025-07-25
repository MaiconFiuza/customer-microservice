package com.fiuza.fiap.customer.core.dto.errors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlreadyExistExceptionDtoTest {

    @Test
    void shouldCreateAlreadyExistExceptionDtoCorrectly() {
        // Given
        String message = "Usuário já cadastrado";
        int status = 400;

        // When
        AlreadyExistExceptionDto dto = new AlreadyExistExceptionDto(message, status);

        // Then
        assertEquals(message, dto.message());
        assertEquals(status, dto.status());
    }

    @Test
    void shouldCompareTwoEqualDtos() {
        AlreadyExistExceptionDto dto1 = new AlreadyExistExceptionDto("Já existe", 400);
        AlreadyExistExceptionDto dto2 = new AlreadyExistExceptionDto("Já existe", 400);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void shouldNotEqualDifferentDtos() {
        AlreadyExistExceptionDto dto1 = new AlreadyExistExceptionDto("Duplicado", 400);
        AlreadyExistExceptionDto dto2 = new AlreadyExistExceptionDto("Outro erro", 409);

        assertNotEquals(dto1, dto2);
    }
}
