package com.tracked.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpDto {
    @JsonProperty("nickname")
    @NotEmpty(message = "Nickname can not be empty")
    private String nickname;
}
