package com.livefootballapp.api.actions;

import com.livefootballapp.api.actions.interfaces.GameObserver;
import com.livefootballapp.api.models.GameDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinishGameAction implements GameObserver {

    private final List<GameDto> games;
    @Getter
    private final List<GameDto> finishedGames = new ArrayList<>();

    /**
     * Constructor
     *
     * @param games
     */
    public FinishGameAction(List<GameDto> games) {
        this.games = games;
    }

    /**
     * Method to finish the game
     *
     * @param gameDto
     */
    @Override
    public void onGameFinished(GameDto gameDto) {
        gameDto.setFinishedGame(true);
        finishedGames.add(gameDto);
        games.remove(gameDto);
    }

    /**
     * Method to get sorted finished games
     *
     * @return List<GameDto>
     */
    public List<GameDto> getSortedFinishedGames() {
        finishedGames.sort(Comparator
                .comparingInt(this::getSumScore)
                .reversed()
                .thenComparing(getIndexMap()::get, Comparator.reverseOrder()));
        return finishedGames;
    }

    /**
     * Method to retrieve the ending order of games
     *
     * @return Map<GameDto, Integer>
     */
    private Map<GameDto, Integer> getIndexMap() {
        return IntStream.range(0, finishedGames.size()).boxed().collect(Collectors.toMap(finishedGames::get, Function.identity()));
    }

    /**
     * Method to get the sum of scores of the game
     *
     * @param gameDto
     * @return int
     */
    private int getSumScore(GameDto gameDto) {
        return gameDto.getHomeTeamDto().getScore() + gameDto.getAwayTeamDto().getScore();
    }
}
