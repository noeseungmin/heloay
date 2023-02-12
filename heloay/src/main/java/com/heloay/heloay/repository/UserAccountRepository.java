package com.heloay.heloay.repository;

import com.heloay.heloay.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
   UserAccount findByUsername(String username);

}
