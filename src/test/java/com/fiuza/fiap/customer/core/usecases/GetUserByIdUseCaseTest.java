package com.fiuza.fiap.customer.core.usecases;


import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.exceptions.NotFoundException;
import com.fiuza.fiap.customer.core.gateway.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUserByIdUseCaseTest {

    private UserGateway userGateway;
    private GetUserByIdUseCase getUserByIdUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        getUserByIdUseCase = new GetUserByIdUseCase(userGateway);
    }

    @Test
    void shouldReturnUserWhenFoundById() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "12345678900", "João Silva", LocalDate.of(1990, 1, 1),
                "joao", "senha123", "Rua A");
        when(userGateway.findUserByUserId(id)).thenReturn(Optional.of(user));

        User result = getUserByIdUseCase.execute(id);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userGateway, times(1)).findUserByUserId(id);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUserNotFound() {
        UUID id = UUID.randomUUID();
        when(userGateway.findUserByUserId(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            getUserByIdUseCase.execute(id);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userGateway, times(1)).findUserByUserId(id);
    }
}
