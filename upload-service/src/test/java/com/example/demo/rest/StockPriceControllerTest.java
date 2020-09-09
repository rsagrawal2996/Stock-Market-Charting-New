package com.example.demo.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.StockPrice;
import com.example.demo.service.UploadService;

@WebMvcTest(StockPriceController.class)
public class StockPriceControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UploadService uploadService;
	
	@Test
	public void helloWorld_basic() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/upload/hello-world")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World"))
				.andReturn();
		
		assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void dummy_basic() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/upload/dummy")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content()
				.json("{id:10006,companyCode:\"500012\",stockExchange:BSE,pricePerShare:100.1,date:2020-09-07,time:\"10:45:58\"}"))
				.andReturn();
		//(long) 10006, "500012", "BSE", 100.1, LocalDate.now(), "10:45:58"
		
		//assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void getStock_basic() throws Exception{
		when(uploadService.retreiveHardcoded())
		.thenReturn(new StockPrice((long)10001, "500012", "BSE", 100.0, LocalDate.now(), "10:45:56"));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/upload/from-service")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content()
				.json("{id:10001,companyCode:\"500012\",stockExchange:BSE,pricePerShare:100.0,date:2020-09-07,time:\"10:45:56\"}"))
				.andReturn();
		
		//assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void retreiveAll_basic() throws Exception{
		when(uploadService.retrieveAll())
		.thenReturn(Arrays.asList(new StockPrice((long)10005, "500012", "BSE", 100.0, LocalDate.now(), "10:45:56"), 
				new StockPrice((long)10006, "500012", "BSE", 100.1, LocalDate.now(), "10:45:58")));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/upload/all-from-database")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content()
				.json("[{id:10005,companyCode:\"500012\",stockExchange:BSE,pricePerShare:100.0,date:2020-09-07,time:\"10:45:56\"},"
						+ "{id:10006,companyCode:\"500012\",stockExchange:BSE,pricePerShare:100.1,date:2020-09-07,time:\"10:45:58\"}]"))
				.andReturn();
		
		//assertEquals("Hello World", result.getResponse().getContentAsString());
	}

}
