package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.StockPrice;
import com.example.demo.entity.UploadSummary;

public interface UploadService {
	//public List<StockPrice> uploadExcel(MultipartFile file) throws Exception;
	public UploadSummary uploadExcel(MultipartFile file) throws Exception;
	//public UploadSummary showUploadSummary(List<StockPrice> stockPriceList);
	public StockPrice retreiveHardcoded();
	
	public List<StockPrice> retrieveAll();
		
	
	
}
