package com.email.verification.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class User {
	
	
	@Id
	private String email;
	
	String name; 
	
	String otp;

	boolean verified;
	
	private LocalDateTime otpGenerationTime;

	public User(String email, String name, String otp, boolean verified, LocalDateTime otpGenerationTime) {
		super();
		this.email = email;
		this.name = name;
		this.otp = otp;
		this.verified = verified;
		this.otpGenerationTime = otpGenerationTime;
	}

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public LocalDateTime getOtpGenerationTime() {
		return otpGenerationTime;
	}

	public void setOtpGenerationTime(LocalDateTime otpGenerationTime) {
		this.otpGenerationTime = otpGenerationTime;
	}

	
	

}