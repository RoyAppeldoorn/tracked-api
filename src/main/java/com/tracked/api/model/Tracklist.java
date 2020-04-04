package com.tracked.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tracklist {
    @Id
    private String tracklist_id;

    private String title;

    @ManyToMany
    private List<TracklistSong> tracklist_songs;
}
