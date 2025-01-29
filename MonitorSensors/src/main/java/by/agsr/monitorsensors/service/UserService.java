package by.agsr.monitorsensors.service;

import by.agsr.monitorsensors.dto.UserCreateDto;
import by.agsr.monitorsensors.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findById(Integer id);

    UserDto create(UserCreateDto userCreateDto);

    UserDto update(Integer id, UserCreateDto userCreateDto);

    void delete(Integer id);
}
