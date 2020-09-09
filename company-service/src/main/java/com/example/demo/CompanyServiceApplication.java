package com.example.demo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.CompanyDao;
import com.example.demo.dao.IPODao;
import com.example.demo.dao.CompanyCodeDao;
import com.example.demo.dao.StockExchangeDao;
import com.example.demo.entity.Company;
import com.example.demo.entity.CompanyCode;
import com.example.demo.entity.CompanyCodeId;
import com.example.demo.entity.IPO;
import com.example.demo.entity.StockExchange;

@SpringBootApplication
public class CompanyServiceApplication implements CommandLineRunner {

	protected Logger logger = Logger.getLogger(CompanyServiceApplication.class.getName());
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CompanyCodeDao companyCodeDao;
	@Autowired
	private StockExchangeDao stockExchangeDao;
	@Autowired
	private IPODao ipoDao;

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Company company1 = new Company((long)100,"Socgen", 120.0, "Ritesh", "Ritesh", "Finance", "nice");
//		Company company2 = new Company((long)200, "Societe", 120.0, "Ritesh", "Ritesh", "Software", "nice");

		Company company1 = new Company("Socgen", 120.0, "Ritesh", "Ritesh", "Finance", "nice"); //
		Company company2 = new Company("Societe", 120.0, "Ritesh", "Ritesh", "Software", "nice"); //

//		StockExchange stockExchange1 = new StockExchange((long)300, "BSE", "nicebrief", "Bombay", "Ex") ;
//		StockExchange stockExchange2 = new StockExchange((long)400, "NSE", "nicebrief", "Delhi", "Ex") ;

		StockExchange stockExchange1 = new StockExchange("BSE", "nicebrief", "Bombay", "Ex"); //
		StockExchange stockExchange2 = new StockExchange("NSE", "nicebrief", "Delhi", "Ex"); //

		this.stockExchangeDao.save(stockExchange1); //
		this.stockExchangeDao.save(stockExchange2); //

		this.companyDao.save(company1); //
		this.companyDao.save(company2); //

		CompanyCodeId id1 = new CompanyCodeId(company1.getCompanyId(), stockExchange1.getStockExchangeId()); //
		CompanyCodeId id2 = new CompanyCodeId(company1.getCompanyId(), stockExchange2.getStockExchangeId()); //
		CompanyCodeId id3 = new CompanyCodeId(company2.getCompanyId(), stockExchange1.getStockExchangeId()); //
		CompanyCodeId id4 = new CompanyCodeId(company2.getCompanyId(), stockExchange2.getStockExchangeId()); //

//		CompanyCode companyCode1 = new CompanyCode(id1, company1, stockExchange1, "Socgen_BSE", 100.0);
//		CompanyCode companyCode2 = new CompanyCode(id2, company1, stockExchange2, "Socgen_NSE", 100.0);
//		CompanyCode companyCode3 = new CompanyCode(id3, company2, stockExchange1,"Societe_BSE", 100.0);
//		CompanyCode companyCode4 = new CompanyCode(id4, company2, stockExchange2, "Societe_NSE", 100.0);
//		
		CompanyCode companyCode1 = new CompanyCode(id1, "Socgen_BSE", company1.getCompanyName(), 
				stockExchange1.getStockExchangeName(), 100.0);//
		CompanyCode companyCode2 = new CompanyCode(id2, "Socgen_NSE", company1.getCompanyName(),
				stockExchange2.getStockExchangeName(), 100.0);//
		CompanyCode companyCode3 = new CompanyCode(id3, "Societe_BSE", company2.getCompanyName(),
				stockExchange1.getStockExchangeName(), 100.0);//
		CompanyCode companyCode4 = new CompanyCode(id4, "Societe_NSE", company2.getCompanyName(),
				stockExchange2.getStockExchangeName(), 100.0);//

//		CompanyCode companyCode1 = new CompanyCode("Socgen_BSE", 100.0);
//		CompanyCode companyCode2 = new CompanyCode("Socgen_NSE", 100.0);
//		CompanyCode companyCode3 = new CompanyCode("Societe_BSE", 100.0);
//		CompanyCode companyCode4 = new CompanyCode("Societe_NSE", 100.0);

//		company1.getCompanyCodes().add(companyCode1);
//		company1.getCompanyCodes().add(companyCode2);
//		company2.getCompanyCodes().add(companyCode3);
//		company2.getCompanyCodes().add(companyCode4);
//		
//		stockExchange1.getCompanyCodes().add(companyCode1);
//		stockExchange1.getCompanyCodes().add(companyCode3);
//		stockExchange2.getCompanyCodes().add(companyCode2);
//		stockExchange2.getCompanyCodes().add(companyCode4);

//		this.stockExchangeDao.save(stockExchange1);
//		this.stockExchangeDao.save(stockExchange2);
//		
//		this.companyDao.save(company1);
//		this.companyDao.save(company2);

		this.companyCodeDao.save(companyCode1);//
		this.companyCodeDao.save(companyCode2);//
		this.companyCodeDao.save(companyCode3);//
		this.companyCodeDao.save(companyCode4);//

		// System.out.println(this.companyCodeDao.findAll());
//		this.companyCodeDao.findAll();

//		Set<CompanyCode> companyCodes1 = new HashSet<>();
//		Set<CompanyCode> companyCodes2 = new HashSet<>();
//		
//		companyCodes1.add(companyCode1);
//		companyCodes2.add(companyCode2);
//		companyCodes1.add(companyCode3);
//		companyCodes2.add(companyCode4);
//		
//		stockExchange1.setCompanyCodes(companyCodes1);
//		stockExchange2.setCompanyCodes(companyCodes2);
//		
//		this.stockExchangeDao.save(stockExchange1);
//		this.stockExchangeDao.save(stockExchange2);

//		this.companyCodeDao.deleteByCompanyId(company1.getCompanyId());//
//		this.companyDao.delete(company1);//
		// this.companyCodeDao.deleteByStockExchangeId(stockExchange1.getStockExchangeId());
		// logger.info(this.companyCodeDao.findAll());
		// this.companyDao.save(company1);
//
//		Company company3 = new Company((long) 5, "Socgen", 120.0, "Ritesh", "Ritesh Agrawal", "Finance", "nice");//
//		this.companyDao.save(company3);//

		// System.out.println(companyCode3.getStockExchange().getStockExchangeName());
		// System.out.println(companyCode4);
		// System.out.println(this.stockExchangeDao.findAll());
//		this.companyDao.findAll();//
//		this.companyCodeDao.findAll();//
//		this.stockExchangeDao.findAll();//
		
		IPO ipo1 = new IPO("Socgen1", "BSE", 100.0, (long) 1000,LocalDateTime.now() , "remarks");
		IPO ipo2 = new IPO("Socgen2", "BSE", 100.0, (long) 1000,LocalDateTime.now().minusMinutes(15) , "remarks");
		IPO ipo3 = new IPO("Socgen3", "BSE", 100.0, (long) 1000,LocalDateTime.now().minusMinutes(20) , "remarks");
		IPO ipo4 = new IPO("Socgen4", "BSE", 100.0, (long) 1000,LocalDateTime.now().plusMinutes(10) , "remarks");
		IPO ipo5 = new IPO("Socgen5", "BSE", 100.0, (long) 1000,LocalDateTime.now().plusMinutes(30) , "remarks");
		IPO ipo6 = new IPO("Socgen6", "BSE", 100.0, (long) 1000,LocalDateTime.now().minusHours(1) , "remarks");
		
		this.ipoDao.save(ipo1);
		this.ipoDao.save(ipo2);
		this.ipoDao.save(ipo3);
		this.ipoDao.save(ipo4);
		this.ipoDao.save(ipo5);
		this.ipoDao.save(ipo6);
//		this.ipoDao.findAllByOrderByOpenDatetimeAsc();
//		this.ipoDao.findAllByCompanyNameOrderByOpenDatetimeAsc("Socgen");
		

	}

}
