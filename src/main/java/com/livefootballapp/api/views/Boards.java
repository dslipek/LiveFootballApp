package com.livefootballapp.api.views;

import com.livefootballapp.api.actions.FinishGameAction;
import com.livefootballapp.api.models.GameDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class Boards {

    private FinishGameAction finishGameAction;

    /**
     * Method to initialize the start counting
     *
     * @throws InterruptedException
     */
    public void initializeStartCounting() throws InterruptedException {
        for (int i = 3; i > 0; i--) {
            clearBoardAndPrintText("Game will start in " + i + " seconds");
            TimeUnit.SECONDS.sleep(1);
        }
        clearBoardAndPrintText("Game started");
    }

    /**
     * Method to get the board
     *
     * @param games
     */
    public void getBoard(List<GameDto> games) {
        clearBoardAndPrintText("Game Board");
        games.forEach(game -> System.out.println(getTeamsWithScore(game)
                + ": " + (game.isStartedGame() ? "Playing" : "Not started yet")));
    }

    /**
     * Method to get the summary board
     */
    public void getSummaryBoard() {
        clearBoardAndPrintText("Summary Board");
        finishGameAction.getSortedFinishedGames().forEach(game -> System.out.println(getTeamsWithScore(game)));
    }

    /**
     * Method to clear the board and print text
     *
     * @param text
     */
    private void clearBoardAndPrintText(String text) {
        clearBoard();
        System.out.println(text);
    }

    /**
     * Method to clear the board
     */
    private void clearBoard() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Method to get teams with score
     *
     * @param game
     * @return String
     */
    private String getTeamsWithScore(GameDto game) {
        return game.getHomeTeamDto().getName() + " " + game.getHomeTeamDto().getScore()
                + " - " + game.getAwayTeamDto().getScore() + " " + game.getAwayTeamDto().getName();
    }
}
