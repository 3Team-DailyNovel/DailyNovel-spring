package com.dailynovel.web.service;

import java.util.ArrayList;
import java.util.Map;

import com.dailynovel.web.entity.Diary;
import com.dailynovel.web.entity.DiaryPreview;
import com.dailynovel.web.entity.DiaryView;

public interface DiaryService {
	
<<<<<<< HEAD
	Diary viewDiary();

=======
	//모재영 구현
	Diary Diary(Integer id);
	DiaryView viewDiary(Integer id);
	
>>>>>>> branch 'dev' of https://github.com/3Team-DailyNovel/DailyNovel-spring.git
	
	//노션 유진이의 공간 참고(여기에 선언한 이유) -> Diary관련 서비스라고 생각함 (미리보기 서비스가 아닌)
	Map<Integer, ArrayList<DiaryPreview>> getPreview(int year,int month);
<<<<<<< HEAD

=======
>>>>>>> branch 'dev' of https://github.com/3Team-DailyNovel/DailyNovel-spring.git
	void insertDiary(Diary diary);
<<<<<<< HEAD

	int updateDiary();
	int deleteDiary();
=======
	int modifyDiary(Diary diary);
	int removeDiary();
>>>>>>> branch 'dev' of https://github.com/3Team-DailyNovel/DailyNovel-spring.git
}
