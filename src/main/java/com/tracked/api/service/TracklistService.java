package com.tracked.api.service;

import com.tracked.api.model.Tracklist;
import com.tracked.api.model.projection.ITracklist;
import com.tracked.api.repository.GenreRepository;
import com.tracked.api.repository.TracklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TracklistService {

    private final TracklistRepository tracklistRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public TracklistService(TracklistRepository tracklistRepository, GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.tracklistRepository = tracklistRepository;
    }

    public String create(Tracklist tracklist) {
        Tracklist newTracklist = tracklistRepository.saveAndFlush(tracklist);
        return newTracklist.getId();
    }

    public ITracklist get(String id) {
        return tracklistRepository.findById(id, ITracklist.class)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tracklist not found"));
    }
}
