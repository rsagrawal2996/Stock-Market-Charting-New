package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserService;
import static com.example.demo.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Environment environment;

	
	@Autowired
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
			Environment environment) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.environment = environment;
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
//				.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//				.antMatchers("/h2/**").permitAll()
////	                .antMatchers("/login").permitAll()
//				.anyRequest().authenticated().and().addFilter(getAuthenticationFilter());
////	                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//		// this disables session creation on Spring Security
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.headers().frameOptions().disable();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
		//http.authorizeRequests().antMatchers("/**").hasIpAddress("192.168.0.11")
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
//	private JWTAuthenticationFilter getAuthenticationFilter() throws Exception {
//        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager(), userDetailsService);
//        //authenticationFilter.setAuthenticationManager(authenticationManager());
//        authenticationFilter.setFilterProcessesUrl("/login");
//        return authenticationFilter;
//    }
	
	private JWTAuthenticationFilter getAuthenticationFilter() throws Exception
	{
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(userDetailsService, environment, authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager()); 
		//authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		authenticationFilter.setFilterProcessesUrl(LOGIN_URL);
		return authenticationFilter;
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
