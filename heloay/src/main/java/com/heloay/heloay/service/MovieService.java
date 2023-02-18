package com.heloay.heloay.service;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.dto.MovieDto;
import com.heloay.heloay.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public Page<Movie> movieList(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return this.movieRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Movie findMovie(Long movieId){
        return movieRepository.findById(movieId).orElse(null);
    }

    @Transactional
    public Movie create(MovieDto dto, MultipartFile file) throws IOException {
        Movie movie = dto.toEntity();
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\movieposters";

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);


        movie.setImgUrl("/movieposters/"+ fileName);
        movie.setImgName(fileName);

        return movieRepository.save(movie);
    }

    public Movie update(Long movieId, MovieDto dto) {
        Movie movie = dto.toEntity();
        Movie movieUpdate = movieRepository.findById(movieId).orElse(null);

        if(movieUpdate == null){
            return null;
        }
        movieUpdate.patch(movie);
        return movieRepository.save(movieUpdate);
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}