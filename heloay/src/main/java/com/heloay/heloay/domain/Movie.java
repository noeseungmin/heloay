package com.heloay.heloay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MoviesMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    private String imgName;
    private String imgUrl;

    private String title;
    private String content;
    private String genres;
    private String actor;
    private String director;
    private Integer runTime;
    private String grade;

    @Builder
    public MoviesMovie(Review review, Long id,String title, String content, String genres, String actor) {
        this.id = id;
        this.review = review;
        this.title = title;
        this.content = content;
        this.genres = genres;
        this.actor = actor;
    }
}
