package com.dailynovel.web.service;

import java.nio.file.Path;
import java.util.List;

import com.dailynovel.web.entity.Export;
import com.dailynovel.web.entity.setFont;
import com.dailynovel.web.entity.Setting;


public interface SettingService {
	Setting getById(Integer id);
	List<Export> getDiaryListByid(Integer id);
	
	List<setFont> getByFontId();
	String getfontNameforCSS(int fid);
	
	int updateProfile(Setting setting);
	int updateFont(Setting setting);
	
	int deleteAcount(Integer id);
	void deleteBeforeImage(Path filePath) throws 파일없음예외;
	
}
