package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CompanyCode;
import com.example.demo.entity.CompanyCodeId;

@Repository
public interface CompanyCodeDao extends JpaRepository<CompanyCode, CompanyCodeId>{
	@Modifying
	@Transactional
	@Query("delete from CompanyCode s where s.company.id = ?1")
	public void deleteByCompanyId(Long companyId);
	
	@Query("select s from CompanyCode s where s.company.id = ?1")
	public List<CompanyCode> findAllByCompanyId(Long companyId);
	
	@Query("select s from CompanyCode s where s.stockExchange.id = ?1")
	public List<CompanyCode> findAllByStockExchangeId(Long stockExchangeId);
	
	
	@Query("select s from CompanyCode s where s.company.id = ?1 and s.stockExchange.id = ?2")	
	public Optional<CompanyCode> findByCompanyIdAndStockExchangeId(Long companyId, Long stockExchangeId);
	
	@Modifying
	@Transactional
	@Query("delete from CompanyCode s where s.company.id = ?1 and s.stockExchange.id = ?2")
	public void deleteByCompanyIdAndStockExchangeId(Long companyId, Long stockExchangeId);
	
	public Optional<CompanyCode> findByCompanyCodeValue(String companyCodeValue);
	
	public void deleteByCompanyCodeValue(String companyCodeValue);
	
	//@Query("select s from CompanyCode s where s.company.id = ?1")
	public List<CompanyCode> findAllByCompanyNameContaining(String name);
	
	public List<CompanyCode> findAllByStockExchangeNameContaining(String name);

}
