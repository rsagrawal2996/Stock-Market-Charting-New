package com.example.demo.models;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchangeRequestModel {
	
	private String stockExchangeName;

	
	private String brief;

	
	private String contactAddress;

	
	private String remarks;

}
