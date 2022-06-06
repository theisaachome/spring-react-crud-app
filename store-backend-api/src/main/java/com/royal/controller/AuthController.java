package com.royal.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.royal.entity.Role;
import com.royal.entity.User;
import com.royal.payload.AuthResponse;
import com.royal.payload.SignInDto;
import com.royal.payload.SignUpDto;
import com.royal.repo.RoleRepo;
import com.royal.repo.UserRepo;
import com.royal.security.jwt.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 *@author Isaachome
 */

@Api(value = "Auth Controller exposes for sign up and sign in REST Apis")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired(required = true)
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserRepo userRepo;
	    @Autowired
	    private RoleRepo roleRepo;

	    @Autowired
	    private JwtTokenProvider jwtTokenProvider;
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @ApiOperation(value = "REST API to login or signin a user to the application.")
	    @PostMapping("/signin")
	    public ResponseEntity<AuthResponse> authenticateUser(
	    	@Valid	@RequestBody SignInDto loginDto){
	      Authentication authentication= authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        // get token from provider
	        String token = jwtTokenProvider.generateToken(authentication);
	        return   ResponseEntity.ok(new AuthResponse(token));
	    }


	    @ApiOperation(value = "REST API to register or signup a user to the application.")
	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(
	    	@Valid	@RequestBody SignUpDto signUpDto){
	        // check username availability
	        if(userRepo.existsByUsername(signUpDto.getUsername())){
	            return  new ResponseEntity<>("Username is already taken",HttpStatus.BAD_REQUEST);
	        }
	        // check email availability
	        if(userRepo.existsByEmail(signUpDto.getEmail())){
	            return  new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
	        }
	        // create user
	        User user=new User();
	        user.setUsername(signUpDto.getUsername());
	        user.setEmail(signUpDto.getEmail());
	        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
	        Role role = roleRepo.findByName("ROLE_ADMIN").get();
	        user.setRoles(Collections.singleton(role));
	        userRepo.save(user);
	        return  new ResponseEntity<>("User register successfully.",HttpStatus.OK);
	    }
	
}
