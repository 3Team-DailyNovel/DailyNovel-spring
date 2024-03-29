package com.dailynovel.web.controller.member.api;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.web.entity.Diary;
import com.dailynovel.web.service.DiaryService;

import jakarta.servlet.http.HttpSession;

@RestController("apiDiaryController")
@RequestMapping("/diarys")
public class DiaryController {

	@Autowired
	private DiaryService service;

	@GetMapping("preview")
	public Map<Integer, ArrayList<Diary>> getPreview(
			@RequestParam int year,
			@RequestParam int month,
			HttpSession session) {
		int memberid = (int) session.getAttribute("id");
		Map<Integer, ArrayList<Diary>> list = service.getAllbyMonthly(memberid, year, month);
		System.out.println(list);
		return list;
	}

	// @PostMapping
	// public String insert(@RequestBody Diary diary) {
	// service.insertDiary(diary);
	//
	// diary.setMemberId(1);
	//
	//
	// System.out.println(diary);
	// return "ㄷ...될까..?";
	// }

	@PostMapping
	public String insert(@RequestBody Map<String, Object> dry
			,HttpSession session) throws ParseException {

		// service.insertDiary(diary);
		//
		// diary.setMemberId(1);
		Integer feelingId = 0;
		Integer weatherId = 0;
		
//		int memberId = Integer.parseInt(String.valueOf(dry.get("memberId")));
		int memberId = (int) session.getAttribute("id");
		int templateId = Integer.parseInt(String.valueOf(dry.get("templateId")));
		if(String.valueOf(dry.get("feelingId")).equals("null")) {
			feelingId = null;
		}
		else
			feelingId = Integer.parseInt(String.valueOf(dry.get("feelingId")));
//		Integer feelingId = 
//		Integer
		if(String.valueOf(dry.get("weatherId")).equals("null")) {
			weatherId = null;
		}
		else
			weatherId = Integer.parseInt(String.valueOf(dry.get("weatherId")));
//		weatherId = Integer.parseInt(String.valueOf(dry.get("weatherId")));
		Integer honesty = Integer.parseInt(String.valueOf(dry.get("honesty")));
		String regDate = (String) dry.get("regDate");
		String title = (String) dry.get("title");
		String content = (String) dry.get("content");

//		String currentTimestampToString = "2022/12/12 08:03:15";


	//  String to Timestamp
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	dateFormat.setLenient(false);// 날짜와 시간을 엄격하게 확인
	
	
//	try {
//	    
//	    System.out.println(stringToTimestamp);
//	} catch (ParseException e) {
//	    e.printStackTrace();
//	}

	Date stringToDate = dateFormat.parse(regDate);
    Timestamp stringToTimestamp = new Timestamp(stringToDate.getTime());  
    
    Diary diary = new Diary(memberId, templateId, feelingId, weatherId, honesty, stringToTimestamp, title, content, null);
    
	service.insertDiary(diary);
			
		

		return "ㄷ...될까..?";
	}
	
	@PutMapping
//	@PutMapping
	public int modify(
//			@PathVariable("id") Integer id,
//			@RequestBody String title,
//			@RequestBody String content) {
			@RequestBody Diary diary) {
		
		
		//1 값은 자료형이 int 라서 어쩔수없이 넣음
//		Diary d= new Diary(1, 1, null, null, null, null, title, content,id);
		Diary d= new Diary(1, 1, null, null, null, null, diary.getTitle(), diary.getContent(),diary.getId());
		
		System.out.println(d.getTitle());
		System.out.println(d.getContent());
		System.out.println(d.getId());
		
		
		
		service.modifyDiary(d);
		
		return 1;
	}
	
	// ajax 테스트
	@GetMapping("{id}") 
	public Diary get(
			@PathVariable("id") int id){
		
		Diary d = service.Diary(id);
		
//		return "menu " + id;
		return d;
		//객체를 못전달해 이를 다 표현하기 위해 문자열로 바꾸거나,바꾸는 표현식을 써야해!
		//요새 트렌드는 JSON과 XML을 써야
		
	};
	
	@DeleteMapping("{id}")
	public String delete(
			@PathVariable int id) {
		
		int result = service.removeDiary(id);
		
		return id + "완료";
	}

}
