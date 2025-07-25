package com.fiuza.fiap.customer.core.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRequestTest {

    @Test
    void shouldCreateUserRequestWithAllFields() {
        // Given
        String cpf = "12345678900";
        String name = "João Silva";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        String login = "joao";
        String password = "senha123";
        String address = "Rua A";

        // When
        UserRequest request = new UserRequest(cpf, name, birthdate, login, password, address);

        // Then
        assertEquals(cpf, request.cpf());
        assertEquals(name, request.name());
        assertEquals(birthdate, request.birthdate());
        assertEquals(login, request.login());
        assertEquals(password, request.password());
        assertEquals(address, request.address());
    }

    @Test
    void shouldCompareTwoEqualUserRequests() {
        UserRequest r1 = new UserRequest("123", "Nome", LocalDate.of(2000, 1, 1),
                "login", "pass", "endereço");
        UserRequest r2 = new UserRequest("123", "Nome", LocalDate.of(2000, 1, 1),
                "login", "pass", "endereço");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
