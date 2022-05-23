package com.example.basic.Services;

import com.example.basic.Dtos.UserDTO;
import com.example.basic.Entities.Users;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UsersService extends BaseService <Users,Long> {
    CompletableFuture<List<UserDTO>> getAllDTO();
    CompletableFuture<UserDTO> getOneDTO(Long id);
}