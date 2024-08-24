package com.elearn.app.services;

import com.elearn.app.dtos.UserDto;

public interface UserService {

    UserDto create(UserDto dto);

    UserDto geById(String userId);
}
