package student.adventure;

import java.util.HashMap;
import java.util.Map;

/** Game Manager class that stores and manages instances of all the games. */
public class GameManager {
  private static int id = 0;
  private static Map<Integer, Game> games = new HashMap<>();

  /** Resets the id to 0 and clears all game instances. */
  public static void reset() {
    id = 0;
    games.clear();
  }

  /**
   * Adds a new game instance to the games map.
   *
   * @param game game instance to be added
   * @return the id of the game instance added
   */
  public static int addGame(Game game) {
    games.put(++id, game);
    return id;
  }

  /**
   * Gets the game corresponding to the given id.
   *
   * @param id the id of the game to be returned
   * @return the corresponding game instance
   */
  public static Game getGame(int id) {
    if (id > 0 && id <= games.size()) {
      return games.get(id);
    }
    return null;
  }

  /**
   * Removes the game corresponding to the given id.
   *
   * @param id of the game to be removed
   * @return if game found, true, else, false
   */
  public static boolean removeGame(int id) {
    if (games.containsKey(id)) {
      games.remove(id);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the total number of instances created.
   *
   * @return the size of the games map
   */
  public static int getInstanceCount() {
    return games.size();
  }
}
