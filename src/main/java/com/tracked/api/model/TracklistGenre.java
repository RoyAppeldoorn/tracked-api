package com.tracked.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TracklistGenre {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "tracklist_id")
    private Tracklist tracklist;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
