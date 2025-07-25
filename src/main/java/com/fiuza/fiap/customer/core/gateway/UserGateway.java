package com.fiuza.fiap.customer.core.gateway;

import com.fiuza.fiap.customer.core.entities.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserGateway {

    User createUser(User user);

    Optional<User> findUserByCpf(String cpf);

    Optional<User> findUserByUserId(UUID customerId);

    boolean hasUser(String cpf);
}
