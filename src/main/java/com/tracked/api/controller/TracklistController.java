package com.tracked.api.controller;

import com.tracked.api.model.Tracklist;
import com.tracked.api.service.TracklistService;
import com.tracked.api.utils.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/tracklist")
@RestController
@CrossOrigin
public class TracklistController {

    private final TracklistService tracklistService;

    @Autowired
    public TracklistController(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    @PostMapping(value = "/create")
    public void createPlayer(@Valid @RequestBody Tracklist tracklist) {
        tracklistService.createTracklist(tracklist);
    }

}
