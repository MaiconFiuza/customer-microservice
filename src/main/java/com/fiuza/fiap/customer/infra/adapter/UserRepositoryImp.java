package com.fiuza.fiap.customer.infra.adapter;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.gateway.UserGateway;
import com.fiuza.fiap.customer.infra.mappers.UserMapper;
import com.fiuza.fiap.customer.infra.model.UserModel;
import com.fiuza.fiap.customer.infra.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImp implements UserGateway {

    private final UserRepository userRepository;

    public UserRepositoryImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UserModel userModel = UserMapper.userToUserModel(user);
        UserModel userSaved = userRepository.save(userModel);
        return UserMapper.userModelToUser(userSaved);
    }

    @Override
    public Optional<User> findUserByCpf(String cpf) {
        Optional<UserModel> userSaved = userRepository.findUserByCpf(cpf);
        return Optional.of(UserMapper.userModelToUser(userSaved.get()));
    }

    @Override
    public Optional<User> findUserByUserId(UUID customerId) {
        Optional<UserModel> userSaved = userRepository.findById(customerId);
        return Optional.of(UserMapper.userModelToUser(userSaved.get()));
    }

    @Override
    public boolean hasUser(String cpf) {
        return userRepository.findUserByCpf(cpf).isPresent();
    }
}
