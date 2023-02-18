package com.heloay.heloay.domain;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Builder
    public Review(Movie movie, UserAccount userAccount ,String content, float rating) {
        this.movie = movie;
        this.userAccount = userAccount;
        this.content = content;
        this.rating = rating;
    }
}
