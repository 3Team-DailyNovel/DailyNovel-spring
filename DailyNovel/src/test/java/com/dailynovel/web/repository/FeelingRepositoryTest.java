package com.dailynovel.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.dailynovel.web.entity.FeelingViewDetails;

@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
class FeelingRepositoryTest {

	@Autowired
	private FeelingRepository repository;
	
	@Test
	void testFindAll() {
		List<FeelingViewDetails> list = repository.findAll();
		System.out.println(list);

	}

}
