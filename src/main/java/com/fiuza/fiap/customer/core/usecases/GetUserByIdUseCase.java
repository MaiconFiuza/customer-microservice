package com.fiuza.fiap.customer.core.usecases;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.exceptions.InternalServerError;
import com.fiuza.fiap.customer.core.exceptions.NotFoundException;
import com.fiuza.fiap.customer.core.gateway.UserGateway;

import java.util.UUID;

public class GetUserByIdUseCase {
    private final UserGateway userGateway;

    public GetUserByIdUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(UUID customerId) {
        try {
            return userGateway.findUserByUserId(customerId).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        }catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError("Aconteceu um erro inesperado. Por favor tente novamente");
        }
    }
}
