package com.brunas.springcache.application.controller.user;

import com.brunas.springcache.application.controller.user.dto.UserRequest;
import com.brunas.springcache.application.controller.user.dto.UserResponse;
import com.brunas.springcache.application.controller.user.dto.converter.UserConverter;
import com.brunas.springcache.domain.model.user.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserConverter converter;

    public UserController(final UserService service, final UserConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<UserResponse> salvar(@RequestBody UserRequest userRequest) {
        User user = converter.converterParaUser(userRequest);
        User userSalvo = service.salvar(user);
        return new ResponseEntity<>(converter.converterParaResponse(userSalvo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> alterar(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = converter.converterParaUser(userRequest);
        User userAlterado = service.alterar(id, user);

        return userAlterado == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(userAlterado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> buscar(@PathVariable Long id) {
        User user = service.buscar(id);

        return user == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> excluir(@PathVariable Long id) {
        if (Objects.isNull(service.buscar(id))) {
            return ResponseEntity.notFound().build();
        }

        service.exluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listar() {
        List<User> users = service.listar();
        return ResponseEntity.ok(converter.converterParaListaResponse(users));
    }
}
