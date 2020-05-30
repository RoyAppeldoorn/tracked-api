package com.tracked.api.service;

import com.tracked.api.model.projection.IGenre;
import com.tracked.api.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<IGenre> getAllGenres() {
        return genreRepository.findAllGenresBy();
    }
}
