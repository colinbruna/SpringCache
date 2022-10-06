package com.brunas.springcache.application.controller.user;

import com.brunas.springcache.domain.model.user.User;

import java.util.List;

public interface UserService {

    User salvar(User user);

    User alterar(Long id, User user);

    User buscar(Long id);

    void exluir(Long id);

    List<User> listar();
}
