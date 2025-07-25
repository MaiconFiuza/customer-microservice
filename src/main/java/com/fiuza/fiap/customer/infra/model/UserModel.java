package com.fiuza.fiap.customer.infra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String login;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    public UserModel(UUID id, String cpf, String name, LocalDate birthdate, String login, String password, String address) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public UserModel(String cpf, String name, LocalDate birthdate, String login, String password, String address) {
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public UserModel() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
