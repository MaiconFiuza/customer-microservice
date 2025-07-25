package com.fiuza.fiap.customer.core.usecases;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.core.exceptions.AlreadyExistException;
import com.fiuza.fiap.customer.core.exceptions.InternalServerError;
import com.fiuza.fiap.customer.core.gateway.UserGateway;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(User user) {
        try{
            var hasUser = userGateway.hasUser(user.getCpf());

            if(hasUser) {
                throw new AlreadyExistException("Cpf já se encontra em uso");
            }

            User savedUser = userGateway.createUser(user);
            return savedUser;

        } catch (AlreadyExistException e) {
            throw new AlreadyExistException(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
