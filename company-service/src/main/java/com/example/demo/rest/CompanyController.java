package com.example.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Company;
import com.example.demo.models.CompanyRequestModel;
import com.example.demo.models.CompanyResponseModel;
import com.example.demo.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CompanyResponseModel>> getAllCompanies(){
		List<CompanyResponseModel> companies = companyService.getAllCompanies();
		
		return new ResponseEntity<List<CompanyResponseModel>>(companies, HttpStatus.OK);
	}
	
	@GetMapping("/id/{companyId}")
	public ResponseEntity<CompanyResponseModel> findCompanyById(@PathVariable("companyId") Long companyId){
		CompanyResponseModel companyResponse = companyService.findCompanyById(companyId);
		if(companyResponse!=null) {
			return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/companyName/{companyName}")
	public ResponseEntity<CompanyResponseModel> findCompanyByName(@PathVariable("companyName") String companyName){
		CompanyResponseModel companyResponse = companyService.findCompanyByCompanyName(companyName);
		if(companyResponse!=null) {
			return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{companyId}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable("companyId") Long companyId){
		String response = companyService.deleteCompanyById(companyId);
		if(response=="Successfully Deleted") {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<CompanyResponseModel> addCompany(@RequestBody CompanyRequestModel companyRequestModel){
		CompanyResponseModel companyResponse = companyService.addCompany(companyRequestModel);
		if(companyResponse!=null) {
			return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.CREATED);
		}
		return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/pattern/{name}")
	public ResponseEntity<List<CompanyResponseModel>> findCompanyByNameContaining(@PathVariable("name") String name){
		List<CompanyResponseModel> companyResponses = companyService.findCompanyByCompanyNameContaining(name);
		return new ResponseEntity<List<CompanyResponseModel>>(companyResponses, HttpStatus.OK);		
	}
	
	@PutMapping("/update/{companyId}")
	public ResponseEntity<CompanyResponseModel> updateCompany(@PathVariable("companyId") Long companyId, @RequestBody CompanyRequestModel companyRequestModel){
		CompanyResponseModel companyResponse = companyService.updateCompany(companyId, companyRequestModel);
		if(companyResponse!=null) {
			return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/sector/{sector}")
	public ResponseEntity<List<CompanyResponseModel>> findCompanyBySector(@PathVariable("sector") String sector){
		List<CompanyResponseModel> companyResponses = companyService.findCompanyBySector(sector);
		return new ResponseEntity<List<CompanyResponseModel>>(companyResponses, HttpStatus.OK);
	}
	
	@GetMapping("/companyCode/{companyCodeValue}")
	public ResponseEntity<CompanyResponseModel> findCompanyByCompanyCode(@PathVariable("companyCodeValue") String companyCodeValue){
		CompanyResponseModel companyResponse = companyService.findCompanyByCompanyCode(companyCodeValue);
		if(companyResponse!=null) {
			return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<CompanyResponseModel>(companyResponse, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/stockExchange/{stockExchangeName}")
	public ResponseEntity<List<CompanyResponseModel>> findCompanyByStockExchangeName(@PathVariable("stockExchangeName") String stockExchangeName){
		List<CompanyResponseModel> companyResponses = companyService.findCompanyByStockExchangeName(stockExchangeName);
		return new ResponseEntity<List<CompanyResponseModel>>(companyResponses, HttpStatus.OK);		
	}
	
	
	
		
	
	

}
