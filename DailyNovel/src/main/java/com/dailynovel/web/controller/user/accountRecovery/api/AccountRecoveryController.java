package com.dailynovel.web.controller.user.accountRecovery.api;

import java.util.Random;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailynovel.web.service.MemberService;

import jakarta.mail.internet.MimeMessage;

@RequestMapping("/user/account-recovery")
@RestController("apiAccountRecoveryController")
public class AccountRecoveryController {

//	
	@Autowired
	private JavaMailSenderImpl sender;

	@Autowired
	private MemberService service;

	@RequestMapping("mailCheck")
	public String mailCheck(String email) throws Exception {
		int checkEmail = service.FindSameEmail(email);
		if (checkEmail == 1) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
			uuid = uuid.substring(0, 15); // uuid를 앞에서부터 10자리 잘라줌.
			System.out.println(uuid);
			boolean temporaryPassword = service.temporaryPassword(email, uuid);
			if (temporaryPassword) {
				MimeMessage message = sender.createMimeMessage();
				// use the true flag to indicate you need a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, false);
				helper.setTo(email);
				helper.setSubject("DailyNovel 임시 비밀번호 메일입니다.");
				// use the true flag to indicate the text included is HTML
				helper.setText("<html><body>임시 비밀번호:" + uuid + "</body></html>", true);
				// let's include the infamous windows Sample file (this time copied to c:/)
				sender.send(message);
				return "true";
			}
			return "false";
		}
		return "false";
	}

	@RequestMapping("nicknameCheck")
	public String nicknameCheck(String nickname) {

		String email = service.findEmailByNickname(nickname);
		if (email != null) {
			return email;
		}
		return "false";
	}

	@RequestMapping("passwordchange")

	public String passwordChage(String password, String email) {

		int passwordChangeCheck = service.passwordChange(password, email);
		if (passwordChangeCheck == 1) {
			return "success";
		}

		return "false";
	}

	@RequestMapping("update-password")
	public String renew() {
		return "/user/account-recovery/update-password";
	}

}