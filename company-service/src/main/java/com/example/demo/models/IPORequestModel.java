package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPORequestModel {
	private String companyName;	
	
	private String stockExchangeName;	
	
	private Double pricePerShare;	
	
	private Long totalShares;	
	
	private LocalDateTime openDatetime;	
	
	private String remarks;

}
