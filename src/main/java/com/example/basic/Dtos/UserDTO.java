package com.example.basic.Dtos;

import com.example.basic.Entities.Base;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
@Data
public class UserDTO extends Base implements Serializable {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private int password;
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "updatedate")
    private String updatedate;
}
