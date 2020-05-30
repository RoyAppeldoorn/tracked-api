package com.tracked.api.controller;

import com.tracked.api.model.projection.IGenre;
import com.tracked.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/genre")
@RestController
@CrossOrigin
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    public List<IGenre> getAllGenres() {
        return genreService.getAllGenres();
    }
}
