package com.heloay.heloay.domain;

import com.heloay.heloay.domain.auditing.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "account_user")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {
    @Id
    @Column(length = 50)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(nullable = false)
    private String password;
    private String username;
    private int age;

    @Builder
    public UserAccount(String userId, Movie movie, Review review, String password, String username, int age) {
        this.userId = userId;
        this.movie = movie;
        this.review = review;
        this.password = password;
        this.username = username;
        this.age = age;
    }

    public static UserAccount createUser(String userId, Movie movie, Review review, String password, String username, int age){
        return UserAccount.builder()
                .userId(userId)
                .movie(movie)
                .review(review)
                .password(password)
                .username(username)
                .age(age)
                .build();
    }

}
