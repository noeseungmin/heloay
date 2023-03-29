package com.heloay.heloay.dto;

import com.heloay.heloay.domain.Movie;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class MainMovieDto {

    private Long id;
    private String title;
    private String genres;
    private String imgUrl;
    

    @QueryProjection
    public MainMovieDto(Long id, String title, String genres, String imgUrl) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.imgUrl = imgUrl;
    }
}
