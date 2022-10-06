package com.brunas.springcache.application.controller.user.dto.converter;

import com.brunas.springcache.application.controller.user.dto.UserRequest;
import com.brunas.springcache.application.controller.user.dto.UserResponse;
import com.brunas.springcache.domain.model.user.User;

import java.util.List;

public interface UserConverter {

    User converterParaUser(UserRequest userRequest);

    UserResponse converterParaResponse(User user);

    List<UserResponse> converterParaListaResponse(List<User> users);

}
