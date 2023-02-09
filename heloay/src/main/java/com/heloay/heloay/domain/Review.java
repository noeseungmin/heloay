package com.heloay.heloay.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "review")
    private List<UserAccount> userAccounts;
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

    public void putUser(UserAccount userAccount){
        this.userAccounts.add(userAccount);
    }
}
