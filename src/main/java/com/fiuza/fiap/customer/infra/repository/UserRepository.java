package com.fiuza.fiap.customer.infra.repository;

import com.fiuza.fiap.customer.infra.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findUserByCpf(String cpf);
}
