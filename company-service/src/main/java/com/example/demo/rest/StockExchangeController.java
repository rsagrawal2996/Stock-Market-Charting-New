package com.example.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CompanyRequestModel;
import com.example.demo.models.CompanyResponseModel;
import com.example.demo.models.StockExchangeRequestModel;
import com.example.demo.models.StockExchangeResponseModel;
import com.example.demo.service.StockExchangeService;

@RestController
@RequestMapping("/stockExchanghes")
public class StockExchangeController {
	private StockExchangeService stockExchangeService;

	public StockExchangeController(StockExchangeService stockExchangeService) {
		super();
		this.stockExchangeService = stockExchangeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StockExchangeResponseModel>> getAllStockExchanges(){
		List<StockExchangeResponseModel> exchanges = stockExchangeService.getAllStockExchanges();
		
		return new ResponseEntity<List<StockExchangeResponseModel>>(exchanges, HttpStatus.OK);
	}
	
	@GetMapping("/id/{stockExchangeId}")
	public ResponseEntity<StockExchangeResponseModel> findStockExchangeById(@PathVariable("stockExchangeId") Long stockExchangeId){
		StockExchangeResponseModel exchangeResponse = stockExchangeService.findStockExchangeById(stockExchangeId);
		if(exchangeResponse!=null) {
			return new ResponseEntity<StockExchangeResponseModel>(exchangeResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<StockExchangeResponseModel>(exchangeResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/stockExchangeName/{stockExchangeName}")
	public ResponseEntity<StockExchangeResponseModel> findStockExchangeByName(@PathVariable("stockExchangeName") String stockExchangeName){
		StockExchangeResponseModel stockExchangeResponse = stockExchangeService.findStockExchangeByStockExchangeName(stockExchangeName);
		if(stockExchangeResponse!=null) {
			return new ResponseEntity<StockExchangeResponseModel>(stockExchangeResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<StockExchangeResponseModel>(stockExchangeResponse, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<StockExchangeResponseModel> addStockExchange(@RequestBody StockExchangeRequestModel stockExchangeRequestModel){
		StockExchangeResponseModel stockExchangeResponse = stockExchangeService.addStockExchange(stockExchangeRequestModel);
		if(stockExchangeResponse!=null) {
			return new ResponseEntity<StockExchangeResponseModel>(stockExchangeResponse, HttpStatus.CREATED);
		}
		return new ResponseEntity<StockExchangeResponseModel>(stockExchangeResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	

}
