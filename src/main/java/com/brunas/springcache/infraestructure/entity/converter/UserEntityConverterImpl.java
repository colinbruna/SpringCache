package com.brunas.springcache.infraestructure.entity.converter;

import com.brunas.springcache.domain.model.user.User;
import com.brunas.springcache.infraestructure.entity.UserEntity;
import com.brunas.springcache.infraestructure.entity.converter.UserEntityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEntityConverterImpl implements UserEntityConverter {

    private final ModelMapper mapper;

    public UserEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserEntity converterParaEntity(User user) {
        return user == null?
                null:
                mapper.map(user, UserEntity.class);
    }

    @Override
    public User converterParaUser(UserEntity userEntity) {
        return userEntity == null?
                null:
                mapper.map(userEntity, User.class);
    }

    @Override
    public List<User> converterParaListaUsers(List<UserEntity> usersEntity) {
        List<User> users = new ArrayList<>();

        for (UserEntity userEntity : usersEntity) {
            users.add(converterParaUser(userEntity));
        }

        return users;
    }
}
