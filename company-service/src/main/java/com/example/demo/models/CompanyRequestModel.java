package com.example.demo.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestModel {	
	
	
	private String companyName;
	
	
	private Double turnover;
	
	
	private String ceo;
	
	
	
	private String directors;
	
	
	private String sector;
	
	
	private String writeup;
	
	private List<CompanyCodeModelForCompany> companyCodes;
	
//	private List<IPORequestModel> ipos; 
	

}
