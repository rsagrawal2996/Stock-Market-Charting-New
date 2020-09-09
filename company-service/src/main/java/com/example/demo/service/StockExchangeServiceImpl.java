package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
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
import com.example.demo.models.CompanyResponseModel;
import com.example.demo.models.StockExchangeRequestModel;
import com.example.demo.models.StockExchangeResponseModel;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{
	
	private CompanyDao companyDao;
	private CompanyCodeDao companyCodeDao;
	private StockExchangeDao stockExchangeDao;
	private ModelMapper modelMapper;
	
	

	public StockExchangeServiceImpl(CompanyDao companyDao, CompanyCodeDao companyCodeDao,
			StockExchangeDao stockExchangeDao, ModelMapper modelMapper) {
		super();
		this.companyDao = companyDao;
		this.companyCodeDao = companyCodeDao;
		this.stockExchangeDao = stockExchangeDao;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public List<StockExchangeResponseModel> getAllStockExchanges() {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockExchangeResponseModel>>() {}.getType();
		return modelMapper.map(stockExchangeDao.findAll(), listType);
		
	}

	@Override
	@Transactional
	public StockExchangeResponseModel findStockExchangeById(Long stockExchangeId) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchange stockExchange = stockExchangeDao.findById(stockExchangeId).orElse(null);
		if(stockExchange!=null) {
			return modelMapper.map(stockExchange, StockExchangeResponseModel.class);
		}	
		
		return null;
	}

	@Override
	@Transactional
	public StockExchangeResponseModel findStockExchangeByStockExchangeName(String stockExchangeName) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchange stockExchange = stockExchangeDao.findByStockExchangeName(stockExchangeName).orElse(null);
		if(stockExchange!=null) {
			return modelMapper.map(stockExchange, StockExchangeResponseModel.class);
		}
		return null;
	}

	@Override
	@Transactional
	public StockExchangeResponseModel addStockExchange(StockExchangeRequestModel stockExchangeRequest) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		StockExchange stockExchange = stockExchangeDao.findByStockExchangeName(stockExchangeRequest.getStockExchangeName()).orElse(null);
		if(stockExchange!=null) {
			return modelMapper.map(stockExchange, StockExchangeResponseModel.class);
		}
		stockExchange = new StockExchange();
		stockExchange.setBrief(stockExchangeRequest.getBrief());
		stockExchange.setContactAddress(stockExchangeRequest.getContactAddress());
		stockExchange.setStockExchangeName(stockExchangeRequest.getStockExchangeName());
		stockExchange.setRemarks(stockExchangeRequest.getRemarks());		
		stockExchange = stockExchangeDao.save(stockExchange);
		
		return modelMapper.map(stockExchange, StockExchangeResponseModel.class);
	}

}
