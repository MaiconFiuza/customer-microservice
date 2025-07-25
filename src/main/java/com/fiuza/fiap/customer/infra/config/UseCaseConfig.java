package com.fiuza.fiap.customer.infra.config;

import com.fiuza.fiap.customer.core.gateway.UserGateway;
import com.fiuza.fiap.customer.core.usecases.CreateUserUseCase;
import com.fiuza.fiap.customer.core.usecases.GetUserByCpfUseCase;
import com.fiuza.fiap.customer.core.usecases.GetUserByIdUseCase;
import com.fiuza.fiap.customer.infra.adapter.UserRepositoryImp;
import com.fiuza.fiap.customer.infra.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public UserGateway userGateway(UserRepository userRepository) {
        return new UserRepositoryImp(userRepository);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    public GetUserByCpfUseCase getUserByCpfUseCase(UserGateway userGateway) {return new GetUserByCpfUseCase(userGateway);}

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(UserGateway userGateway) {return new GetUserByIdUseCase(userGateway);}

}
