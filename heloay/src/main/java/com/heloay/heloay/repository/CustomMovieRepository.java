package com.heloay.heloay.repository;

import com.heloay.heloay.dto.MainMovieDto;
import com.heloay.heloay.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomMovieRepository {
    Page<MovieDto> selectMovieList(String searchVal, Pageable pageable);

    Page<MainMovieDto> selectMainMovieList(String searchVal, Pageable pageable);
}
