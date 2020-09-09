package com.example.demo.service;

import java.util.List;

import com.example.demo.models.IPORequestModel;
import com.example.demo.models.IPOResponseModel;

public interface IPOService {
	public List<IPOResponseModel> getAllIPO();
	public List<IPOResponseModel> getAllIPOByCompanyName(String companyName);
	public IPOResponseModel getIPOById(Long ipoId);
	public IPOResponseModel addIPO(IPORequestModel ipoRequestModel);
	public IPOResponseModel updateIPO(Long ipoId, IPORequestModel ipoRequestModel);

}
