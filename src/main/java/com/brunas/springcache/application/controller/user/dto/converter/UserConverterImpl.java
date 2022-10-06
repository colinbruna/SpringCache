package com.brunas.springcache.application.controller.user.dto.converter;

import com.brunas.springcache.application.controller.user.dto.UserRequest;
import com.brunas.springcache.application.controller.user.dto.UserResponse;
import com.brunas.springcache.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterImpl implements UserConverter{

    private final ModelMapper mapper;

    public UserConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User converterParaUser(UserRequest userRequest) {
        return userRequest == null?
                null:
                mapper.map(userRequest, User.class);
    }

    @Override
    public UserResponse converterParaResponse(User user) {
        return user == null?
                null:
                mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> converterParaListaResponse(List<User> users) {
        List<UserResponse> usersResponse = new ArrayList<>();

        for (User user : users) {
            usersResponse.add(converterParaResponse(user));
        }

        return usersResponse;
    }
}
