package student.adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import student.adventure.schema.Direction;
import student.adventure.schema.Item;

/** CommandOptions class for all the possible commands corresponding to the given game instance. */
public class CommandOptions {
  private static Map<String, List<String>> commandOptions = new HashMap<>();

  /**
   * Gets the command options for the given game state.
   *
   * @param game the game whose command options are to be returned
   * @return the command options corresponding to the current game state
   */
  public static Map<String, List<String>> getCommandOptions(Game game) {
    addGo(game);
    addTake(game);
    addDrop(game);
    return commandOptions;
  }

  /**
   * Adds the possible go commands.
   *
   * @param game the current game instance
   */
  private static void addGo(Game game) {
    List<String> possibleDirections = new ArrayList<>();
    List<Direction> directions = game.getCurrentRoom().getDirections();
    for (Direction direction : directions) {
      possibleDirections.add(direction.getDirectionName());
    }
    commandOptions.put("go", possibleDirections);
  }

  /**
   * Adds the possible take commands.
   *
   * @param game the current game instance
   */
  private static void addTake(Game game) {
    List<String> itemNames = new ArrayList<>();
    List<Item> items = game.getCurrentRoom().getItems();
    for (Item item : items) {
      itemNames.add(item.getItemName());
    }
    commandOptions.put("take", itemNames);
  }

  /**
   * Adds the possible drop commands.
   *
   * @param game the current game instance
   */
  private static void addDrop(Game game) {
    List<String> itemNames = new ArrayList<>();
    List<Item> items = game.getCurrentItems();
    for (Item item : items) {
      itemNames.add(item.getItemName());
    }
    commandOptions.put("drop", itemNames);
  }
}
