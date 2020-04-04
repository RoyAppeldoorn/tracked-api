package com.tracked.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tracklist {
    @Id
    @NotNull
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String embed_url;

    @OneToMany(mappedBy = "tracklist")
    private List<TracklistSong> songs;
}
