package com.travelstory.services;

import com.travelstory.entity.User;
import com.travelstory.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SendMailService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Value("${spring.mail.username}")
    private String senderEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    public void messageSender(User user, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);
        String randomPass = RandomStringUtils.randomAlphanumeric(10);
        String encodedRandomPass = passwordEncoder.encode(randomPass);
        user.setPassword(encodedRandomPass);
        user = userRepository.saveAndFlush(user);
        messageSender(user, "TravelStory password recovery", "Your new password is: " + randomPass);
    }
}
