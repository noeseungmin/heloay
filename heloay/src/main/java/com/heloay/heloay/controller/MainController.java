package com.heloay.heloay.controller;

import com.heloay.heloay.dto.MainMovieDto;
import com.heloay.heloay.dto.MovieDto;
import com.heloay.heloay.repository.CustomMovieRepository;
import com.heloay.heloay.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CustomMovieRepository customMovieRepository;

    @GetMapping("/")
    public String main(String searchVal, Pageable pageable, Model model){
        Page<MainMovieDto> results = customMovieRepository.selectMainMovieList(searchVal, pageable);
        model.addAttribute("list", results);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", searchVal);

        pageModelPut(results, model);
        return "view/main";
    }
    private void pageModelPut(Page<MainMovieDto> results, Model model){
        model.addAttribute("totalCount", results.getTotalElements());
        model.addAttribute("size", results.getPageable().getPageSize());
        model.addAttribute("number", results.getPageable().getPageNumber());

    }


}
