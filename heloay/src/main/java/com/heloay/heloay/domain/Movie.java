package com.heloay.heloay.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Movie(String imgName, String imgUrl, String title, String content, String genres, String actor, String director, Integer runTime, String grade) {
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

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setImgUrl(String imgUrl) {
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
