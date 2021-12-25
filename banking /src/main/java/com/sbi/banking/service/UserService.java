package com.sbi.banking.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sbi.banking.model.UserRegister;
import com.sbi.banking.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserRegister getUser(Long id)
	{
		return userRepository.findById(id).get();
	}
	
	public void saveUser(UserRegister user)
	{
		userRepository.save(user);
	}
	
	public UserRegister findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public UserRegister findByAadharNumber(Long aadharNumber)
	{
		return userRepository.findByAadharNumber(aadharNumber);
	}
	public Boolean existsByPhoneNumber(String phoneNumber)
	{
		return userRepository.existsByPhoneNumber(phoneNumber);
	}

	public UserRegister getByPassword(String password)
	{
		return userRepository.getByPassword(password);
	}
	
	public UserRegister findByName(String name)
	{
		return userRepository.findByName(name);
	}
}