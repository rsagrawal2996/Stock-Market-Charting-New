package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ipo_id")
	private Long ipoId;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "company_id", nullable = false)
//	private Company company;
	
	@Column(name = "company_name")
	private String companyName;
	
	
	@Column(name = "stock_exchange_name")
	private String stockExchangeName;
	
	@Column(name = "price_per_share")
	private Double pricePerShare;
	
	@Column(name = "total_shares")
	private Long totalShares;
	
	@Column(name = "open_datetime", columnDefinition = "TIMESTAMP NULL")
	private LocalDateTime openDatetime;
	
	@Column(name = "remarks")
	private String remarks;

	public IPO(String companyName, String stockExchangeName, Double pricePerShare, Long totalShares,
			LocalDateTime openDatetime, String remarks) {
		super();
		this.companyName = companyName;
		this.stockExchangeName = stockExchangeName;
		this.pricePerShare = pricePerShare;
		this.totalShares = totalShares;
		this.openDatetime = openDatetime;
		this.remarks = remarks;
	}	
	
	

}
