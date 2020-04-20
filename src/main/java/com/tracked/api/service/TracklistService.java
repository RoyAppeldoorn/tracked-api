package com.tracked.api.service;

import com.tracked.api.model.Tracklist;
import com.tracked.api.repositories.TracklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TracklistService {

    private final TracklistRepository tracklistRepository;

    @Autowired
    public TracklistService(TracklistRepository tracklistRepository) {
        this.tracklistRepository = tracklistRepository;
    }

    public Tracklist createTracklist(Tracklist tracklist) {
        return tracklistRepository.save(tracklist);
    }

    public Tracklist getTracklist(String id) {
        return tracklistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tracklist not found"));
    }
}
