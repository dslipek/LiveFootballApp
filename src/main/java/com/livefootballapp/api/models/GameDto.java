package com.livefootballapp.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class GameDto {
    private TeamDto homeTeamDto;
    private TeamDto awayTeamDto;
    private boolean startedGame;
    private boolean finishedGame;
}
