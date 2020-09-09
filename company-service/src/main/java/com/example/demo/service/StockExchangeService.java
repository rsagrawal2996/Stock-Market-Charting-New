package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.StockExchange;
import com.example.demo.models.StockExchangeRequestModel;
import com.example.demo.models.StockExchangeResponseModel;



public interface StockExchangeService {
	public List<StockExchangeResponseModel> getAllStockExchanges();
	public StockExchangeResponseModel findStockExchangeById(Long stockExchangeId);
	public StockExchangeResponseModel findStockExchangeByStockExchangeName(String stockExchangeName);
	//public void deleteCompany(Long companyId);
	//public void deleteById(String customerId);
	public StockExchangeResponseModel addStockExchange(StockExchangeRequestModel stockExchangeRequest);

}
