# Football World Cup Scoreboard

A Java app simulating a **Live Football World Cup Scoreboard** that shows matches and scores.

**Notes:** The current implementation uses manually set timers and time intervals for simulating the playing of the
games. Here
is a brief explanation of the intended logic:

1. When the app loads initially a 3...2...1 timer is started to visualize the start of the games
2. When the game is starting, new Thread is starting for the game, which will simulate the playing time of the game and
   update the score, after 90 seconds game will finish.
3. After the playing time ends (currently hard coded to be 90 seconds) and all the games finish, the updates are
   canceled and summary board is show.

## Tech stack

- Java
- JUnit5

## Application features:

**Live Football World Cup Scoreboard** that shows matches and scores.

The board supports the following operations:

1. Start a game. When a game starts, it should capture (being initial score 0 – 0): a. Home team b. Away team
2. Finish game. It will remove a match from the scoreboard.
3. Update score. Receiving the pair score; home team score and away team score updates a game score.
4. Get a summary of games by total score. Those games with the same total score will be returned ordered by sum of score
   and order of finish.

As an example, being the current data in the system:

    a. Mexico - Canada: 0 - 5
    b. Spain - Brazil: 10 – 2
    c. Germany - France: 2 – 2
    d. Uruguay - Italy: 6 – 6
    e. Argentina - Australia: 3 - 1

The summary would provide with the following information:

    1. Uruguay 6 - Italy 6
    2. Spain 10 - Brazil 2
    3. Mexico 0 - Canada 5
    4. Argentina 3 - Australia 1
    5. Germany 2 - France 2

### Possible Improvements

- Add a clock under the game status, say counting down the seconds, to make it look more like a real-time app
- Add some frontend to the app, so that it can be used in a more user-friendly way
- Add validation for the input data, so that the user can't enter invalid data (for example negative scores, or empty
  team names)

### Running The App locally

To run the app, follow these steps.

1. Ensure that `Maven` and `JAVA 17` is installed.
2. In terminal use command `mvn clean install`.
3. Run the main method in the `LiveFootballApp` class.