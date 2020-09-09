package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCode {
	@EmbeddedId
	CompanyCodeId id;
	
	@ManyToOne(fetch=FetchType.LAZY)//, cascade=CascadeType.ALL)
	@MapsId("company_id")
	@JoinColumn(name = "company_id")
	Company company;
	
	@ManyToOne(fetch=FetchType.LAZY)//, cascade=CascadeType.ALL)
	@MapsId("stock_exchange_id")
	@JoinColumn(name = "stock_exchange_id")
	StockExchange stockExchange;
	
	@Column(name = "company_code_value", unique = true, nullable = false)
	private String companyCodeValue;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	@Column(name = "stock_exchange_name", nullable = false)
	private String stockExchangeName;
	
	@Column(name = "stock_price")
	private Double stockPrice;

	public CompanyCode(CompanyCodeId id, String companyCodeValue, String companyName, String stockExchangeName,
			Double stockPrice) {
		super();
		this.id = id;
		this.companyCodeValue = companyCodeValue;
		this.companyName = companyName;
		this.stockExchangeName = stockExchangeName;
		this.stockPrice = stockPrice;
	}	

}
