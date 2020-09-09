package com.example.demo.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.StockPriceDao;
import com.example.demo.entity.StockPrice;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {
	
	@InjectMocks
	private UploadServiceImpl uploadServiceImpl;
	
	@Mock
	private StockPriceDao stockPriceDao;
	
	@Test
	public void retrieveAll_basic() {
		when(stockPriceDao.findAll())
		.thenReturn(Arrays.asList(new StockPrice((long)10005, "500012", "BSE", 100.0, LocalDate.now(), "10:45:56"), 
				new StockPrice((long)10006, "500012", "BSE", 100.1, LocalDate.now(), "10:45:58")));
		
		List<StockPrice> stocks = uploadServiceImpl.retrieveAll();
		
		assertEquals(100.0, stocks.get(0).getPricePerShare());
		assertEquals(100.1, stocks.get(1).getPricePerShare());
		
	}

}
