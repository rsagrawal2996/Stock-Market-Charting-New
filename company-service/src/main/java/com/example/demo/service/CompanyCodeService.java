package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.CompanyCode;
import com.example.demo.entity.CompanyCodeId;



public interface CompanyCodeService {
	public Iterable<CompanyCode> getAllCompanyCodes();
	public Optional<CompanyCode> findCompanyCodeById(CompanyCodeId id);
	public Iterable<CompanyCode> getAllCompanyCodesByCompanyId(Long companyId);
	public Iterable<CompanyCode> getAllCompanyCodesByStockExchangeId(Long stockExchangeId);
	public void deleteCompanyCode(CompanyCodeId id);
	//public void deleteById(String customerId);
	public CompanyCode addCompanyCode(CompanyCode companyCode);

}
