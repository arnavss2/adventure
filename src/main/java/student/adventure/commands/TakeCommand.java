package student.adventure.commands;

import student.adventure.Game;
import student.adventure.schema.Command;
import student.adventure.schema.Item;

/** Commands to take item from a room. */
public class TakeCommand extends Command {

  public TakeCommand() {
    this.name = "take";
  }

  @Override
  public String run(Game game, String[] args) {
    Item item = new Item(args[1].toUpperCase());
    if (game.getCurrentRoom().getItems().contains(item)) {
      game.getCurrentItems().add(0, item);
      game.getCurrentRoom().getItems().remove(item);
      return "You picked up \"" + item.toString() + "\".";
    } else {
      return "There is no item \"" + item.toString() + "\" in the room.";
    }
  }
}
