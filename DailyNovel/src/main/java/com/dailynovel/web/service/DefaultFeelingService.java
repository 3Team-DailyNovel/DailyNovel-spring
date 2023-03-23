package com.dailynovel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.web.entity.FeelingList;
import com.dailynovel.web.repository.FeelingRepository;

@Service
public class DefaultFeelingService implements FeelingService {

	@Autowired
	private FeelingRepository repository;
	
	public void setRepository(FeelingRepository repository){
		this.repository = repository;
	}
	
	@Override
	public List<FeelingList> getList() {
		return repository.findAll();
	}

		
	

}
