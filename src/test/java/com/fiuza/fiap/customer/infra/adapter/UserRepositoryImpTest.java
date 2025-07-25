package com.fiuza.fiap.customer.infra.adapter;


import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.infra.mappers.UserMapper;
import com.fiuza.fiap.customer.infra.model.UserModel;
import com.fiuza.fiap.customer.infra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryImpTest {

    private UserRepository userRepository;
    private UserRepositoryImp userRepositoryImp;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userRepositoryImp = new UserRepositoryImp(userRepository);
    }

    @Test
    void shouldCreateUser() {
        // Given
        User user = new User("12345678900", "João", LocalDate.of(1990, 1, 1),
                "joao", "senha", "rua A");
        UserModel userModel = UserMapper.userToUserModel(user);
        userModel.setId(UUID.randomUUID());

        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        // When
        User result = userRepositoryImp.createUser(user);

        // Then
        assertNotNull(result);
        assertEquals(user.getCpf(), result.getCpf());
        verify(userRepository, times(1)).save(any(UserModel.class));
    }

    @Test
    void shouldFindUserByCpf() {
        // Given
        String cpf = "12345678900";
        User user = new User(UUID.randomUUID(), cpf, "João", LocalDate.of(1990, 1, 1),
                "joao", "senha", "rua A");
        UserModel userModel = UserMapper.userToUserModel(user);

        when(userRepository.findUserByCpf(cpf)).thenReturn(Optional.of(userModel));

        // When
        Optional<User> result = userRepositoryImp.findUserByCpf(cpf);

        // Then
        assertTrue(result.isPresent());
        assertEquals(cpf, result.get().getCpf());
        verify(userRepository).findUserByCpf(cpf);
    }

    @Test
    void shouldFindUserByUserId() {
        // Given
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "12345678900", "João", LocalDate.of(1990, 1, 1),
                "joao", "senha", "rua A");
        UserModel userModel = UserMapper.userToUserModel(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userModel));

        // When
        Optional<User> result = userRepositoryImp.findUserByUserId(userId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldReturnTrueIfUserExistsByCpf() {
        String cpf = "12345678900";
        when(userRepository.findUserByCpf(cpf)).thenReturn(Optional.of(mock(UserModel.class)));

        boolean exists = userRepositoryImp.hasUser(cpf);

        assertTrue(exists);
        verify(userRepository).findUserByCpf(cpf);
    }

    @Test
    void shouldReturnFalseIfUserDoesNotExistByCpf() {
        String cpf = "00000000000";
        when(userRepository.findUserByCpf(cpf)).thenReturn(Optional.empty());

        boolean exists = userRepositoryImp.hasUser(cpf);

        assertFalse(exists);
        verify(userRepository).findUserByCpf(cpf);
    }
}
