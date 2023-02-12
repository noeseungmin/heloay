package com.heloay.heloay.config;

import com.heloay.heloay.domain.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AccountContext extends User {

    private final UserAccount userAccount;
    public AccountContext(UserAccount userAccount, Collection<? extends GrantedAuthority> authorities) {
    super(userAccount.getUsername(), userAccount.getPassword(), authorities);
    this.userAccount = userAccount;
    }

    public UserAccount getUserAccount(){
        return userAccount;
    }
}
