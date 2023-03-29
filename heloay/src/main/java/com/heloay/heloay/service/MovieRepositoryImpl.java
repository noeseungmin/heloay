package com.heloay.heloay.service;

import com.heloay.heloay.dto.MainMovieDto;
import com.heloay.heloay.dto.MovieDto;
import com.heloay.heloay.dto.QMainMovieDto;
import com.heloay.heloay.dto.QMovieDto;
import com.heloay.heloay.repository.CustomMovieRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.heloay.heloay.domain.QMovie.movie;

@Repository
public class MovieRepositoryImpl implements CustomMovieRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MovieRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<MovieDto> selectMovieList(String searchVal, Pageable pageable) {

        List<MovieDto> content = getMovie(searchVal, pageable);
        Long count = getCount(searchVal);

        return new PageImpl<>(content, pageable, count);
    }
    @Override
    public Page<MainMovieDto> selectMainMovieList(String searchVal, Pageable pageable) {

        List<MainMovieDto> content = getMainMovieList(searchVal, pageable);
        Long count = getCount(searchVal);

        return new PageImpl<>(content, pageable, count);
    }


    private Long getCount(String searchVal){

        Long count = jpaQueryFactory.select(movie.count())
                .from(movie)
                .where(containsSearch(searchVal))
                .fetchOne();
    return count;
    }
    private List<MovieDto> getMovie(String searchVal, Pageable pageable) {
    List<MovieDto> content = jpaQueryFactory
            .select(new QMovieDto(
                    movie.id,
                    movie.title,
                    movie.runTime,
                    movie.genres))
            .from(movie)
            .where(containsSearch(searchVal))
            .orderBy(movie.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        return content;
    }

    private List<MainMovieDto> getMainMovieList(String searchVal, Pageable pageable) {
        List<MainMovieDto> content = jpaQueryFactory
                .select(new QMainMovieDto(
                        movie.id,
                        movie.title,
                        movie.genres,
                        movie.imgUrl
                ))
                .from(movie)
                .where(containsSearch(searchVal))
                .orderBy(movie.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return content;
    }

    private BooleanExpression containsSearch(String searchVal) {
        return searchVal != null ? movie.title.contains(searchVal) : null;
    }


}
