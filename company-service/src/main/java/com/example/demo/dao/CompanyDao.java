package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long>{
	public Optional<Company> findByCompanyName(String companyName);
	public List<Company> findByCompanyNameContainingIgnoreCase(String name);
	public List<Company> findAllBySector(String sector);
	//public Optional<Company> findByCompanyCodesContaining(String companyCode);
	

}
