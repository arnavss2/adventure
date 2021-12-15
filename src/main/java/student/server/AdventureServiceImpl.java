package student.server;

import java.util.Collections;
import java.util.SortedMap;
import student.adventure.CommandOptions;
import student.adventure.Game;
import student.adventure.GameManager;

/** Implementation of the Adventure Service interface. */
public class AdventureServiceImpl implements AdventureService {

  /** Resets the service to its initial state. */
  @Override
  public void reset() {
    GameManager.reset();
  }

  /**
   * Creates a new Adventure game and stores it.
   *
   * @return the id of the game.
   */
  @Override
  public int newGame() throws AdventureException {
    return GameManager.addGame(new Game());
  }

  /**
   * Returns the state of the game instance associated with the given ID.
   *
   * @param id the instance id
   * @return the current state of the game
   */
  @Override
  public GameStatus getGame(int id) {
    Game game = GameManager.getGame(id);
    if (game == null) {
      return new GameStatus(
          true,
          id,
          "Game with id " + id + " does not exist",
          null,
          null,
          new AdventureState(0),
          Collections.emptyMap());
    }
    game.isCompleted();
    game.isLost();
    AdventureState state = new AdventureState(game.numberOfStepsLeft());
    return new GameStatus(
        false,
        id,
        game.getCurrentRoom().getDescription(),
        game.getCurrentRoom().getImageUrl(),
        null,
        state,
        CommandOptions.getCommandOptions(game));
  }

  /**
   * Removes & destroys a game instance with the given ID.
   *
   * @param id the instance id
   * @return false if the instance could not be found and/or was not deleted
   */
  @Override
  public boolean destroyGame(int id) {
    return GameManager.removeGame(id);
  }

  /**
   * Executes a command on the game instance with the given id, changing the game state if
   * applicable.
   *
   * @param id the instance id
   * @param command the issued command
   */
  @Override
  public void executeCommand(int id, Command command) {
    Game game = GameManager.getGame(id);
    game.executeCommand(command);
  }

  /**
   * Returns a sorted leaderboard of player "high" scores.
   *
   * @return a sorted map of player names to scores
   */
  @Override
  public SortedMap<String, Integer> fetchLeaderboard() {
    return null;
  }
}
