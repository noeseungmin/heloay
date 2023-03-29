package com.heloay.heloay.dto;

import com.heloay.heloay.domain.Movie;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.io.File;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDto {
    private Long id;
    private String title;
    private String content;
    private String genres;
    private String actor;
    private String director;
    private Integer runTime;
    private String grade;

    public Movie toEntity() {
        return Movie.builder()
                .id(id)
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
    public MovieDto(Long id, String title, String content, String genres, String actor, String director, Integer runTime, String grade) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.genres = genres;
        this.actor = actor;
        this.director = director;
        this.runTime = runTime;
        this.grade = grade;
    }
    @QueryProjection
    public MovieDto(Long id, String title, Integer runTime, String genres) {
        this.id = id;
        this.title = title;
        this.runTime = runTime;
        this.genres = genres;
    }
}