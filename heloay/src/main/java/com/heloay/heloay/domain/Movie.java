package com.heloay.heloay.domain;

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

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private List<UserAccount> userAccounts;
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


    public void putReview(Review review){
        this.reviews.add(review);
    }
}
