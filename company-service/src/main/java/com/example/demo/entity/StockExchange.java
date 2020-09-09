package com.example.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_exchange_id")
	private Long stockExchangeId;

	@Column(name = "stock_exchange_name", unique = true, nullable = false)
	private String stockExchangeName;

	@Column(name = "brief")
	private String brief;

	@Column(name = "contact_address")
	private String contactAddress;

	@Column(name = "remarks")
	private String remarks;

//	@Column(name = "companies")
//	private List<Company> companies;
	@OneToMany(mappedBy = "stockExchange")//, cascade = CascadeType.ALL)
	private List<CompanyCode> companyCodes;// = new HashSet<>();

	public StockExchange(String stockExchangeName, String brief, String contactAddress, String remarks) {
		super();
		this.stockExchangeName = stockExchangeName;
		this.brief = brief;
		this.contactAddress = contactAddress;
		this.remarks = remarks;
	}

	public StockExchange(Long stockExchangeId, String stockExchangeName, String brief, String contactAddress,
			String remarks) {
		super();
		this.stockExchangeId = stockExchangeId;
		this.stockExchangeName = stockExchangeName;
		this.brief = brief;
		this.contactAddress = contactAddress;
		this.remarks = remarks;
	}
	
	

}
