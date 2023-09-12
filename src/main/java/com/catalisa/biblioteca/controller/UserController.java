package com.catalisa.biblioteca.controller;

import com.catalisa.biblioteca.model.UserModel;
import com.catalisa.biblioteca.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id){
        Optional<UserModel> user = userService.buscarPorId(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        return ResponseEntity.ok(user.get());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrarUser(@RequestBody UserModel userModel){
        UserModel user = userService.cadastrarUser(userModel);
        return ResponseEntity.ok().body("Novo usuário cadastrado com sucesso! \n" + user);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserModel> alterarUser (@PathVariable Long id, @RequestBody UserModel userModel){
        UserModel user = userService.alterarUser(id,userModel);
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deletarUser(@PathVariable Long id){
        userService.deletarUser(id);
    }
}
