package com.heloay.heloay.repository;

import com.heloay.heloay.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
   UserAccount findByUsername(String username);

}
