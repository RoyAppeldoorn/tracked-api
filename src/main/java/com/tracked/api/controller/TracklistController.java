package com.tracked.api.controller;

import com.tracked.api.model.Tracklist;
import com.tracked.api.service.impl.TracklistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/tracklist")
@RestController
@CrossOrigin
public class TracklistController {

    private final TracklistServiceImpl tracklistServiceImpl;

    @Autowired
    public TracklistController(TracklistServiceImpl tracklistServiceImpl) {
        this.tracklistServiceImpl = tracklistServiceImpl;
    }

    @GetMapping("/{id}")
    public Tracklist getTracklist(@Valid @PathVariable String id) {
        return tracklistServiceImpl.get(id);
    }

    @PostMapping("/create")
    public void createTracklist(@Valid @RequestBody Tracklist tracklist) {
        tracklistServiceImpl.create(tracklist);
    }

}
