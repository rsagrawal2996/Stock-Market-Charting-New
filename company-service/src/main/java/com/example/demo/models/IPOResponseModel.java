package com.example.demo.models;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPOResponseModel {
	private Long ipoId;
	
	private String companyName;	
	
	private String stockExchangeName;	
	
	private Double pricePerShare;	
	
	private Long totalShares;	
	
	private LocalDateTime openDatetime;	
	
	private String remarks;

}
