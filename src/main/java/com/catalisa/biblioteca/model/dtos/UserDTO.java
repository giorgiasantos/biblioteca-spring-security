package com.catalisa.biblioteca.model.dtos;

import com.catalisa.biblioteca.model.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;

    public UserDTO(UserModel userModel) {
        this.id = userModel.getId_user();
        this.username = userModel.getUsername();
    }

    public UserDTO() {
    }
}
