package com.tracked.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TracklistSong {

    @EmbeddedId
    private TracklistSongKey id;

    @ManyToOne
    @MapsId("tracklist_id")
    @JoinColumn(name = "tracklist_id")
    private Tracklist tracklist;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name = "song_id")
    private Song song;

    private String from;

    private String till;
}
