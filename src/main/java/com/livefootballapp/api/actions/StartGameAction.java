package com.livefootballapp.api.actions;

import com.livefootballapp.api.actions.interfaces.GameObserver;
import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.utils.RandomIntUtil;

import java.util.ArrayList;
import java.util.List;

public class StartGameAction {

    private final List<GameDto> games;
    private final FinishGameAction finishGameAction;

    /**
     * Constructor
     *
     * @param games
     * @param finishGameAction
     */
    public StartGameAction(List<GameDto> games, FinishGameAction finishGameAction) {
        this.games = games;
        this.finishGameAction = finishGameAction;
    }

    /**
     * Method to start the game
     */
    public void startGame() {
        GameDto game = games.get(RandomIntUtil.getRandomInt(5));
        if (!game.isStartedGame()) {
            game.setStartedGame(true);
            startUpdateAction(game);
        }
    }

    /**
     * Method to start the update action
     *
     * @param game
     */
    private void startUpdateAction(GameDto game) {
        List<GameObserver> observers = new ArrayList<>();
        observers.add(finishGameAction);
        UpdateGameAction updateGameAction = new UpdateGameAction(game, observers);
        new Thread(updateGameAction).start();
    }

}
