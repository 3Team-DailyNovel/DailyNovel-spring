package com.dailynovel.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dailynovel.web.entity.FeelingList;

@Mapper
public interface FeelingRepository {
	
		List<FeelingList> findAll();

}
