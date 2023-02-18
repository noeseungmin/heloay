package com.heloay.heloay.controller;

import com.heloay.heloay.domain.Movie;
import com.heloay.heloay.service.MovieService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MovieService movieService;

    @GetMapping("main")
    public String movie(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Movie> paging = this.movieService.movieList(page);
        model.addAttribute("paging", paging);
        return "view/main";
    }
}
