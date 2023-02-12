package com.heloay.heloay.service;

import com.heloay.heloay.domain.UserAccount;
import com.heloay.heloay.dto.SignUpDto;
import com.heloay.heloay.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAccountRepository userAccountRepository;

    @Transactional
    public UserAccount createUser(SignUpDto dto){
        UserAccount userAccount = dto.toEntity();
        userAccountRepository.save(userAccount);
        return userAccount;
    }
}
