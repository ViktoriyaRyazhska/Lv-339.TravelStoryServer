package com.travelstory.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.travelstory.dao.UserDAO;
import com.travelstory.dto.LoginDTO;
import com.travelstory.dto.RegistrationDTO;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${CLOUDINARY_URL}")
    private String cloudinaryUrl;

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenProvider tokenProvider;

    @Override
    public void registrateUser(RegistrationDTO registrationDTO) {
        if (userDAO.existsByEmail(registrationDTO.getEmail())) {

        } else {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setFirstName(registrationDTO.getFirstName());
            user.setLastName(registrationDTO.getLastName());
            user.setPassword(registrationDTO.getPassword());
            user.setGender(registrationDTO.getGender());
            user.setUserRole(UserRole.ROLE_USER);
            userDAO.save(user);
        }
    }


    public User updateAvatarUrl(Long userId, User userDetails) throws IOException {
        User user = userDAO.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found",
                "Dear customer, no such user in the database", UserServiceImpl.class));
        String avatarInBase64 = userDetails.getProfilePictureUrl();
        byte[] decodedImg = Base64.getDecoder().decode(avatarInBase64);
        String avatarUrl = uploadImageOnCloud(decodedImg);
        user.setProfilePictureUrl(avatarUrl);
        return userDAO.save(user);
    }

    private String uploadImageOnCloud(byte[] decodedImg) throws IOException {
        Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
        Map params = ObjectUtils.asMap("public_id", "avatars/someimage");
        Map uploadResult = cloudinary.uploader().upload(decodedImg, params);
        return uploadResult.get("url").toString();
    }

    @Override
    public boolean checkCredentials(LoginDTO loginDTO) {
        return userDAO.existsByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public String signIn(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        return tokenProvider.createToken(email, userDAO.findByEmail(email).getUserRole(),
                userDAO.findByEmail(email).getId());
    }
}
