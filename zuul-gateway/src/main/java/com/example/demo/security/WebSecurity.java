package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.http.ActuatorMediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.example.demo.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	private final Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
        //.antMatchers(environment.getProperty("api.users.actuator.url.path")).permitAll()
        .antMatchers(API_ACTUATOR_URL).permitAll()
        .antMatchers().permitAll()
        //.antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
        .antMatchers(ZUUL_ACTUATOR_URL).permitAll()
        //.antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
        .antMatchers(H2_URL).permitAll()
        //.antMatchers(HttpMethod.POST, environment.getProperty("api.registration.url.path")).permitAll()
        .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
        //.antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
        .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                //.antMatchers("/users-ws/users/register").permitAll()                
                //.antMatchers("/users-ws/login").permitAll()
                //.antMatchers(environment.getProperty("api.h2console.url" + ".path")).permitAll()
//                .antMatchers("/users-ws/users/test").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), environment));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

}
