package com.brunas.springcache.infraestructure.entity.converter;

import com.brunas.springcache.domain.model.user.User;
import com.brunas.springcache.infraestructure.entity.UserEntity;

import java.util.List;

public interface UserEntityConverter {

    UserEntity converterParaEntity(User user);

    User converterParaUser(UserEntity userEntity);

    List<User> converterParaListaUsers(List<UserEntity> usersEntity);
}
