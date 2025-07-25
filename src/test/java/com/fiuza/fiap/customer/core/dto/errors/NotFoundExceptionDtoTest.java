package com.fiuza.fiap.customer.core.dto.errors;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionDtoTest {

    @Test
    void shouldCreateNotFoundExceptionDtoCorrectly() {
        // Given
        String message = "Usuário não encontrado";
        int status = 404;

        // When
        NotFoundExceptionDto dto = new NotFoundExceptionDto(message, status);

        // Then
        assertEquals(message, dto.message());
        assertEquals(status, dto.status());
    }

    @Test
    void shouldCompareTwoEqualDtos() {
        NotFoundExceptionDto dto1 = new NotFoundExceptionDto("Erro", 404);
        NotFoundExceptionDto dto2 = new NotFoundExceptionDto("Erro", 404);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void shouldNotEqualDifferentDtos() {
        NotFoundExceptionDto dto1 = new NotFoundExceptionDto("Erro A", 404);
        NotFoundExceptionDto dto2 = new NotFoundExceptionDto("Erro B", 500);

        assertNotEquals(dto1, dto2);
    }
}
