package com.heloay.heloay.controller;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.dto.MovieDto;
import com.heloay.heloay.repository.CustomMovieRepository;
import com.heloay.heloay.service.MovieRepositoryImpl;
import com.heloay.heloay.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CustomMovieRepository customMovieRepository;


    @GetMapping
    public String movie(String searchVal, Pageable pageable, Model model){
        Page<MovieDto> results = customMovieRepository.selectMovieList(searchVal, pageable);
        model.addAttribute("list", results);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", searchVal);

        pageModelPut(results, model);
        return "view/movie";
    }
    private void pageModelPut(Page<MovieDto> results, Model model){
        model.addAttribute("totalCount", results.getTotalElements());
        model.addAttribute("size", results.getPageable().getPageSize());
        model.addAttribute("number", results.getPageable().getPageNumber());

    }

    @GetMapping("/{movieId}")
    public String detailMovie(@PathVariable Long movieId, Model model){
        Movie movie = movieService.findMovie(movieId);
        model.addAttribute("movie", movie);
        return "view/detail";
    }

    @GetMapping("/new")
    public String newMovie(Model model){
        model.addAttribute("movieform", new Movie());
        return "view/registration";
    }

    @PostMapping("/new")
    public String movieCreate(MovieDto dto,
                                     @RequestParam("imgUrl") MultipartFile poster) throws IOException {
        Movie movie = movieService.create(dto, poster);
        return "redirect:/movies/" + movie.getId();
    }
    @GetMapping("/{movieId}/update")
    public String movieUpdate(@PathVariable Long movieId, Model model){
        Movie movie = movieService.findMovie(movieId);
        model.addAttribute("update", movie);
        return "view/update";
    }
    @PostMapping("/{movieId}/update")
    public String movieUpdate(@PathVariable Long movieId, MovieDto dto){
        Movie movie = movieService.update(movieId, dto);
        return "redirect:/movies/" + movieId;
    }

    @PostMapping("/{movieId}/delete")
    public String delete(@PathVariable Long movieId){
        movieService.deleteMovie(movieId);

        return "redirect:/movies";
    }
}
