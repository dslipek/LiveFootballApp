package com.livefootballapp.api.actions;

import com.livefootballapp.api.actions.interfaces.GameObserver;
import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.models.TeamDto;
import com.livefootballapp.api.utils.RandomIntUtil;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class UpdateGameAction implements Runnable {

    private final GameDto gameDto;
    private final List<GameObserver> observers;

    /**
     * Constructor
     *
     * @param gameDto
     * @param observers
     */
    public UpdateGameAction(GameDto gameDto, List<GameObserver> observers) {
        this.gameDto = gameDto;
        this.observers = observers;
    }

    /**
     * Method to update the game
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            updateScoreForRandomTeam();
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                log.error("Error while updating game", e);
            }
        }
        notifyObservers();
    }

    /**
     * Check game is not finished and random score equals 1 then update score for random team
     */
    protected void updateScoreForRandomTeam() {
        if (!gameDto.isFinishedGame() && RandomIntUtil.getRandomInt(2) == 1) {
            TeamDto teamDto = RandomIntUtil.getRandomInt(2) == 0 ? gameDto.getHomeTeamDto() : gameDto.getAwayTeamDto();
            teamDto.setScore(teamDto.getScore() + 1);
        }
    }

    /**
     * When game is finished notify observers to finish game
     */
    private void notifyObservers() {
        observers.forEach(observer -> observer.onGameFinished(gameDto));
    }
}
