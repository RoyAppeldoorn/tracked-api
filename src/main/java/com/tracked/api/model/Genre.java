package com.tracked.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "GENRE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Genre {
    @Id
    private String id;

    private String genre;

    @OneToMany(mappedBy = "genre")
    private List<TracklistGenre> genres;
}
