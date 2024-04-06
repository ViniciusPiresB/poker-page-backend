package com.example.pokerpage.service;

import com.example.pokerpage.dto.user.UserCreateDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.models.User;
import com.example.pokerpage.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserDTO create(UserCreateDTO userCreateDTO){
        User user = objectMapper.convertValue(userCreateDTO, User.class);
        User createdUser = userRepository.save(user);
        return objectMapper.convertValue(createdUser, UserDTO.class);
    }
}
