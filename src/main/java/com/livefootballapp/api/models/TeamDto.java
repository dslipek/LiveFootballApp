package com.livefootballapp.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class TeamDto {
    public String name;
    public int score;
}
