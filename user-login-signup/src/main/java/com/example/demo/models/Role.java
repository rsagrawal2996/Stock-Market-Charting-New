package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    String id;
    String role;
	
	
	
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
