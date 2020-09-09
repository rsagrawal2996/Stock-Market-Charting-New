package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

import static com.example.demo.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	Environment environment;
	
	public JWTAuthorizationFilter(AuthenticationManager authManager, Environment environment) {
        super(authManager);
        this.environment = environment;
    }
	
	@Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        //String header = req.getHeader(HEADER_STRING);
		//String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));
		String authorizationHeader = req.getHeader(HEADER_STRING);

//        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//            chain.doFilter(req, res);
//            return;
//        }
		
//		if (authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
//            chain.doFilter(req, res);
//            return;
//        }
		
		if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		
        //String token = request.getHeader(HEADER_STRING).replace("Bearer ", "");
		//String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));
		String authorizationHeader = req.getHeader(HEADER_STRING);
		if (authorizationHeader == null) {
            return null;
        }
        //System.out.println(token);
		//String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "");
		String token = authorizationHeader.replace(TOKEN_PREFIX, "");
//        if (token != null) {
//            // parse the token
//            String user = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//            System.out.println("####");
//            System.out.println(user);
//            System.out.println("####");
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//            }
//            return null;
//        }
//        return null;
		
//		String userId = Jwts.parser()
//                .setSigningKey(environment.getProperty("token.secret"))
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
		
		String userId = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (userId == null) {
            return null;
        }
  
        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }

}
