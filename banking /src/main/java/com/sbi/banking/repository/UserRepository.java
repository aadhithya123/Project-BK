package com.sbi.banking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sbi.banking.model.UserRegister;


@Repository
public interface UserRepository extends JpaRepository<UserRegister, Long>
{
	public UserRegister findByEmail(String email);
	public UserRegister findByAadharNumber(Long aadharNumber);
	public UserRegister getByPassword(String password);
	public boolean existsByPhoneNumber(String phoneNumber);
	Optional<UserRegister> existsByname(String name);
	public UserRegister findByName(String name);
}
