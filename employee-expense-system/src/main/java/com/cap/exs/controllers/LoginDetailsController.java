package com.cap.exs.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.request.LoginRequest;
import com.cap.exs.response.JwtResponse;
import com.cap.exs.security.jwt.JwtUtils;
import com.cap.exs.security.services.UserDetailsImpl;
import com.cap.exs.services.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*",maxAge = 30)
@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "LoginDetails", tags = { "LoginAPI" })
public class LoginDetailsController {

	@Autowired
	LoginService loginService;
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	
	/**
	 * This method is for logging in
	 * 
	 * @param LoginRequest
	 * @return JwtResponse
	 * @throws {@link UsernameNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * @throws {@link BadCredentialsException}
	 * 
	 */
	
	@PostMapping("/signin")
	@ApiOperation(value = "Signin", response = ResponseEntity.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully signed in"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public ResponseEntity<JwtResponse> signIn(@ApiParam(name="Signin Request", required = true)@RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		Employee emp = employeeRepository.findByUsername(userDetails.getUsername());
		
		System.out.println("emp id = " + emp.getEmpId());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 emp.getEmpId(), 
												 userDetails.getUsername(), 
												 roles));
		
		
		
	}
	
}
