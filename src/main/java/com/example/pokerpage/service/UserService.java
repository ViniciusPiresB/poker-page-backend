package com.example.pokerpage.service;

import com.example.pokerpage.dto.user.UserCreateDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.dto.user.UserUpdateDTO;
import com.example.pokerpage.enums.ErrorEnum;
import com.example.pokerpage.exception.ValidationException;
import com.example.pokerpage.models.User;
import com.example.pokerpage.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserDTO create(UserCreateDTO userCreateDTO){
        User user = objectMapper.convertValue(userCreateDTO, User.class);
        User createdUser = userRepository.save(user);
        return objectMapper.convertValue(createdUser, UserDTO.class);
    }

    public List<UserDTO> list(){
        return this.userRepository.findAll().stream().map(user -> objectMapper.convertValue(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO get(String nome){
        User user = this.getUser(nome);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public UserDTO update(UserUpdateDTO userUpdateDTO){
        User user = this.getUser(userUpdateDTO.getNome());

        user.setNome(userUpdateDTO.getNewNome());

        User updatedUser =  this.userRepository.save(user);

        return this.objectMapper.convertValue(updatedUser, UserDTO.class);
    }

    public void delete(String nome){
        User userToBeDeleted = this.getUser(nome);

        this.userRepository.delete(userToBeDeleted);
    }

    private User getUser(String nome){
        User user = this.userRepository.findByNome(nome);

        if(user == null) throw new ValidationException("User not found", ErrorEnum.NOT_FOUND);

        return user;
    }
}
