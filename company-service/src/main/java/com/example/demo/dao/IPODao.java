package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.IPO;

@Repository
public interface IPODao extends JpaRepository<IPO, Long>{
	public List<IPO> findAllByCompanyNameOrderByOpenDatetimeAsc(String companyName);
	public List<IPO> findAllByOrderByOpenDatetimeAsc();
	

}
