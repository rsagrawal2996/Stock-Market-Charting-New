package com.example.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private Long companyId;
	
	
	@Column(name = "company_name", unique = true, nullable = false)
	private String companyName;
	
	@Column(name = "turnover")
	private Double turnover;
	
	@Column(name = "ceo")
	private String ceo;
	
	//@Column(name = "stock_exchanges")
	@OneToMany(mappedBy = "company")//, cascade = CascadeType.ALL)
	private List<CompanyCode> companyCodes;// = new HashSet<>();
	
	@Column(name = "directors")
	private String directors;
	
	@Column(name = "sector")
	private String sector;
	
	@Column(name = "writeup")
	private String writeup;
	
//	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)	
//	private List<IPO> ipos;
	

	
//	public Company(String companyName, Double turnover, String ceo, String directors, String writeup) {
//		this.companyName = companyName;
//		this.turnover = turnover;
//		this.ceo = ceo;
//		this.directors = directors;
//		this.writeup = writeup;
//	}

//	public Company(Long companyId, String companyName, Double turnover, String ceo, String directors, String writeup) {
//		super();
//		this.companyId = companyId;
//		this.companyName = companyName;
//		this.turnover = turnover;
//		this.ceo = ceo;
//		this.directors = directors;
//		this.writeup = writeup;
//	}

	public Company(Long companyId, String companyName, Double turnover, String ceo, String directors, String sector,
			String writeup) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.turnover = turnover;
		this.ceo = ceo;
		this.directors = directors;
		this.sector = sector;
		this.writeup = writeup;
	}

	public Company(String companyName, Double turnover, String ceo, String directors, String sector, String writeup) {
		super();
		this.companyName = companyName;
		this.turnover = turnover;
		this.ceo = ceo;
		this.directors = directors;
		this.sector = sector;
		this.writeup = writeup;
	}
		

}
