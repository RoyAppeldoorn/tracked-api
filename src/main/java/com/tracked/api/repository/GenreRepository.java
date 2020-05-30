package com.tracked.api.repository;

import com.tracked.api.model.Genre;
import com.tracked.api.model.projection.IGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, String> {
    List<IGenre> findAllGenresBy();
}
