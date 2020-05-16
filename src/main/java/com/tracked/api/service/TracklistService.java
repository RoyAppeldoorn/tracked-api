package com.tracked.api.service;

import com.tracked.api.model.Tracklist;

public interface TracklistService {

    void create(Tracklist tracklist);

    Tracklist get(String uuid);

}
