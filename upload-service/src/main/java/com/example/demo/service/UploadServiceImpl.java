package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.StockPriceDao;
import com.example.demo.entity.StockPrice;
import com.example.demo.entity.UploadSummary;

@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private StockPriceDao stockPriceDao;	
	

//	public UploadServiceImpl(StockPriceDao stockPriceDao) {
//		super();
//		this.stockPriceDao = stockPriceDao;
//		
//	}

//	@SuppressWarnings("resource")
//	@Override
//	@Transactional
//	public List<StockPrice> uploadExcel(MultipartFile stockDataFile) throws Exception {
//		// TODO Auto-generated method stub
//		List<StockPrice> stockPrices = new ArrayList<>();
//		XSSFWorkbook book = new XSSFWorkbook(stockDataFile.getInputStream());
//		XSSFSheet sheet = book.getSheetAt(0);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
//		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
//			StockPrice curr = new StockPrice();
//			
//			XSSFRow row = sheet.getRow(i);
//			if (row == null) {
//				break;
//			}
//			
//			if(row.getPhysicalNumberOfCells() == 5) {
//				curr.setCompanyCode((String) row.getCell(0).getStringCellValue());
//				curr.setStockExchange(row.getCell(1).getStringCellValue());
//				curr.setPricePerShare((Double) row.getCell(2).getNumericCellValue());
//				//curr.setDate(row.getCell(3).getDateCellValue());
//				curr.setDate(LocalDate.parse(LocalDateTime.ofInstant(row.getCell(3).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatter), formatter));
//				//curr.setDate(LocalDate.parse(LocalDateTime.ofInstant(row.getCell(3).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatter), formatter).atStartOfDay());
//				curr.setTime(row.getCell(4).getStringCellValue().trim());
//				//curr.setTime(LocalDateTime.parse(LocalDateTime.ofInstant(row.getCell(4).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatterTime), formatterTime));
//				stockPrices.add(curr);
//			}else {
//				return new ArrayList<StockPrice>();
//			}
//			
//		}
//		for(StockPrice stockPrice: stockPrices) {
//			stockPriceDao.save(stockPrice);
//		}
//		return stockPrices;
//		
//	}
	
	public UploadServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	@Override
	@Transactional
	public UploadSummary uploadExcel(MultipartFile stockDataFile) throws Exception {
		// TODO Auto-generated method stub
		Boolean flag = true;
		List<StockPrice> stockPrices = new ArrayList<>();
		//List<LocalDate> dates = new ArrayList<>();
		XSSFWorkbook book = new XSSFWorkbook(stockDataFile.getInputStream());
		XSSFSheet sheet = book.getSheetAt(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			
			StockPrice curr = new StockPrice();
			
			XSSFRow row = sheet.getRow(i);
			if (row == null || !flag) {
				break;
			}
			
			if(row.getPhysicalNumberOfCells() == 5) {
				curr.setCompanyCode((String) row.getCell(0).getStringCellValue());
				curr.setStockExchange(row.getCell(1).getStringCellValue());
				curr.setPricePerShare((Double) row.getCell(2).getNumericCellValue());
				//curr.setDate(row.getCell(3).getDateCellValue());
				LocalDate date = LocalDate.parse(LocalDateTime.ofInstant(row.getCell(3).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatter), formatter);
				curr.setDate(date);
				//dates.add(date);
				//curr.setDate(LocalDate.parse(LocalDateTime.ofInstant(row.getCell(3).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatter), formatter).atStartOfDay());
				curr.setTime(row.getCell(4).getStringCellValue().trim());
				//curr.setTime(LocalDateTime.parse(LocalDateTime.ofInstant(row.getCell(4).getDateCellValue().toInstant(), ZoneId.systemDefault()).format(formatterTime), formatterTime));
				stockPrices.add(curr);
			}else {
				flag = false;
			}
			
		}
		
		UploadSummary summary = new UploadSummary();
		
		if(flag) {
			Collections.sort(stockPrices, (x, y) -> x.getDate().compareTo(y.getDate()));
			
			for(StockPrice stockPrice: stockPrices) {
				stockPriceDao.save(stockPrice);
			}
			
			
			summary.setCompanyCode(stockPrices.get(0).getCompanyCode());
			summary.setNumberofRecords(stockPrices.size());
			summary.setStockExchange(stockPrices.get(0).getStockExchange());
			summary.setStatus("Success");
			summary.setFrom(stockPrices.get(0).getDate());
			summary.setTo(stockPrices.get(stockPrices.size()-1).getDate());
			
		}else {
			summary.setStatus("Failure! Some Data is Missing.");
		}
		
		
		
		
		return summary;
		
	}

//	@Override
//	@Transactional
//	public UploadSummary showUploadSummary(List<StockPrice> stockPriceList) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	@Transactional
	public StockPrice retreiveHardcoded() {
		return new StockPrice((long)10001, "500012", "BSE", 100.0, LocalDate.now(), "10:45:56");
	}

	@Override
	@Transactional
	public List<StockPrice> retrieveAll() {
		// TODO Auto-generated method stub
		return stockPriceDao.findAll();
	}

	

}
