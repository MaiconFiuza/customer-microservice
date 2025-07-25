package com.fiuza.fiap.customer.infra.mappers;

import com.fiuza.fiap.customer.core.entities.user.User;
import com.fiuza.fiap.customer.infra.model.UserModel;

public class UserMapper {
    public static UserModel userToUserModel(User user) {
        return new UserModel(
              user.getId(),
              user.getCpf(),
              user.getName(),
              user.getBirthdate(),
              user.getLogin(),
              user.getPassword(),
              user.getAddress()
        );
    }

    public static User userModelToUser(UserModel userModel) {
        return new User(
                userModel.getId(),
                userModel.getCpf(),
                userModel.getName(),
                userModel.getBirthdate(),
                userModel.getLogin(),
                userModel.getPassword(),
                userModel.getAddress()
        );
    }
}
