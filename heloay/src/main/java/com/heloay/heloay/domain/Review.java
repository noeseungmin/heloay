package com.heloay.heloay.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SequenceGenerator(
        name = "REVIEW_SEQ_GENERATOR", // 시퀀스 생성기 이름
        sequenceName = "REVIEW_SEQ", // 실제 DB의 시퀀스
        initialValue = 1, allocationSize = 1)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username")
    private UserAccount userAccount;
    @Column(length = 1000)
    private String content;
    private float rating;

    public void findMovieAndUserAccount(Movie movie, UserAccount userAccount){
        this.movie = movie;
        this.userAccount = userAccount;
    }

    @Builder
    public Review(Long id, Movie movie, UserAccount userAccount ,String content, float rating) {
        this.id = id;
        this.movie = movie;
        this.userAccount = userAccount;
        this.content = content;
        this.rating = rating;
    }
}
