package com.email.verification.service;

import com.email.verification.request.RegisterRequest;
import com.email.verification.response.RegisterResponse;

public interface UserService {
	
	
    public RegisterResponse response(RegisterRequest request);
	
	public void verify(String email, String otp);
	

}
