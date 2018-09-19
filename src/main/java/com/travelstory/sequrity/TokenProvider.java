package com.travelstory.sequrity;

import com.travelstory.dto.LoginDTO;

import javax.annotation.PostConstruct;
import java.util.Base64;

public class TokenProvider {

    private String secretKey;

    private long validity;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(LoginDTO loginDTO) {

        return null;
    }
}
