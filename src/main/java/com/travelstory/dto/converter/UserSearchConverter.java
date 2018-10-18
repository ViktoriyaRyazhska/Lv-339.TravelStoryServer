package com.travelstory.dto.converter;

import com.travelstory.dto.UserSearchDTO;
import com.travelstory.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSearchConverter implements GeneralConverter<UserSearchDTO, User> {

    @Override
    public User convertToEntity(UserSearchDTO dto) {

        return null;
    }

    @Override
    public UserSearchDTO convertToDto(User entity) {
        UserSearchDTO userSearchDTO = new UserSearchDTO();
        userSearchDTO.setId(entity.getId());
        userSearchDTO.setProfilePic(entity.getProfilePic());
        userSearchDTO.setFirstName(entity.getFirstName());
        userSearchDTO.setLastName(entity.getLastName());
        return userSearchDTO;
    }

    @Override
    public List<UserSearchDTO> convertToDto(List<User> entities) {
        List<UserSearchDTO> userSearchDTOList = new ArrayList<>();
        for (User entity : entities) {
            UserSearchDTO userSearchDTO = new UserSearchDTO();
            userSearchDTO.setId(entity.getId());
            userSearchDTO.setProfilePic(entity.getBackgroundPic());
            userSearchDTO.setFirstName(entity.getFirstName());
            userSearchDTO.setLastName(entity.getLastName());
            userSearchDTOList.add(userSearchDTO);
        }
        return userSearchDTOList;
    }
}
