package com.tracked.api.controller;

import com.tracked.api.model.Tracklist;
import com.tracked.api.model.User;
import com.tracked.api.model.projection.ITracklist;
import com.tracked.api.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/tracklist")
@RestController
@CrossOrigin
public class TracklistController {

    private final TracklistService tracklistService;

    @Autowired
    public TracklistController(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    @GetMapping("/{id}")
    public ITracklist getTracklist(@Valid @PathVariable String id) {
        return tracklistService.get(id);
    }

    @PostMapping("/create")
    public Tracklist createTracklist(@Valid @RequestBody Tracklist tracklist, Principal principal) {
        if(principal == null) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Not authenticated, create an account first");
        }

        User firebaseUser = (User) principal;
        tracklist.setUser(firebaseUser);

        return tracklistService.create(tracklist);
    }

}
