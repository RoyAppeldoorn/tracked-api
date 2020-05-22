package com.tracked.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TracklistDto {
    @JsonProperty("id")
    private String id;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @NotBlank
    @JsonProperty("embed_url")
    private String embed_url;
}
