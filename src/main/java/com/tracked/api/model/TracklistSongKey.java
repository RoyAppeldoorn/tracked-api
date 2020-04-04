package com.tracked.api.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TracklistSongKey implements Serializable {

    @Column(name="tracklist_id")
    String tracklistId;

    @Column(name="song_id")
    String songId;

}
