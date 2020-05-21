package com.tracked.api.service.impl;

import com.tracked.api.model.Tracklist;
import com.tracked.api.repository.TracklistRepository;
import com.tracked.api.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TracklistServiceImpl {

    private final TracklistRepository tracklistRepository;

    @Autowired
    public TracklistServiceImpl(TracklistRepository tracklistRepository) {
        this.tracklistRepository = tracklistRepository;
    }

    public void create(Tracklist tracklist) {
        tracklistRepository.save(tracklist);
    }

    public Tracklist get(String id) {
        return tracklistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tracklist not found"));
    }
}
