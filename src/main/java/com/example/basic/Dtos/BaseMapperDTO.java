package com.example.basic.Dtos;

import com.example.basic.Entities.Users;
import org.modelmapper.ModelMapper;

public class BaseMapperDTO {
    private final ModelMapper modelMapper = new ModelMapper();

    private static BaseMapperDTO baseMapperDTO;

    private BaseMapperDTO(){}

    public static BaseMapperDTO singleInstance(){
        if(baseMapperDTO == null){
            baseMapperDTO = new BaseMapperDTO();
        }
        return baseMapperDTO;
            }

    public UserDTO mapToAppoimentDto(Users users){
        return modelMapper.map(users,UserDTO.class);
    }

}
