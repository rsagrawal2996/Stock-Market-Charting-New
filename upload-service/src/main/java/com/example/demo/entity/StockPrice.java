package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "company_code", nullable = false)
	private String companyCode;
	
	@Column(name = "stock_exchange")
	private String stockExchange;
	
	@Column(name = "price_per_share")
	private Double pricePerShare;
	
	@Column(name = "date")//, columnDefinition = "TIMESTAMP NULL")
	private LocalDate date;
	
	@Column(name = "time")//, columnDefinition = "TIMESTAMP NULL")
	private String time;	

}
