package com.tracked.api.repository;

import com.tracked.api.model.Tracklist;
import com.tracked.api.model.projection.ITracklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TracklistRepository extends JpaRepository<Tracklist, String> {
    <T> List<T> findAllBy(Class<T> clazz);

    <T> Optional<T> findById(String id, Class<T> clazz);
}
