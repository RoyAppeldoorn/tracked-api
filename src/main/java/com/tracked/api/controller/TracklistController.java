package com.tracked.api.controller;

import com.tracked.api.model.Tracklist;
import com.tracked.api.model.User;
import com.tracked.api.model.projection.ITracklist;
import com.tracked.api.security.service.CustomUserDetailsService;
import com.tracked.api.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    @Secured("ROLE_USER")
    public ResponseEntity<String> createTracklist(@Valid @RequestBody Tracklist tracklist, @AuthenticationPrincipal User user) {
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        tracklist.setUser(user);
        String idFromNewTracklist = tracklistService.create(tracklist);
        return new ResponseEntity<>(idFromNewTracklist, HttpStatus.OK);
    }

}
