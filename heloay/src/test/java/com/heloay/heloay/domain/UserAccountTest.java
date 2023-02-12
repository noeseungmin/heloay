package com.heloay.heloay.domain;

import com.heloay.heloay.repository.MovieRepository;
import com.heloay.heloay.repository.ReviewRepository;
import com.heloay.heloay.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserAccountTest {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserAccountRepository userAccountRepository;


    }
