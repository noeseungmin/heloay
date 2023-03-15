package com.heloay.heloay.dto;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.domain.Review;
import com.heloay.heloay.domain.UserAccount;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private Movie movie;
    private UserAccount userAccount;
    private String content;
    private float rating;

    public Review toEntity(){
       return Review.builder()
                .movie(movie)
                .userAccount(userAccount)
                .content(content)
                .rating(rating)
                .build();
    }
    @Builder
    public ReviewDto(Long id, String content, float rating) {
        this.id = id;
        this.content = content;
        this.rating = rating;
    }

}
