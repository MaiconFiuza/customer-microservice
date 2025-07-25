package com.fiuza.fiap.customer.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.fiap.customer.application.controllers.UserController;

import com.fiuza.fiap.customer.core.dto.UserRequest;
import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.usecases.CreateUserUseCase;
import com.fiuza.fiap.customer.core.usecases.GetUserByIdUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private GetUserByIdUseCase getUserByIdUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUser() throws Exception {
        // Given
        UserRequest request = new UserRequest("12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "senha123", "Rua A");

        User response = new User(UUID.randomUUID(), request.cpf(), request.name(),
                request.birthdate(), request.login(), request.password(), request.address());

        Mockito.when(createUserUseCase.execute(any(User.class))).thenReturn(response);

        // When & Then
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.name").value("João Silva"))
                .andExpect(jsonPath("$.login").value("joao"));
    }

    @Test
    void shouldFindUserByCustomerId() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "12345678900", "João Silva",
                LocalDate.of(1990, 1, 1), "joao", "senha123", "Rua A");

        Mockito.when(getUserByIdUseCase.execute(userId)).thenReturn(user);

        // When & Then
        mockMvc.perform(get("/user/{customerId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.name").value("João Silva"));
    }
}
