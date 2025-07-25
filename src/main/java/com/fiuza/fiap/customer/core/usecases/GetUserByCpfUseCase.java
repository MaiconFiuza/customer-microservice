package com.fiuza.fiap.customer.core.usecases;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.exceptions.NotFoundException;
import com.fiuza.fiap.customer.core.gateway.UserGateway;

public class GetUserByCpfUseCase {
    private final UserGateway userGateway;

    public GetUserByCpfUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(String cpf) {
       return userGateway.findUserByCpf(cpf).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }
}
