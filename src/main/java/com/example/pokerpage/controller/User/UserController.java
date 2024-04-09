package com.example.pokerpage.controller.User;

import com.example.pokerpage.dto.user.UserCreateDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.dto.user.UserUpdateDTO;
import com.example.pokerpage.models.User;
import com.example.pokerpage.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController implements IUserController {
    public final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>(this.userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> list(){
        return new ResponseEntity<>(this.userService.list(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDTO> get(@PathVariable String name){
        return new ResponseEntity<>(this.userService.get(name), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<>(this.userService.update(userUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> delete(@PathVariable String nome){
        this.userService.delete(nome);
        return ResponseEntity.ok().build();
    }
}
