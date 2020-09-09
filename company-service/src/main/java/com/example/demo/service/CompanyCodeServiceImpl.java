package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CompanyCode;
import com.example.demo.entity.CompanyCodeId;

@Service
public class CompanyCodeServiceImpl implements CompanyCodeService{

	@Override
	public Iterable<CompanyCode> getAllCompanyCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CompanyCode> findCompanyCodeById(CompanyCodeId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CompanyCode> getAllCompanyCodesByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CompanyCode> getAllCompanyCodesByStockExchangeId(Long stockExchangeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCompanyCode(CompanyCodeId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompanyCode addCompanyCode(CompanyCode companyCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
