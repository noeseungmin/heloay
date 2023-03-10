package com.heloay.heloay.controller;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.dto.MovieDto;
import com.heloay.heloay.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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


    @GetMapping
    public String movie(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Movie> paging = this.movieService.movieList(page);
        model.addAttribute("paging", paging);
        return "view/movie";
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
