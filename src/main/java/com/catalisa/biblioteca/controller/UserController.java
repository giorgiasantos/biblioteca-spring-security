package com.catalisa.biblioteca.controller;

import com.catalisa.biblioteca.model.UserModel;
import com.catalisa.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users")

public class UserController {

    @Autowired
    UserService userService;
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public List<UserModel> listarTodosOsUsers(){
        return userService.buscarTodos();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "/{id}")
    public Optional<UserModel> buscarUsuarioPorId(@PathVariable Long id){
        return userService.buscarPorId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel cadastrarUser(@RequestBody UserModel userModel){
        return userService.cadastrarUser(userModel);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "/{id}")
    public UserModel alterarUser (@PathVariable Long id, @RequestBody UserModel userModel){
        return userService.alterarUser(id,userModel);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deletarUser(@PathVariable Long id){
        userService.deletarUser(id);
    }
}
