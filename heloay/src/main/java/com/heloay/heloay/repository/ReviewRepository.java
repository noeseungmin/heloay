package com.heloay.heloay.repository;

import com.heloay.heloay.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    void deleteByIdAndUserAccount_Username(Long reviewId, String Username);
}
