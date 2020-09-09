package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.models.UserDto;
import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserResponseModel;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.example.demo.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	//private AuthenticationManager authenticationManager;
    private UserService userService;
    private Environment environment;
    
    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
    public JWTAuthenticationFilter(UserService userService, Environment environment, AuthenticationManager authenticationManager) {
        //this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserRequestLoginModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserRequestLoginModel.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String userName = ((User) auth.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserProfileByEmail(userName);
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        UserResponseModel userDetails = modelMapper.map(userService.getUserProfileByEmail(userName), UserResponseModel.class);

//        System.out.println("#####");
//        System.out.println(userName);
//        System.out.println("#####");

        String token = Jwts.builder()
                .setSubject(userDetails.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

//        System.out.println("#####");
//        System.out.println(token);
//        System.out.println("#####");
//        res.addHeader("myToken", TOKEN_PREFIX + token);
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//        res.addHeader("userId", String.valueOf(userDetails.getId()));
//        res.addHeader("usertype", String.valueOf(userDetails.getUserType()));
        
        res.addHeader("token", token);
        res.addHeader("userName", userDetails.getUserName());
    }

}
