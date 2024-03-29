package org.cn.kaito.auth.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cn.kaito.auth.DTO.UserDTO;
import org.cn.kaito.auth.Dao.Entity.UserEntity;

@Data

public class UserLoginResponse {
    private String token;
    private UserDTO user;
    public UserLoginResponse(String token,UserDTO userDTO) {
        this.token = token;
        user = userDTO;
    }

}
