package com.livefootballapp.api.actions;

import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.utils.GameFactoryUtil;
import com.livefootballapp.api.utils.RandomIntUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class StartGameActionUnitTest {

    @Test
    public void whenStartGame_thenGameIsStarted() {
        // Given
        List<GameDto> games = Arrays.asList(
                GameFactoryUtil.createGame("Mexico", "Canada", 2, 1, false, false),
                GameFactoryUtil.createGame("Spain", "Brazil", 3, 0, false, false),
                GameFactoryUtil.createGame("Germany", "France", 1, 1, false, false),
                GameFactoryUtil.createGame("Uruguay", "Italy", 0, 0, false, false),
                GameFactoryUtil.createGame("Argentina", "Australia", 4, 3, false, false));
        FinishGameAction finishGameAction = new FinishGameAction(games);
        StartGameAction startGameAction = new StartGameAction(games, finishGameAction);

        // When
        try (MockedStatic<RandomIntUtil> utilities = Mockito.mockStatic(RandomIntUtil.class)) {
            utilities.when(() -> RandomIntUtil.getRandomInt(5))
                    .thenReturn(0);

            startGameAction.startGame();

            // Then
            Assertions.assertTrue(games.get(0).isStartedGame());
            Assertions.assertFalse(games.get(1).isStartedGame());
            Assertions.assertFalse(games.get(2).isStartedGame());
            Assertions.assertFalse(games.get(3).isStartedGame());
            Assertions.assertFalse(games.get(4).isStartedGame());
        }
    }
}
