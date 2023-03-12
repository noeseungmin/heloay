package com.heloay.heloay.controller;

import com.heloay.heloay.dto.ReviewDto;
import com.heloay.heloay.dto.security.AccountContext;
import com.heloay.heloay.service.MovieService;
import com.heloay.heloay.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}")
    public String reviewWrite(ReviewDto reviewDto, @PathVariable Long movieId, @AuthenticationPrincipal AccountContext accountContext){
        String username = accountContext.getUserAccount().getUsername();
        reviewService.reviewWrite(movieId, reviewDto, username);
        return "redirect:/movies/" + movieId;
    }

    @PostMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Long reviewId, Long movieId,
                               @AuthenticationPrincipal AccountContext accountContext){
        reviewService.deleteReview(reviewId, accountContext.getUsername());
        return "redirect:/movies/" + movieId;
    }
}
