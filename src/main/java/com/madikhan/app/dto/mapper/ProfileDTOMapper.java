package com.madikhan.app.dto.mapper;

import com.madikhan.app.dto.ProfileDTO;
import com.madikhan.app.model.Profile;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProfileDTOMapper {

    private ModelMapper modelMapper;

    @Autowired
    public ProfileDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProfileDTO profileToDTO(Profile profile) {
        return modelMapper.map(profile, ProfileDTO.class);
    }

    public Profile dtoToProfile(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, Profile.class);
    }

}
