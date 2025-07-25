package com.fiuza.fiap.customer.infra.mappers;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.infra.model.UserModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserMapperTest {
    @Test
    void shouldMapUserToUserModel() {
        // Given
        UUID id = UUID.randomUUID();
        User user = new User(id, "12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "senha123", "Rua A");

        UserModel userModel = UserMapper.userToUserModel(user);

        // Then
        assertNotNull(userModel);
        assertEquals(user.getId(), userModel.getId());
        assertEquals(user.getCpf(), userModel.getCpf());
        assertEquals(user.getName(), userModel.getName());
        assertEquals(user.getBirthdate(), userModel.getBirthdate());
        assertEquals(user.getLogin(), userModel.getLogin());
        assertEquals(user.getPassword(), userModel.getPassword());
        assertEquals(user.getAddress(), userModel.getAddress());
    }

    @Test
    void shouldMapUserModelToUser() {
        // Given
        UUID id = UUID.randomUUID();
        UserModel userModel = new UserModel(id, "98765432100", "Maria Souza",
                LocalDate.of(1985, 5, 20), "maria", "senha456", "Rua B");

        // When
        User user = UserMapper.userModelToUser(userModel);

        // Then
        assertNotNull(user);
        assertEquals(userModel.getId(), user.getId());
        assertEquals(userModel.getCpf(), user.getCpf());
        assertEquals(userModel.getName(), user.getName());
        assertEquals(userModel.getBirthdate(), user.getBirthdate());
        assertEquals(userModel.getLogin(), user.getLogin());
        assertEquals(userModel.getPassword(), user.getPassword());
        assertEquals(userModel.getAddress(), user.getAddress());
    }
}
