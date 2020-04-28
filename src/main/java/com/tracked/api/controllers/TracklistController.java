package com.tracked.api.controllers;

import com.tracked.api.models.Tracklist;
import com.tracked.api.services.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/tracklist")
@RestController
@CrossOrigin
public class TracklistController {

    private final TracklistService tracklistService;

    @Autowired
    public TracklistController(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    @GetMapping(value = "/{id}")
    public Tracklist getTracklist(@Valid @PathVariable String id) {
        return tracklistService.getTracklist(id);
    }

    @PostMapping(value = "/create")
    public void createTracklist(@Valid @RequestBody Tracklist tracklist) {
        tracklistService.createTracklist(tracklist);
    }

}
