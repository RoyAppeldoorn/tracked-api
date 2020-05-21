package com.tracked.api.service;

import com.tracked.api.model.Tracklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracklistService extends JpaRepository<Tracklist, String> {

}
