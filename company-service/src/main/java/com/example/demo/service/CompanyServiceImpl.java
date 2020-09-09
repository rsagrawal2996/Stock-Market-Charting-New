package com.example.demo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CompanyCodeDao;
import com.example.demo.dao.CompanyDao;
import com.example.demo.dao.StockExchangeDao;
import com.example.demo.entity.Company;
import com.example.demo.entity.CompanyCode;
import com.example.demo.entity.CompanyCodeId;
import com.example.demo.entity.StockExchange;
import com.example.demo.models.CompanyCodeModelForCompany;
import com.example.demo.models.CompanyRequestModel;
import com.example.demo.models.CompanyResponseModel;

@Service
public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao;
	private CompanyCodeDao companyCodeDao;
	private StockExchangeDao stockExchangeDao;
	private ModelMapper modelMapper;
	
	

	public CompanyServiceImpl(CompanyDao companyDao, CompanyCodeDao companyCodeDao, StockExchangeDao stockExchangeDao, ModelMapper modelMapper) {
		super();
		this.companyDao = companyDao;
		this.companyCodeDao = companyCodeDao;
		this.stockExchangeDao = stockExchangeDao;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public List<CompanyResponseModel> getAllCompanies() {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyResponseModel>>() {}.getType();
		return modelMapper.map(companyDao.findAll(), listType);

	}

	@Override
	@Transactional
	public CompanyResponseModel findCompanyById(Long companyId) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company company = companyDao.findById(companyId).orElse(null);
		if(company!=null) {
			return modelMapper.map(company, CompanyResponseModel.class);
		}	
		
		return null;
	}

	@Override
	@Transactional
	public CompanyResponseModel findCompanyByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company company = companyDao.findByCompanyName(companyName).orElse(null);
		if(company!=null) {
			return modelMapper.map(company, CompanyResponseModel.class);
		}
		return null;
	}

	@Override
	@Transactional
	public String deleteCompanyById(Long companyId) {
		// TODO Auto-generated method stub
		Optional<Company> company = companyDao.findById(companyId);	
		if(company.isPresent()) {
			List<CompanyCode> companyCodes = companyCodeDao.findAllByCompanyId(companyId);
			if(!companyCodes.isEmpty()) {
				companyCodeDao.deleteByCompanyId(companyId);
			}
			companyDao.deleteById(companyId);
			return "Successfully Deleted";		
			
		}	
		return "Not Found";
		
	}

	@Override
	@Transactional
	public CompanyResponseModel addCompany(CompanyRequestModel companyRequest){
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company company = companyDao.findByCompanyName(companyRequest.getCompanyName()).orElse(null);
		if(company!=null) {
			return modelMapper.map(company, CompanyResponseModel.class);
		}
		company = new Company();
		company.setCeo(companyRequest.getCeo());
		company.setCompanyName(companyRequest.getCompanyName());
		company.setWriteup(companyRequest.getWriteup());
		company.setTurnover(companyRequest.getTurnover());
		company.setSector(companyRequest.getSector());
		company.setDirectors(companyRequest.getDirectors());
		company = companyDao.save(company);
		List<CompanyCodeModelForCompany> companyCodes = companyRequest.getCompanyCodes(); 
		for(CompanyCodeModelForCompany companyCode : companyCodes) {
			StockExchange stockExchange = stockExchangeDao.findByStockExchangeName(companyCode.getStockExchangeName()).orElse(null);
			if(stockExchange==null) {
				return null;
			}
			CompanyCodeId id = new CompanyCodeId(company.getCompanyId(), stockExchange.getStockExchangeId());
			CompanyCode companyCode1 = new CompanyCode(id, companyCode.getCompanyCodeValue(), company.getCompanyName(),
					companyCode.getStockExchangeName(), companyCode.getStockPrice());
			companyCodeDao.save(companyCode1);		
			
		}
		//company = companyDao.save(company);
		CompanyResponseModel companyResponse = modelMapper.map(company, CompanyResponseModel.class);
		companyResponse.setCompanyCodes(companyCodes);
		return companyResponse;
		
	}
	

	@Override
	@Transactional
	public List<CompanyResponseModel> findCompanyByCompanyNameContaining(String name) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<Company> companies = companyDao.findByCompanyNameContainingIgnoreCase(name);
		Type listType = new TypeToken<List<CompanyResponseModel>>(){}.getType();		
		return modelMapper.map(companies, listType);
		
	}

	@Override
	@Transactional
	public CompanyResponseModel updateCompany(Long companyId, CompanyRequestModel companyRequest) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Company company = companyDao.findById(companyId).orElse(null);
		if(company==null) {
			return null;
			//return modelMapper.map(companyRequest, CompanyResponseModel.class);
		}		
		company.setCeo(companyRequest.getCeo());
		company.setCompanyName(companyRequest.getCompanyName());
		company.setWriteup(companyRequest.getWriteup());
		company.setTurnover(companyRequest.getTurnover());
		company.setSector(companyRequest.getSector());
		company.setDirectors(companyRequest.getDirectors());
		company = companyDao.save(company);
		companyCodeDao.deleteByCompanyId(companyId);
		List<CompanyCodeModelForCompany> companyCodes = companyRequest.getCompanyCodes(); 
		for(CompanyCodeModelForCompany companyCode : companyCodes) {
			StockExchange stockExchange = stockExchangeDao.findByStockExchangeName(companyCode.getStockExchangeName()).orElse(null);
			if(stockExchange==null) {
				return null;
			}
			CompanyCodeId id = new CompanyCodeId(company.getCompanyId(), stockExchange.getStockExchangeId());
			CompanyCode companyCode1 = new CompanyCode(id, companyCode.getCompanyCodeValue(), company.getCompanyName(),
					companyCode.getStockExchangeName(), companyCode.getStockPrice());
			companyCodeDao.save(companyCode1);		
			
		}
		//return modelMapper.map(company, CompanyResponseModel.class);
		CompanyResponseModel companyResponse = modelMapper.map(company, CompanyResponseModel.class);
		companyResponse.setCompanyCodes(companyCodes);
		return companyResponse;
	}

	@Override
	@Transactional
	public List<CompanyResponseModel> findCompanyBySector(String sector) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyResponseModel>>() {}.getType();
		return modelMapper.map(companyDao.findAllBySector(sector), listType);
	}

	@Override
	@Transactional
	public CompanyResponseModel findCompanyByCompanyCode(String companyCodeValue) {
		// TODO Auto-generated method stub
		CompanyCode companyCode = companyCodeDao.findByCompanyCodeValue(companyCodeValue).orElse(null);
		if(companyCode!=null) {
			Company company = companyDao.findByCompanyName(companyCode.getCompanyName()).orElse(null);
			if(company!=null) {
				return modelMapper.map(company, CompanyResponseModel.class);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public List<CompanyResponseModel> findCompanyByStockExchangeName(String stockExchangeName) {
		// TODO Auto-generated method stub
		List<CompanyCode> companyCodes = companyCodeDao.findAllByStockExchangeNameContaining(stockExchangeName);
		List<Company> companies = new ArrayList<>();
		if(!companyCodes.isEmpty()) {
			for(CompanyCode companyCode : companyCodes) {
				Company company = companyDao.findByCompanyName(companyCode.getCompanyName()).orElse(null);
				if(company!=null) {
					companies.add(company);
				}
			}
			
			Type listType = new TypeToken<List<CompanyResponseModel>>() {}.getType();
			return modelMapper.map(companies, listType);
			
			
		}
		return null;
		
	}
	
	

}
