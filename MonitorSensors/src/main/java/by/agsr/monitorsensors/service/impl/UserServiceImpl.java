package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.dto.UserCreateDto;
import by.agsr.monitorsensors.dto.UserDto;
import by.agsr.monitorsensors.entity.User;
import by.agsr.monitorsensors.exception.ResourceNotFoundException;
import by.agsr.monitorsensors.mapper.UserMapper;
import by.agsr.monitorsensors.repository.UserRepository;
import by.agsr.monitorsensors.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDto)
                .toList();
    }

    public UserDto findById(Integer id){
        return userRepository.findById(id)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserDto create(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userMapper::mapToEntity)
                .map(userRepository::save)
                .map(userMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public UserDto update(Integer id, UserCreateDto userCreateDto) {
        return userRepository.findById(id)
                .map(user -> userMapper.mapToUpdate(user, userCreateDto))
                .map(userRepository::saveAndFlush)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }
}
