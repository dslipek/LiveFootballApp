package com.livefootballapp.api;

import com.livefootballapp.api.actions.FinishGameAction;
import com.livefootballapp.api.actions.StartGameAction;
import com.livefootballapp.api.data.Data;
import com.livefootballapp.api.models.GameDto;
import com.livefootballapp.api.views.Boards;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class LiveFootballApp {

    private static final List<GameDto> games = Data.games;

    /**
     * Main method
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        if (games.isEmpty()) {
            log.error("No games available");
            throw new RuntimeException("No games available");
        }
        FinishGameAction finishGameAction = new FinishGameAction(games);
        runGame(new Boards(finishGameAction), new StartGameAction(games, finishGameAction));
    }

    /**
     * Method to run the game
     *
     * @param boards
     * @param startGameAction
     * @throws InterruptedException
     */
    private static void runGame(Boards boards, StartGameAction startGameAction) throws InterruptedException {
        boards.initializeStartCounting();
        while (true) {
            if (!games.stream().allMatch(GameDto::isStartedGame)) {
                startGameAction.startGame();
            }
            if (games.stream().allMatch(GameDto::isFinishedGame)) {
                boards.getSummaryBoard();
                break;
            }
            boards.getBoard(games);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}