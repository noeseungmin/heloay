package com.heloay.heloay.service;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.domain.Review;
import com.heloay.heloay.domain.UserAccount;
import com.heloay.heloay.dto.ReviewDto;
import com.heloay.heloay.repository.MovieRepository;
import com.heloay.heloay.repository.ReviewRepository;
import com.heloay.heloay.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MovieRepository movieRepository;
    private final UserAccountRepository userAccountRepository;
    private final ReviewRepository reviewRepository;


    @Transactional
    public Review reviewWrite(Long movieId, ReviewDto reviewDto, String username) {
        Review review = reviewDto.toEntity();
        UserAccount findUser = userAccountRepository.findByUsername(username);
        Movie findMovie = movieRepository.findById(movieId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 작성 실패 : 게시글을 찾을 수 없습니다.");
        });
        review.setMovie(findMovie);
        review.setUserAccount(findUser);
        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, String username) {
    reviewRepository.deleteByIdAndUserAccount_Username(reviewId, username);
    }
}
