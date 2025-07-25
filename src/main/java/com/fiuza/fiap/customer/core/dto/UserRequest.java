package com.fiuza.fiap.customer.core.dto;

import java.time.LocalDate;

public record UserRequest(
         String cpf,
         String name,
        LocalDate birthdate,
         String login,
         String password,
         String address
) {
}
