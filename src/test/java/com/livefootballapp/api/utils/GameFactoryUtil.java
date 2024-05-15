package com.livefootballapp.api.utils;

import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.models.TeamDto;

public class GameFactoryUtil {

    public static GameDto createGame(String homeTeam, String awayTeam, int homeScore, int awayScore, boolean startedGame, boolean finishedGame) {
        return GameDto.builder()
                .homeTeamDto(TeamDto.builder().name(homeTeam).score(homeScore).build())
                .awayTeamDto(TeamDto.builder().name(awayTeam).score(awayScore).build())
                .startedGame(startedGame)
                .finishedGame(finishedGame)
                .build();
    }

    public static GameDto createGame(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return createGame(homeTeam, awayTeam, homeScore, awayScore, true, true);
    }
}
