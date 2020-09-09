package com.example.demo.rest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.StockPrice;
import com.example.demo.entity.UploadSummary;
import com.example.demo.service.UploadService;

@RestController
@RequestMapping("/upload")
public class StockPriceController {
	//@Autowired
	private UploadService uploadService;

	public StockPriceController(UploadService uploadService) {
		super();
		this.uploadService = uploadService;
	}

//	public StockPriceController() {
//		super();
//	}

//	@PostMapping("/stockPriceList")
//	public ResponseEntity<List<StockPrice>> uploadData(@RequestParam("file") MultipartFile stockDataFile) throws Exception{
//		List<StockPrice> stockPrices  = new ArrayList<>();
//		stockPrices = uploadService.uploadExcel(stockDataFile);
//		if(stockPrices.isEmpty()) {
//			throw new Exception();
//		}		
//		
//		return new ResponseEntity<List<StockPrice>>(stockPrices, HttpStatus.OK);
//		
//	}
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/dummy")
	public StockPrice dummy() {
		return new StockPrice((long) 10006, "500012", "BSE", 100.1, LocalDate.now(), "10:45:58");
	}

	@GetMapping("/from-service")
	public StockPrice getStock() {
		return uploadService.retreiveHardcoded();
	}

	@GetMapping("/all-from-database")
	public ResponseEntity<List<StockPrice>> retrieveAll() {
		return new ResponseEntity<List<StockPrice>>(uploadService.retrieveAll(), HttpStatus.OK);
	}

	@PostMapping("/stockPriceList")
	public ResponseEntity<UploadSummary> uploadData(@RequestParam("file") MultipartFile stockDataFile)
			throws Exception {
		UploadSummary summary = new UploadSummary();
		summary = uploadService.uploadExcel(stockDataFile);
		if (summary.getStatus() != "Success") {
			throw new Exception();
		}

		return new ResponseEntity<UploadSummary>(summary, HttpStatus.OK);

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some Date is missing")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<UploadSummary> badDate() {
		// return "Some Data in the Excel Sheet is missing";
		UploadSummary summary = new UploadSummary();
		summary.setStatus("Failure! Some Data is Missing.");
		return ResponseEntity.badRequest().body(summary);

	}

}
