package com.example.demo.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestLoginModel {
	
    
    private String email;
    
    private String password;   

}
