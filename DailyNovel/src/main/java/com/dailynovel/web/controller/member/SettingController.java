package com.dailynovel.web.controller.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

//import com.aspose.words.Document;
import com.dailynovel.web.entity.Export;
import com.dailynovel.web.entity.Setting;
import com.dailynovel.web.entity.setFont;
import com.dailynovel.web.service.SettingService;
import com.dailynovel.web.service.파일없음예외;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/setting/")
public class  SettingController {

	@Autowired
	private SettingService settingService;
	
	
	@Autowired
	private JavaMailSender mailSender; //일기 email전송을 위해 필요한 api?
	
	private String imageName;
//	private Integer id;
//	public SettingController() {
//		id=1;
//	}
	
	@RequestMapping("main")
	public String main(HttpSession session){
		
//		if(session.getAttribute("id")==null)
//			return "redirect:/user/login";
		
		return "/member/settings/main";
	}

		// 세팅-프로필-------------------------------------------------------------------
		@RequestMapping("profile")
		public String profile(Model model, HttpSession session) {
	       
	        System.out.println(session.getAttribute("id"));
	     
			
			Setting setting = settingService.getById((Integer)session.getAttribute("id")); // id 1번의 member테이블의 값 가지고 오기
			model.addAttribute("setting", setting);		// 가지고 온 테이블 값을 model에 심기
			//System.out.println(setting);				// 삭제요망 제대로 가지고 왔는지 확인차 출력해 보기 삭제요망
			imageName = setting.getProfileImage();		// 가지고 온 profile이미지의 명칭을 전역변수에 넣기
			//System.out.println(imageName);
			return "member/settings/component/profile";
		}

		@PostMapping("profile/update")
		public String profileUpdate(Model model,
				// MultipartFile profileIma,
				@RequestPart(name = "image") MultipartFile profile, 
				@RequestParam("name") String Nickname,
				@RequestParam("stsMessage") String stsMessage, 
				@ModelAttribute Setting setting, 
				HttpServletRequest request,
				HttpSession session
				) throws Exception {
			
			if (profile != null && !profile.isEmpty()) { // 사용자가 새로운 이미지를 등록 했을 때만 실행하기
			// 전에 등록한 프로필 사진 사진파일 삭제하는 코드?
			String beforeImagePath = System.getProperty("user.home"); // 컴퓨터의 사용자 경로 추출 
/*노트북 경로*/ Path filePath = Paths.get( beforeImagePath + "/Desktop/novelPrj/mon/DailyNovel/src/main/webapp/img/profile/" + imageName);
// 노트북 경로 Path filePath = Paths.get( beforeImagePath + "/Desktop/proproprj/DailyNovel/src/main/webapp/img/profile/" + imageName);
// *데스크톱경로*/Path filePath = Paths.get( beforeImagePath + "/Desktop/novelPrj(2)/nav/DailyNovel/src/main/webapp/img/profile/" + imageName); 
			
			try {
				// 삭제하는 클래스 생성(사실상 서비스를 호출) service.deleteImage(filePath);
				// 서비스에 deleteImage매소드를 만들고, 그 안에서 filePath에 해당 이미지 파일잉 없으면 출력할 예외만들기 'throw new 사진없음예외();'
				settingService.deleteBeforeImage(filePath);
				if(!imageName.equals("pro-img.png"))
					Files.delete(filePath);				
			}
			catch(파일없음예외 e){
				//System.out.println(e.getMessage());
			}
			
			String realPath= "";
			
				Date date = new Date(System.currentTimeMillis()); // 현재 시간 측정
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd-HH-mm-ss-SS"); // 시간 측정 포멧 지정
				String time = format.format(date); // 측정한 시간을 포멧화 하기
				MultipartFile img = profile; // input된 이미지를 파일에 넣기
				String profileImage = time + "__" + img.getOriginalFilename(); // 이미지 파일의 이름을 추출

				String urlPath = "/img/profile/" + profileImage; // 업로드할 파일이 저장될 경로
				realPath = request.getServletContext().getRealPath(urlPath); // 실제 파일 경로

				byte[] buf = new byte[1024];
				InputStream fis = img.getInputStream();
				int size = 1024;
				FileOutputStream fos = new FileOutputStream(realPath);
	 
				while ((size = fis.read(buf)) != -1) {
					fos.write(buf, 0, size);
				}
				fis.close();
				fos.close();
				setting.setProfileImage(profileImage);
			}
			//Integer id = 1;
			setting.setId((Integer)session.getAttribute("id"));
			setting.setNickName(Nickname);
			setting.setStatusMessage(stsMessage);
			int a = settingService.updateProfile(setting);
			
			if(setting.getProfileImage()!=null) {  // 사진의 업데이트가 있으면 5초 지연
				Thread.sleep(5000);
				return "redirect:../profile";
			}
			else								   // 사진의 업데이트가 없으면 바로 전송(상태메시지 수정만 있는 경우
				return "redirect:../profile";
		}

		// 세팅-폰트-------------------------------------------------------------------
		@RequestMapping("/font")
		public String font(Model model, Model model2, Model model3, HttpSession session) {

			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
			
			List<setFont> font = settingService.getByFontId();
			
			model2.addAttribute("setting", setting);
			model.addAttribute("font", font);
			
			/* 폰트 미리보기 문구 및 선택하기(?)*/
			Random rand = new Random();
			int x = rand.nextInt(9);
			List<String> preview = new ArrayList<>();
			preview.add("푸른 물망초의 꽃말을 아시나요?");
			preview.add("내가 만든 쿠키, 너를 위해 구웠지.");
			preview.add("오, 캡틴 마이 캡틴");
			preview.add("비를 맞고 걷는 사람에게 필요한 것은 우산이 아니라..");
			preview.add("앗,김흥식 법률사무소 뽀식이네 감자탕 보다 싸다.");
			preview.add("첫눈에 반한다는 말을 믿나요? 아니면 제가 다시 걸어와 볼까요?");
			preview.add("바람소리와 스산한 빗소리가 사무실 창밖을 때린다.");
			preview.add("정말 좋아합니다. 이번엔 거짓이 아니라고요");
			preview.add("뜨거운 사람 함부로 발로 차지마라, 너는 누구에게..");
			
			model3.addAttribute("preview", preview.get(x));
			
			return "member/settings/component/font";
		}
		
		@RequestMapping("/font/update")
		public String fontUpdate(Model model,
				@RequestParam(name = "font", required = false) String font,
				@RequestParam(name = "fontSize", required = false) int fontSize,
				@ModelAttribute Setting setting,
				HttpSession session
				) {
			//Integer id = 1;
			
			setting.setId((Integer)session.getAttribute("id"));
			setting.setFontFamily((font));
			setting.setFontSize((fontSize==16 ? "16" : fontSize==22?"22":"28"));
			
			int a = settingService.updateFont(setting);

				return "redirect:../font";
		}

		// 세팅-알람-------------------------------------------------------------------
		@RequestMapping("/alarm")
		public String alarm(Model model, HttpSession session) {
			
			//Integer id = 1;
			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
			model.addAttribute("setting", setting);
			return "member/settings/component/alarm";
		}

		@PostMapping("/alarm/update")
		public String alarm(Model model, 
				@RequestParam(name = "web-alarm", required = false) String alarmSwitch,
				@RequestParam(name = "katolk-alarm", required = false) String kakaoAlarmSwitch,
				@RequestParam(name = "timer") String alarmTime, 
				@ModelAttribute Setting setting,
				HttpSession session) {
			
			//Integer id = 1;
			setting.setId((Integer)session.getAttribute("id"));
			setting.setAlarmSwitch((alarmSwitch == null ? "0" : "1"));
			setting.setKakaoAlarmSwitch((kakaoAlarmSwitch == null ? "0" : "1"));
			setting.setAlarmTime(alarmTime);

			int a = settingService.updateProfile(setting);

			if (a == 1)
				return "redirect:../alarm";
			else
				return "ㅋㅋ";
		}

		// 세팅-내보내기-------------------------------------------------------------------
		@RequestMapping("/export")
		public String export(Model model, HttpSession session) throws UnknownHostException {
			
			//Integer id = 1;
			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
			model.addAttribute("setting", setting);
			return "member/settings/component/export";
		}

		@RequestMapping("/export/email")
		public String exportEmail(Model model, HttpServletResponse response, HttpSession session) throws Exception {
			//Integer id = 1;
			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
			List<Export> export = settingService.getDiaryListByid((Integer)session.getAttribute("id"));

		    MimeMessage mail = mailSender.createMimeMessage();
		    // use the true flag to indicate you need a multipart message
		    MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");// 메일 보내는 것 형식?
		    helper.setTo(setting.getEmail());                                     // 받는 사람 메일
		    helper.setSubject("DailyNovel 일기목록 메일입니다.");						  // 메일 제목
		    
		    String content = "<html><body>\n";									  // 메일 내용~
		    for (Export aa : export) {
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(aa.getRegDate());
		    	content += date+" ["+aa.getFeelingName()+"]<br>"+aa.getText()+"<br><br><hr>"; 	
		    	
		    }
		    content+="</body></html>";											  // ~메일 내용
		    helper.setText(content,true);
		    
		    mailSender.send(mail);
		    //return "member/settings/component/export";
		    return "true";
		  }
		
		@RequestMapping("/export/text")
		public void exportText(Model model, HttpServletResponse response, HttpSession session) throws Exception {
			
			//Integer id = 1;
			List<Export> export = settingService.getDiaryListByid((Integer)session.getAttribute("id"));
			
			// 1) com.lowagie.text.Document 클래스 인스턴스를 생성합니다.
		Document document = new Document();
		Date makingTime = new Date(System.currentTimeMillis()); // 현재 시간 측정
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd-HH-mm-ss-SS"); // 시간 측정 포멧 지정
		String time = format.format(makingTime); // 측정한 시간을 포멧화 하기
		File file = new File(time+"DailyNovel.PDF");
		
		try {
			// 2) Writer와 Document 사이의 연관을 맺어줍니다.
			PdfWriter.getInstance(document, new FileOutputStream(file));	
			
			// 3)  문서를 오픈합니다.
			document.open();
			
			// 4) 한글 입력을 위해 폰트를 선택해줍니다. iTextAsian.jar에서는 다음 3개의 폰트를 사용할 수 있습니다.
			// HYGoThic-Medium, HYSMyeongJo-Medium, HYSMyeongJoStd-Medium
			String fontFace = "HYGoThic-Medium";
			
			// 5) 글자 방향을 결정하는 CMap은 두가지가 있습니다. 
			// UniKS-UCS2-H : 가로, UniKS-UCS2-V : 세로
			String fontNameH = "UniKS-UCS2-H";

			// 6) 준비한 설정값들을 활용해 Font 객체를 생성해줍니다. 생성자에 들어가는 인자는 BaseFont 와 사이즈 입니다.
			BaseFont bf = BaseFont.createFont(fontFace, fontNameH, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bf, 11);
			
			// 7) 문서에 일기 내용 첨부	
			for (Export aa : export) {
				//out.print(aa.getRegDate());
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(aa.getRegDate());
				document.add(new Paragraph(date+" "+"  "+"["+aa.getFeelingName()+"]", font));
				//document.add(new Paragraph("["+aa.getFeelingName()+"]", font));
				document.add(new Paragraph("제목: "+aa.getTitle(), font));
				document.add(new Paragraph("\n", font));
				document.add(new Paragraph(aa.getText(), font));
				document.add(new Paragraph("\n", font));
				document.add(new Paragraph("\n", font));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			document.close();	
		}
		
		 // 바탕화면으로 전송
			String path = time + "DailyNovel.PDF";
				
			FileInputStream fis = new FileInputStream(path);
			byte buf[] = new byte[1024];

			int size = 1024;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(time + "DailyNovel.PDF", "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			while ((size = fis.read(buf)) != -1) {
				response.getOutputStream().write(buf, 0, size);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();

			fis.close();

			// 지연시간

			try {
			// 바탕화면 삭제
				Path testPdf = Paths.get(time + "DailyNovel.PDF");

				Files.delete(testPdf);
				
				// 디렉토리 삭제
//		            Files.delete(directoryPath);

			} catch (NoSuchFileException e) {
				//System.out.println("삭제하려는 파일/디렉토리가 없습니다");
			} catch (DirectoryNotEmptyException e) {
				//System.out.println("디렉토리가 비어있지 않습니다");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}

		// 세팅-피드백-------------------------------------------------------------------
		@RequestMapping("/service-help")
		public String serviceHelp(Model model, HttpSession session) {

			//Integer id = 1;
			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
			model.addAttribute("setting", setting);
			
			return "member/settings/component/service-help";
		}

		// 세팅-로그아웃-------------------------------------------------------------------
		@RequestMapping("/out")
		public String out(HttpSession session, Model model) {
						
			//Integer id = 63;
			Setting setting = settingService.getById((Integer)session.getAttribute("id"));
						
			model.addAttribute("setting", setting);
			return "/member/settings/component/out";
		}
		
		@RequestMapping("/out/logout")
		public String logOut(HttpSession session) {
			
			// 사실상 로그아웃도 기능이니 이 한 줄 'session.invalidate();'을 서비스에서 불러와야 하는 게 아닌가 싶다.
			session.invalidate();
			
			
			return "redirect:/";
		}

		@PostMapping("/out/delete")
		public String acountOut(Model model, 
				@ModelAttribute Setting setting,
				HttpSession session) {

			//Integer id = 63;
			setting.setId((Integer)session.getAttribute("id"));

			int a = settingService.deleteAcount((Integer)session.getAttribute("id"));
			//System.out.println(a);
//			return "member/settings/main";
			if (a == 1)
				return "redirect:../../../";
			else
				return "redirect:../../setting/out";
		}
	
}
