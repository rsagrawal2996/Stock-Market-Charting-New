package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;

@Repository
public interface UserDao extends CrudRepository<Users, Long>{
	public Users findByUserNameAndPassword(String userName, String password);
	public Users findByUserName(String userName);
	public Users findByEmail(String email);
	public Users findByEmailAndPassword(String email, String password);

}
