package com.brunas.springcache.infraestructure.adapter;

import com.brunas.springcache.domain.model.user.User;
import com.brunas.springcache.domain.service.user.UserAdapter;
import com.brunas.springcache.infraestructure.entity.UserEntity;
import com.brunas.springcache.infraestructure.entity.converter.UserEntityConverter;
import com.brunas.springcache.infraestructure.repository.user.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdapterGatewayImpl implements UserAdapter {

    private final UserRepository repository;
    private final UserEntityConverter converter;

    public UserAdapterGatewayImpl(UserRepository repository, UserEntityConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @CacheEvict(value = "listar", allEntries = true)
    public User salvar(User user) {
        UserEntity userEntity = converter.converterParaEntity(user);
        UserEntity userEntitySalvo = repository.save(userEntity);
        return converter.converterParaUser(userEntitySalvo);
    }

    @CacheEvict("listar")
    @CachePut(value = "buscar", key = "#id")
    public User alterar(Long id, User user) {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = converter.converterParaEntity(user);
            userEntity.setId(optionalUserEntity.get().getId());
            return converter.converterParaUser(repository.save(userEntity));
        }

        return null;
    }

    @Cacheable(value = "buscar")
    public User buscar(Long id) {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);

        return optionalUserEntity.map(converter::converterParaUser).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(value = "buscar", allEntries = true),
            @CacheEvict(value = "listar", allEntries = true)
    })
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "listar")
    public List<User> listar() {
        List<UserEntity> usersEntity = repository.findAll();
        return converter.converterParaListaUsers(usersEntity);
    }
}
