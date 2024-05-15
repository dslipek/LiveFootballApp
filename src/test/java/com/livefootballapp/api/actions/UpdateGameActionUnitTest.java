package com.livefootballapp.api.actions;

import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.utils.GameFactoryUtil;
import com.livefootballapp.api.utils.RandomIntUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

public class UpdateGameActionUnitTest {

    @Test
    public void whenUpdateGameActionIsStarted_thenGameIsUpdated() {
        // given
        GameDto gameDto = GameFactoryUtil.createGame("Mexico", "Canada", 0, 0, true, false);
        UpdateGameAction updateGameAction = new UpdateGameAction(gameDto, new ArrayList<>());

        // when
        try (MockedStatic<RandomIntUtil> utilities = Mockito.mockStatic(RandomIntUtil.class)) {
            utilities.when(() -> RandomIntUtil.getRandomInt(2)).thenReturn(1);
            updateGameAction.updateScoreForRandomTeam();
            Assertions.assertEquals(1, gameDto.getAwayTeamDto().getScore());
            Assertions.assertEquals(0, gameDto.getHomeTeamDto().getScore());
        }
    }

    @Test
    public void whenUpdateGameActionIsStartedAndFinished_thenGameIsNotUpdated() {
        // given
        GameDto gameDto = GameFactoryUtil.createGame("Mexico", "Canada", 0, 0, true, true);
        UpdateGameAction updateGameAction = new UpdateGameAction(gameDto, new ArrayList<>());

        // when
        try (MockedStatic<RandomIntUtil> utilities = Mockito.mockStatic(RandomIntUtil.class)) {
            utilities.when(() -> RandomIntUtil.getRandomInt(2)).thenReturn(1);
            updateGameAction.updateScoreForRandomTeam();
            Assertions.assertEquals(0, gameDto.getAwayTeamDto().getScore());
            Assertions.assertEquals(0, gameDto.getHomeTeamDto().getScore());
        }
    }
}
