package com.example.demo.models;

import java.util.List;

import com.example.demo.entity.CompanyCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchangeResponseModel {
	private Long stockExchangeId;
	
	private String stockExchangeName;

	
	private String brief;

	
	private String contactAddress;

	
	private String remarks;
	
	private List<CompanyCodeModelForExchange> companyCodes;

}
