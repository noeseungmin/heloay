package com.heloay.heloay.controller;

import com.heloay.heloay.config.AccountContext;
import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.domain.Review;
import com.heloay.heloay.domain.UserAccount;
import com.heloay.heloay.dto.ReviewDto;
import com.heloay.heloay.service.MovieService;
import com.heloay.heloay.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final MovieService movieService;


    @PostMapping("movie/detail/{movieId}")
    public String reviewWrite(ReviewDto reviewDto, @PathVariable Long movieId,@AuthenticationPrincipal AccountContext accountContext){
        String username = accountContext.getUserAccount().getUsername();
        reviewService.reviewWrite(movieId, reviewDto, username);
        return "redirect:/movie/detail/" + movieId;
    }

    @PostMapping("review/{reviewId}/delete")
    public String deleteReview(@PathVariable Long reviewId, Long movieId, @AuthenticationPrincipal AccountContext accountContext){
        reviewService.deleteReview(reviewId, accountContext.getUsername());
        return "redirect:/movie/detail/" + movieId;
    }
}
