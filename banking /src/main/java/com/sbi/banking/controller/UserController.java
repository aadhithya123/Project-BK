package com.sbi.banking.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.banking.model.ERole;
import com.sbi.banking.model.Role;
import com.sbi.banking.model.UserRegister;
import com.sbi.banking.payload.request.LoginRequest;
import com.sbi.banking.payload.request.SignupRequest;
import com.sbi.banking.payload.response.JwtResponse;
import com.sbi.banking.payload.response.MessageResponse;
import com.sbi.banking.repository.RoleRepository;
import com.sbi.banking.repository.UserRepository;
import com.sbi.banking.security.jwt.JwtUtils;
import com.sbi.banking.security.service.UserDetailsImp;
import com.sbi.banking.service.UserService;

@RestController
@RequestMapping("/sbi")
public class UserController 
{

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;
	
	
//	@PostMapping(value = "/saveUser")
//	public ResponseEntity<?> postUser(@RequestBody UserRegister user)
//	{	
//		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    String encoderPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encoderPassword);
//		user.setActive(true);
//		user.setDeactive(false);
//		user.setCreditedOn(new Date());
//		UserRegister existingEmail = userService.findByEmail(user.getEmail());
//		UserRegister existingAadharNum = userService.findByAadharNumber(user.getAadharNumber());
//		Boolean existingPhoneNum = userService.existsByPhoneNumber(user.getPhoneNumber());
//		
//		if (existingEmail != null) 
//		{
//            return new ResponseEntity<>("There is already an account registered with that email please try new email", HttpStatus.NOT_FOUND);
//        }
//		else if(existingAadharNum != null )
//		{
//			return new ResponseEntity<>("There is already an account registered with that Aadhar Number please try new one", HttpStatus.OK);
//		}
//		else if(existingPhoneNum == true)
//		{
//			 return new ResponseEntity<>("There is already an account registered with that phone number please try new number", HttpStatus.NOT_FOUND);
//		}
//		else
//		{
//			userService.saveUser(user);		
//			return new ResponseEntity<>("Successfully Submitted Go To Login Page", HttpStatus.OK);
//		}
//	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) 
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getUserId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
//	userRepository.existsByname(signUpRequest.getName()
	
	// @PostMapping(value = "/signup")
	
	
	
	@RequestMapping(value="/all", headers="Accept=application/json", method=RequestMethod.GET)
	  public List<UserRegister>  getAllUsers() {
	    // This returns a JSON or XML with the users
		List<UserRegister> listValues = userRepository.findAll();
	    return listValues;
	  }
	
	@RequestMapping(value="/signup", headers="Accept=application/json", method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest)
	{
		signUpRequest.setActive(true);
		signUpRequest.setCreditedOn(new Date());
		signUpRequest.setDeactive(false);
		System.out.println(signUpRequest);
		UserRegister existingEmail = userService.findByEmail(signUpRequest.getEmail());
		UserRegister existingAadharNum = userService.findByAadharNumber(signUpRequest.getAadharNumber());
		Boolean existingPhoneNum = userService.existsByPhoneNumber(signUpRequest.getPhoneNumber());
		UserRegister exitingUserName = userRepository.findByName(signUpRequest.getName());
//		String encoderPassword = encoder.encode(signUpRequest.getPassword());
//		signUpRequest.setPassword(encoderPassword);
//		List <UserRegister> s = userRepository.findAll();
//		for(Object o : s)
//		{
//			
//			System.out.println("List of Values: "+o.toString());
//		}
//		System.out.println("");
		
		
		 if(existingAadharNum != null)
		 {
		for(UserRegister object : getAllUsers())
		{
			if(object.getAadharNumber().equals(existingAadharNum))
			{
				break;
			}
		}
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Aadhar Number is already in use!"));
	  }	
		
//		if (existingAadharNum != null) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Aadhar Number is already taken!"));
//		}

	    if (existingEmail != null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if (existingPhoneNum == true) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Phone Number is already in use!"));
		}
		if(exitingUserName != null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: UserName is already in use!"));
		}
		
		// Create new user's account
		UserRegister userRegister = new UserRegister(signUpRequest.getName(),signUpRequest.getEmail(),
											 signUpRequest.getPhoneNumber(),signUpRequest.getDateOfBirth(),
											 signUpRequest.getAadharNumber(),signUpRequest.getGender(),
											 signUpRequest.getMaritalStatus(),signUpRequest.getNationality(),
											 signUpRequest.getPermanentAddress(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getCreditedOn(),signUpRequest.getActive(),signUpRequest.getDeactive());
//		System.out.println(userRegister);
		Set<String> strRoles = signUpRequest.getRole();
		
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}
		
			else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					
					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		userRegister.setRole(roles);
		userRepository.save(userRegister);
//		System.out.println(user);
//		System.out.println(userRepository.save(user));
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"+"     "+userRegister));
	}
}