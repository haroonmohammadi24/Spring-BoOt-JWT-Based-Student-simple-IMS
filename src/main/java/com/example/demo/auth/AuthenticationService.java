package com.example.demo.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.entity.Roles;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	@Autowired
	private final UserRepository userRepo;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private final AuthenticationManager authManager;
	
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user=com.example.demo.entity.User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()) )
				.roles(Roles.USER)
				.build(); 
		userRepo.save(user);
		var jwtToken=jwtService.generateToken(user);
				
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user=userRepo.findByEmail(request.getEmail()).orElseThrow();
		var jwt=jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwt)
				.build();
	}
	
	
	
	


}
