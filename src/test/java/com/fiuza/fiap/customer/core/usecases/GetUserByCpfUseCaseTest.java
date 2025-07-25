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

class GetUserByCpfUseCaseTest {

    private UserGateway userGateway;
    private GetUserByCpfUseCase getUserByCpfUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        getUserByCpfUseCase = new GetUserByCpfUseCase(userGateway);
    }

    @Test
    void shouldReturnUserWhenFoundByCpf() {
        // Given
        String cpf = "12345678900";
        User user = new User(UUID.randomUUID(), cpf, "Maria Silva",
                LocalDate.of(1995, 5, 20), "maria", "senha123", "Rua B");
        when(userGateway.findUserByCpf(cpf)).thenReturn(Optional.of(user));

        User result = getUserByCpfUseCase.execute(cpf);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userGateway, times(1)).findUserByCpf(cpf);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUserNotFoundByCpf() {
        String cpf = "00000000000";
        when(userGateway.findUserByCpf(cpf)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            getUserByCpfUseCase.execute(cpf);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userGateway, times(1)).findUserByCpf(cpf);
    }
}
