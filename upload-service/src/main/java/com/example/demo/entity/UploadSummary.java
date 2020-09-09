package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadSummary {
	private String status;
	private String stockExchange;
	private String companyCode;
	private Integer numberofRecords;
	private LocalDate from;
	private LocalDate to;

}
