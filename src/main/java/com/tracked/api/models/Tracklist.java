package com.tracked.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @JsonProperty("embed_url")
    private String embed_url;

    @OneToMany(mappedBy = "tracklist")
    private List<TracklistSong> songs;
}
