package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Company;
import com.example.demo.models.CompanyRequestModel;
import com.example.demo.models.CompanyResponseModel;



public interface CompanyService {
	public List<CompanyResponseModel> getAllCompanies();
	public CompanyResponseModel findCompanyById(Long companyId);
	public CompanyResponseModel findCompanyByCompanyName(String companyName);
	public String deleteCompanyById(Long companyId);
	//public void deleteById(String customerId);
	public CompanyResponseModel addCompany(CompanyRequestModel companyRequest);
	public List<CompanyResponseModel> findCompanyByCompanyNameContaining(String name);
	public CompanyResponseModel updateCompany(Long companyId, CompanyRequestModel companyRequest);
	public List<CompanyResponseModel> findCompanyBySector(String sector);
	public CompanyResponseModel findCompanyByCompanyCode(String companyCodeValue);
	public List<CompanyResponseModel> findCompanyByStockExchangeName(String stockExchangeName);

}
