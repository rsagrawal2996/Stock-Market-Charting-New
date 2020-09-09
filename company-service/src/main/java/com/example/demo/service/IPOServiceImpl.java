package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IPODao;

import com.example.demo.entity.IPO;

import com.example.demo.models.IPORequestModel;
import com.example.demo.models.IPOResponseModel;

@Service
public class IPOServiceImpl implements IPOService{
	private IPODao ipoDao;
	private ModelMapper modelMapper;
	
	

	public IPOServiceImpl(IPODao ipoDao, ModelMapper modelMapper) {
		super();
		this.ipoDao = ipoDao;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public List<IPOResponseModel> getAllIPO() {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<IPOResponseModel>>() {}.getType();
		return modelMapper.map(ipoDao.findAllByOrderByOpenDatetimeAsc(), listType);
	}

	@Override
	@Transactional
	public List<IPOResponseModel> getAllIPOByCompanyName(String companyName) {
		// TODO Auto-generated method stub		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<IPOResponseModel>>() {}.getType();
		return modelMapper.map(ipoDao.findAllByCompanyNameOrderByOpenDatetimeAsc(companyName), listType);
	}

	@Override
	@Transactional
	public IPOResponseModel getIPOById(Long ipoId) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		IPO ipo = ipoDao.findById(ipoId).orElse(null);
		if(ipo!=null) {
			return modelMapper.map(ipo, IPOResponseModel.class);
		}	
		
		return null;
	}

	@Override
	@Transactional
	public IPOResponseModel addIPO(IPORequestModel ipoRequestModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		IPO ipo = ipoDao.save(modelMapper.map(ipoRequestModel, IPO.class));
		return modelMapper.map(ipo, IPOResponseModel.class);
	}

	@Override
	@Transactional
	public IPOResponseModel updateIPO(Long ipoId, IPORequestModel ipoRequestModel) {
		// TODO Auto-generated method stub
		IPO ipo = ipoDao.findById(ipoId).orElse(null);
		if(ipo == null) {
			return null;
		}
		ipo.setCompanyName(ipoRequestModel.getCompanyName());
		ipo.setOpenDatetime(ipoRequestModel.getOpenDatetime());
		ipo.setPricePerShare(ipoRequestModel.getPricePerShare());
		ipo.setRemarks(ipoRequestModel.getRemarks());
		ipo.setStockExchangeName(ipoRequestModel.getStockExchangeName());
		ipo.setTotalShares(ipoRequestModel.getTotalShares());
		ipo = ipoDao.save(ipo);
		return modelMapper.map(ipo, IPOResponseModel.class);
		
	}

}
