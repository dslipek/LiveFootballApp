package com.livefootballapp.api.actions;

import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.utils.GameFactoryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FinishGameActionUnitTest {

    @Test
    public void whenGameFinished_thenIsFinishedTrue() {
        // Given
        List<GameDto> games = new ArrayList<>();
        GameDto game = GameFactoryUtil.createGame("Mexico", "Canada", 2, 1);
        games.add(game);
        FinishGameAction finishGameAction = new FinishGameAction(games);

        // When
        finishGameAction.onGameFinished(game);

        // Then
        Assertions.assertTrue(game.isFinishedGame());
        Assertions.assertEquals(1, finishGameAction.getFinishedGames().size());
        Assertions.assertEquals(0, games.size());
    }

    @Test
    public void whenAllGamesFinished_thenReturnSortedList() {
        // Given
        List<GameDto> games = new ArrayList<>();
        GameDto game1 = GameFactoryUtil.createGame("Mexico", "Canada", 0, 5);
        GameDto game2 = GameFactoryUtil.createGame("Spain", "Brazil", 10, 2);
        GameDto game3 = GameFactoryUtil.createGame("Germany", "France", 2, 2);
        GameDto game4 = GameFactoryUtil.createGame("Uruguay", "Italy", 6, 6);
        GameDto game5 = GameFactoryUtil.createGame("Argentina", "Australia", 3, 1);
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);
        games.add(game5);
        FinishGameAction finishGameAction = new FinishGameAction(games);

        // When
        finishGameAction.onGameFinished(game1);
        finishGameAction.onGameFinished(game2);
        finishGameAction.onGameFinished(game3);
        finishGameAction.onGameFinished(game4);
        finishGameAction.onGameFinished(game5);

        // Then
        List<GameDto> finishedGames = finishGameAction.getSortedFinishedGames();
        Assertions.assertEquals(game1, finishedGames.get(2));
        Assertions.assertEquals(game2, finishedGames.get(1));
        Assertions.assertEquals(game3, finishedGames.get(4));
        Assertions.assertEquals(game4, finishedGames.get(0));
        Assertions.assertEquals(game5, finishedGames.get(3));
        Assertions.assertEquals(5, finishedGames.size());
        Assertions.assertEquals(0, games.size());
    }
}
