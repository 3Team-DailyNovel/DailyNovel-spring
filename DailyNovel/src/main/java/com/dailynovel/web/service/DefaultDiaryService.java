package com.dailynovel.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailynovel.web.entity.Diary;
import com.dailynovel.web.entity.DiaryPreview;
import com.dailynovel.web.entity.DiaryView;
import com.dailynovel.web.repository.DiaryRepository;

@Service
public class DefaultDiaryService implements DiaryService {
	
	@Autowired
	private DiaryRepository repository;
	

	@Override
	public Diary Diary(Integer id) {
		// TODO Auto-generated method stub
		Diary d = repository.view(id);
		
		return d;
	}

	@Override
	public DiaryView viewDiary(Integer id) {
		// TODO Auto-generated method stub
		DiaryView d2 = repository.findDiaryView(id);
		
		return d2;
	}
	
	
	@Override
<<<<<<< HEAD

=======
>>>>>>> branch 'dev' of https://github.com/3Team-DailyNovel/DailyNovel-spring.git
	public void insertDiary(Diary diary) {
		repository.register(diary);		
<<<<<<< HEAD
=======

	}	
	
	@Override
	public int modifyDiary(Diary diary) {
		
		return 0;
>>>>>>> branch 'dev' of https://github.com/3Team-DailyNovel/DailyNovel-spring.git
	}
	
	@Override
	public int removeDiary() {
		
		return 0;
	}

	

	public Map<Integer, ArrayList<DiaryPreview>> getPreview(int year,int month){
	//담을 객체 선언만 한거
		Map<Integer, ArrayList<DiaryPreview>> diaryMap = new HashMap<>();
		
		//다이어리 값 받아오기
		List<DiaryPreview> list = repository.getDiaryByMonthly(year, month);
		
		for(DiaryPreview dry : list) {
			int curdate = dry.getDiaryDate();
			//해당 날짜가 key:value 선언이 되어있는지 확인, 없으면 생성
			if (!diaryMap.containsKey(curdate)) {
				diaryMap.put(curdate, new ArrayList<DiaryPreview>());
		    }
			//key(날짜) 별 value(미리보기) 넣기
			diaryMap.get(curdate).add(dry);	
		}
		return diaryMap;
	}
}
