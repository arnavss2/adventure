package student.adventure.commands;

import student.adventure.Game;
import student.adventure.schema.Command;
import student.adventure.schema.Item;

/** Command for dropping an item from the current items a player has. */
public class DropCommand extends Command {
  public DropCommand() {
    super();
    this.name = "drop";
  }

  @Override
  public String run(Game game, String[] args) {
    Item item = new Item(args[1]);
    if (game.getCurrentItems().contains(item)) {
      game.getCurrentItems().remove(item);
      game.getCurrentRoom().getItems().add(0, item);
      return "";
    } else {
      return "You don't have \"" + item.toString() + "\"!";
    }
  }
}
