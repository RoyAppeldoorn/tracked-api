package com.tracked.api.repository;

import com.tracked.api.model.Tracklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TracklistRepository extends JpaRepository<Tracklist, String> {
}
