package com.heloay.heloay.repository;

import com.heloay.heloay.domain.Article;
import com.heloay.heloay.domain.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
@Slf4j
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    void findArticle() {
        UserAccount user1 = UserAccount.builder()
                .userId("user1")
                .password("123456")
                .username("홍길동")
                .age(20)
                .build();
        UserAccount user = userAccountRepository.save(user1);
        log.info(user.toString());

        Article article = Article.builder()
                .userAccount(user)
                .title("제목입니다")
                .content("내용입니다")
                .build();

        Article saveArticle = articleRepository.save(article);

        assertNotNull(saveArticle.getCreateAt());

    }
}