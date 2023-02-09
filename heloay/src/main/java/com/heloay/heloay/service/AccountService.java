package com.heloay.heloay.service;

import com.heloay.heloay.domain.UserAccount;
import com.heloay.heloay.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount create(UserAccount account){
        return userAccountRepository.save(account);
    }
}
