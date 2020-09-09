package com.example.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CompanyRequestModel;
import com.example.demo.models.CompanyResponseModel;
import com.example.demo.models.IPORequestModel;
import com.example.demo.models.IPOResponseModel;
import com.example.demo.service.IPOService;

@RestController
@RequestMapping("/ipos")
public class IPOController {
	private IPOService ipoService;

	public IPOController(IPOService ipoService) {
		super();
		this.ipoService = ipoService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<IPOResponseModel>> getAllIPO(){
		List<IPOResponseModel> ipos = ipoService.getAllIPO();
		
		return new ResponseEntity<List<IPOResponseModel>>(ipos, HttpStatus.OK);
	}
	
	@GetMapping("/companyName/{companyName}")
	public ResponseEntity<List<IPOResponseModel>> getIPOByCompanyName(@PathVariable("companyName") String companyName){
		List<IPOResponseModel> ipos = ipoService.getAllIPOByCompanyName(companyName);
		if(!ipos.isEmpty()) {
			return new ResponseEntity<List<IPOResponseModel>>(ipos, HttpStatus.FOUND);
		}
		return new ResponseEntity<List<IPOResponseModel>>(ipos, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/id/{ipoId}")
	public ResponseEntity<IPOResponseModel> findCompanyById(@PathVariable("ipoId") Long ipoId){
		IPOResponseModel ipoResponse = ipoService.getIPOById(ipoId);
		if(ipoResponse!=null) {
			return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.FOUND);
		}
		return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	public ResponseEntity<IPOResponseModel> addIPO(@RequestBody IPORequestModel ipoRequestModel){
		IPOResponseModel ipoResponse = ipoService.addIPO(ipoRequestModel);
		if(ipoResponse!=null) {
			return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.CREATED);
		}
		return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/update/{ipoId}")
	public ResponseEntity<IPOResponseModel> updateCompany(@PathVariable("ipoId") Long ipoId, @RequestBody IPORequestModel ipoRequestModel){
		IPOResponseModel ipoResponse = ipoService.updateIPO(ipoId, ipoRequestModel);
		if(ipoResponse!=null) {
			return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<IPOResponseModel>(ipoResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	

}
