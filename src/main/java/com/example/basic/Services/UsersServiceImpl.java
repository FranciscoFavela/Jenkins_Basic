package com.example.basic.Services;

import com.example.basic.Dtos.BaseMapperDTO;
import com.example.basic.Dtos.UserDTO;
import com.example.basic.Entities.Users;
import com.example.basic.Repositories.BaseRepository;
import com.example.basic.Repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UsersServiceImpl extends BaseServiceImpl<Users, Long> implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    static BaseMapperDTO baseMapperDTO = BaseMapperDTO.singleInstance();
    public UsersServiceImpl(BaseRepository<Users, Long> baseRepository) {
        super(baseRepository);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);


    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<UserDTO>> getAllDTO() {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get ALL Dto");
        System.out.println(Thread.currentThread().getName());
        List<Users> entities =usersRepository.findAll();
        List<UserDTO> dtos = new ArrayList<>();
            for(Users users: entities){
            UserDTO userDTO;
            userDTO = baseMapperDTO.mapToAppoimentDto(users);
            dtos.add(userDTO);
        }
        return CompletableFuture.completedFuture(dtos);
    }
    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<UserDTO> getOneDTO(Long id) {
        System.out.println("Current Thread" + Thread.currentThread().getName());
        LOGGER.info("Request to Get OneDto");
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(usersRepository.findById(id),UserDTO.class);
        return CompletableFuture.completedFuture(userDTO);
    }
}