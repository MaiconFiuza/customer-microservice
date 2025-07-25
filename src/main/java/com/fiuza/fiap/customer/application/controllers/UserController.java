package com.fiuza.fiap.customer.application.controllers;

import com.fiuza.fiap.customer.core.dto.UserRequest;
import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.usecases.CreateUserUseCase;
import com.fiuza.fiap.customer.core.usecases.GetUserByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private static  final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(
        CreateUserUseCase createUserUseCase,
        GetUserByIdUseCase getUserByIdUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @Operation(
            description = "Criar um usuário",
            summary = "Endpoint responsável por buscar um usuário",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequest userRequest) {
        logger.info("POST");
        User userInput = new User(userRequest.cpf(),userRequest.name(), userRequest.birthdate(),
                userRequest.login(), userRequest.password(), userRequest.address());
        User user  = createUserUseCase.execute(userInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(
            description = "Busca um usuário",
            summary = "Endpoint responsável por buscar um usuário",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{customerId}")
    public ResponseEntity<User> findByCustomerId(@PathVariable UUID customerId) {
        logger.info("GET /{}", customerId);
        User result = getUserByIdUseCase.execute(customerId);
        return ResponseEntity.ok(result);
    }

}
