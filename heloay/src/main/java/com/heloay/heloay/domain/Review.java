package com.heloay.heloay.domain;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;
    private String content;
    private Integer voleCount;


    @Builder
    public Review(Movie movie, String content, Integer voleCount) {
        this.movie = movie;
        this.content = content;
        this.voleCount = voleCount;
    }

    public static Review createReview(Movie movie, String content, Integer voleCount){
        return Review.builder()
                .movie(movie)
                .content(content)
                .voleCount(voleCount)
                .build();
    }

}
