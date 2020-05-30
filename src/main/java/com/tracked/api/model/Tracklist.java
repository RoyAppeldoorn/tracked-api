package com.tracked.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tracklist {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @NotBlank
    @JsonProperty("url")
    private String url;

    @OneToMany(mappedBy = "tracklist")
    private List<TracklistSong> songs;

    @OneToMany(mappedBy = "tracklist")
    private List<TracklistGenre> genres;

    @ManyToOne
    private User user;
}
