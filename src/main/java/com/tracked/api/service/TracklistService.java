package com.tracked.api.service;

import com.tracked.api.model.Tracklist;
import com.tracked.api.repositories.TracklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
