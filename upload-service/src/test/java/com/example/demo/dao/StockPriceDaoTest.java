package com.example.demo.dao;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.StockPrice;

@DataJpaTest
public class StockPriceDaoTest {
	@Autowired
	private StockPriceDao stockPriceDao;
	
	@Test
	public void testFindAll() {
		List<StockPrice> stocks = stockPriceDao.findAll();
		assertEquals(4, stocks.size());
	}
	
	@Test
	public void testFindById() {
		StockPrice stock = stockPriceDao.findById((long) 1001).get();
		assertEquals("BSE", stock.getStockExchange());
	}

}
