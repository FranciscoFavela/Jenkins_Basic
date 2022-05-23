package com.example.basic.Controllers;

import com.example.basic.Entities.Users;
import com.example.basic.Services.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
public class UsersControllerImpl extends BaseControllerImpl<Users, UsersServiceImpl> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerImpl.class);
    @GetMapping("/getdto")
    public CompletableFuture<ResponseEntity> getTestAllDto(){
        return servicio.getAllDTO().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/getdto/{id}")
    public CompletableFuture<ResponseEntity> getOneAsyncDto(@PathVariable Long id) throws Exception {
        return servicio.getOneDTO(id).thenApply(ResponseEntity::ok);
    }
}
