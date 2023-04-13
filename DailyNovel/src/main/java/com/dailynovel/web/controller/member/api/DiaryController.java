package com.dailynovel.web.controller.member.api;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


	@PostMapping
	public String insert(@RequestBody Map<String, Object> dry) throws ParseException {

		// service.insertDiary(diary);
		//
		// diary.setMemberId(1);
		Integer feelingId = 0;
		Integer weatherId = 0;
		
		int memberId = Integer.parseInt(String.valueOf(dry.get("memberId")));
		int templateId = Integer.parseInt(String.valueOf(dry.get("templateId")));
		if(String.valueOf(dry.get("feelingId")).equals("null")) {
			feelingId = null;
		}
		else
			feelingId = Integer.parseInt(String.valueOf(dry.get("feelingId")));
//		Integer feelingId = 
//		Integer 
		weatherId = Integer.parseInt(String.valueOf(dry.get("weatherId")));
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

	
	@GetMapping("getList")
	public List<Diary> getDiaryList(
			@RequestParam(required=false) Integer tid,
			@RequestParam(required=false) Integer fid,
			@RequestParam(required=false) Integer wid,
			@RequestParam(required=false) String regDate,
			HttpSession session) {
//		int memberid = (int) session.getAttribute("id");
		System.out.println("이게 날짜"+regDate);
		int memberid = (int)session.getAttribute("id");
		List<Diary> list = service.getAllByCriterion(memberid, tid, fid, wid, regDate);
		System.out.println(list);
//		System.out.println("이거한번봐봐"+ list.get(0).getRegDate().toString());
		return list;
	}
}
