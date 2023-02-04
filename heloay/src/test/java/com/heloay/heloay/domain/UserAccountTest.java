package com.heloay.heloay.domain;

import com.heloay.heloay.repository.MovieRepository;
import com.heloay.heloay.repository.ReviewRepository;
import com.heloay.heloay.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void userSave(){

        Movie movie = Movie.createMovie("스파이더", "ww.eg.gk", "스파이더맨", "거미줄 슝","액션",
                "톰 홀렌드", "몰?루", 120, "3");

        movieRepository.save(movie);

        Review review = Review.createReview(movie, "재밌네요",3);

        reviewRepository.save(review);

        UserAccount user1 = UserAccount.createUser("user1", movie, review, "cos1234", "홍길동", 20);

        UserAccount userAccount = userAccountRepository.save(user1);

        Assertions.assertThat(userAccount.getMovie().getId()).isEqualTo(1L);

    }
}