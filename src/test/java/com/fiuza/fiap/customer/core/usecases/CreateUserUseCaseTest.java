package com.fiuza.fiap.customer.core.usecases;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.customer.core.exceptions.InternalServerError;
import com.fiuza.fiap.customer.core.gateway.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private UserGateway userGateway;
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        createUserUseCase = new CreateUserUseCase(userGateway);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        // Given
        User user = new User("12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "1234", "Rua A");
        User savedUser = new User(UUID.randomUUID(), user.getCpf(), user.getName(), user.getBirthdate(),
                user.getLogin(), user.getPassword(), user.getAddress());

        when(userGateway.hasUser("12345678900")).thenReturn(false);
        when(userGateway.createUser(user)).thenReturn(savedUser);

        // When
        User result = createUserUseCase.execute(user);

        // Then
        assertNotNull(result);
        assertEquals(savedUser.getCpf(), result.getCpf());
        verify(userGateway).hasUser("12345678900");
        verify(userGateway).createUser(user);
    }

    @Test
    void shouldThrowAlreadyExistExceptionWhenCpfExists() {
        User user = new User("12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "1234", "Rua A");

        when(userGateway.hasUser("12345678900")).thenReturn(true);

        AlreadyExistException exception = assertThrows(AlreadyExistException.class, () -> createUserUseCase.execute(user));
        assertEquals("Cpf já se encontra em uso", exception.getMessage());
        verify(userGateway).hasUser("12345678900");
        verify(userGateway, never()).createUser(any());
    }

    @Test
    void shouldThrowInternalServerErrorWhenUnexpectedExceptionOccurs() {
        User user = new User("12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "1234", "Rua A");

        when(userGateway.hasUser("12345678900")).thenThrow(new RuntimeException("Erro no banco"));

        InternalServerError exception = assertThrows(InternalServerError.class, () -> createUserUseCase.execute(user));
        assertEquals("Erro no banco", exception.getMessage());
        verify(userGateway).hasUser("12345678900");
        verify(userGateway, never()).createUser(any());
    }
}
