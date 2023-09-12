package com.catalisa.biblioteca.service;

import com.catalisa.biblioteca.model.UserModel;
import com.catalisa.biblioteca.model.dtos.UserDTO;
import com.catalisa.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // lista todos os usuários
    public List<UserDTO> buscarTodos(){
        List<UserModel> usuarios = userRepository.findAll();
        List<UserDTO> usuarioDTO = new ArrayList<>();

        for(UserModel usuario: usuarios){
            usuarioDTO.add(new UserDTO(usuario));
        }

        return usuarioDTO;
    }

    // busca um usuário por ID
    public Optional<UserModel> buscarPorId(Long id){
        return userRepository.findById(id);
    }

    // cadastra um novo usuário
    public UserModel cadastrarUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    // altera um usuário
    public UserModel alterarUser(Long id, UserModel userModel){
        UserModel userAlterado = userRepository.findById(id).get();

        if(userAlterado != null){
            userAlterado.setUsername(userModel.getUsername());
        }
        if(userAlterado != null){
            userAlterado.setPassword(userModel.getPassword());
        }
        if(userAlterado != null){
            userAlterado.setRoles(userModel.getRoles());
        }

        return userRepository.save(userAlterado);
    }

    // deleta um usuário
    public void deletarUser(Long id){
        userRepository.deleteById(id);
    }
}
