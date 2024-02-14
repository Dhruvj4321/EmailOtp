package com.email.verification.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.verification.repository.UserRepo;
import com.email.verification.request.RegisterRequest;
import com.email.verification.response.RegisterResponse;
import com.email.verification.entity.User;

import jakarta.mail.MessagingException;
import lombok.Builder;

@Builder
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	EmailService emailService;

	@Override
	public RegisterResponse response(RegisterRequest request) {
		// TODO Auto-generated method stub
		
		User existingUser = repo.findByEmail(request.getEmail());
		
		if (existingUser != null && existingUser.isVerified())
		{
			
			throw new RuntimeException("User already exists");
			
		}
		
		User user = User.builder().name(request.getName()).email(request.getEmail()).build();
		
		
		String otp = generateOtp();
		user.setOtp(otp);
		user.setOtpGenerationTime(LocalDateTime.now());
		repo.save(user);
		
		
		
		try {
			sendEmail(user.getEmail(), otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RegisterResponse response = RegisterResponse.builder().email(user.getEmail()).build();
		
		return response;
	}
	
	
	private boolean isOtpExpired(LocalDateTime otpGenerationTime) {
		
		LocalDateTime currentTime = LocalDateTime.now();
		
		return Duration.between(otpGenerationTime, currentTime).getSeconds() > 30;
		
	}

	
	
	private String generateOtp() {
		
		Random random = new Random();
		
		int otpValue = 100000 + random.nextInt(900000);
		
		return String.valueOf(otpValue);
		
	}
	
	
	private void sendEmail(String email, String otp) throws MessagingException {
		
		String subject = "Email verification";
		
		String body = "Your one time password is :" + otp;
		
		emailService.sendEmail(email, subject, body);
		
	}



	@Override
	public void verify(String email, String otp) {
		// TODO Auto-generated method stub
		
		User user = repo.findByEmail(email);
		
		if(user == null) {
			
			throw new RuntimeException("User not found");
			
		}
		else if(user.isVerified()) {
			throw new RuntimeException("User already verified");
		}
		
		else if(isOtpExpired(user.getOtpGenerationTime())) {
			
			throw new RuntimeException("OTP expired");
			
		}
		
		else if(otp.equals(user.getOtp())) {
			user.setVerified(true);
			repo.save(user);
		}
		else {
			throw new RuntimeException("Something went wrong");
		}
	}
	
	

}
