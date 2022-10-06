package com.brunas.springcache.domain.service.user;

import com.brunas.springcache.domain.model.user.User;
import com.brunas.springcache.application.controller.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserAdapter adapter;

    public UserServiceImpl(final UserAdapter adapter) {
        this.adapter = adapter;
    }

    public User salvar(User user) {
        return adapter.salvar(user);
    }

    public User alterar(Long id, User user) {
        return adapter.alterar(id, user);
    }

    public User buscar(Long id) {
        return adapter.buscar(id);
    }

    public void exluir(Long id) {
        adapter.excluir(id);
    }

    @Override
    public List<User> listar() {
        return adapter.listar();
    }

}
