package com.heloay.heloay.dto;

import com.heloay.heloay.domain.Movie;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDto {
    private String imgUrl;
    private String imgName;
    private String title;
    private String content;
    private String genres;
    private String actor;
    private String director;
    private Integer runTime;
    private String grade;

    public Movie toEntity() {
        return Movie.builder()
                .imgName(imgName)
                .imgUrl(imgUrl)
                .title(title)
                .content(content)
                .genres(genres)
                .actor(actor)
                .director(director)
                .runTime(runTime)
                .grade(grade)
                .build();
    }

    @Builder
    public MovieDto(String title, String content, String genres, String actor, String director, Integer runTime, String grade) {
        this.title = title;
        this.content = content;
        this.genres = genres;
        this.actor = actor;
        this.director = director;
        this.runTime = runTime;
        this.grade = grade;
    }
}