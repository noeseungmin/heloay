package com.heloay.heloay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
        name = "MOVIE_SEQ_GENERATOR", // 시퀀스 생성기 이름
        sequenceName = "MOVIE_SEQ", // 실제 DB의 시퀀스
        initialValue = 1, allocationSize = 1)
public class Movie {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "MOVIE_SEQ_GENERATOR")
    @Column(name = "movie_id")
    private Long id;

    @OneToMany(mappedBy = "movie",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("{movie}")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserAccount userAccount;
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
    public Movie(Long id, String imgName, String imgUrl, String title, String content, String genres, String actor, String director, Integer runTime, String grade) {
        this.id = id;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.title = title;
        this.content = content;
        this.genres = genres;
        this.actor = actor;
        this.director = director;
        this.runTime = runTime;
        this.grade = grade;
    }

    public void imageCreate(String imgName, String imgUrl){
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
    public void patch(Movie movie) {
        if (movie.imgUrl != null) {
            this.imgUrl = movie.imgUrl;
        }
        if (movie.imgName != null) {
            this.imgName = movie.imgName;
        }
        if (movie.title != null) {
            this.title = movie.title;
        }
        if (movie.content != null) {
            this.content = movie.content;
        }
        if (movie.director != null) {
            this.director = movie.director;
        }
        if (movie.actor != null) {
            this.actor = movie.actor;
        }
        if (movie.runTime != null) {
            this.runTime = movie.runTime;
        }
        if (movie.grade != null) {
            this.grade = movie.grade;
        }
    }
}
