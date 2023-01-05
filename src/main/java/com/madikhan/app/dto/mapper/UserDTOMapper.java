package com.madikhan.app.dto.mapper;

import com.madikhan.app.dto.UserDTO;
import com.madikhan.app.model.User;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDTOMapper {

    private ModelMapper modelMapper;

    @Autowired
    public UserDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
