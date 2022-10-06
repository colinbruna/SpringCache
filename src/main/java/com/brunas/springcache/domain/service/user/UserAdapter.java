package com.brunas.springcache.domain.service.user;

import com.brunas.springcache.domain.model.user.User;

import java.util.List;

public interface UserAdapter {

    User salvar(User user);

    User alterar(Long id, User user);

    User buscar(Long id);

    void excluir(Long id);

    List<User> listar();
}
