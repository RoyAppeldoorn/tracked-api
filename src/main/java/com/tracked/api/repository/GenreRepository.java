package com.tracked.api.repository;

import com.tracked.api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, String> {

    List<Genre> findAllBy();

}
