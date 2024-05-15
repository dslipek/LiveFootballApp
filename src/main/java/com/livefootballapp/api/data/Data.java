package com.livefootballapp.api.data;

import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.models.TeamDto;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<GameDto> games = new ArrayList<>();

    static {
        games.add(createGame("Mexico", "Canada"));
        games.add(createGame("Spain", "Brazil"));
        games.add(createGame("Germany", "France"));
        games.add(createGame("Uruguay", "Italy"));
        games.add(createGame("Argentina", "Australia"));
    }

    /**
     * Method to create a game
     *
     * @param homeTeam
     * @param awayTeam
     * @return GameDto
     */
    private static GameDto createGame(String homeTeam, String awayTeam) {
        return GameDto.builder()
                .homeTeamDto(createTeam(homeTeam))
                .awayTeamDto(createTeam(awayTeam))
                .startedGame(false)
                .finishedGame(false)
                .build();
    }

    /**
     * Method to create a team
     *
     * @param name
     * @return TeamDto
     */
    private static TeamDto createTeam(String name) {
        return TeamDto.builder()
                .name(name)
                .score(0)
                .build();
    }
}
