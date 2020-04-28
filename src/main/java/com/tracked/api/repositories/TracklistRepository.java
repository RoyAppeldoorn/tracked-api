package com.tracked.api.repositories;

import com.tracked.api.models.Tracklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TracklistRepository extends JpaRepository<Tracklist, String> {
}
