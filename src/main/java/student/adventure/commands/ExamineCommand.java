package student.adventure.commands;

import java.util.Map;
import student.adventure.Game;
import student.adventure.schema.Command;
import student.adventure.schema.Room;

/** Command for displaying the room description and its items. */
public class ExamineCommand extends Command {
  public ExamineCommand() {
    this.name = "examine";
    this.aliases = new String[] {"inspect"};
  }

  @Override
  public String run(Game game, String[] args) {
    return game.getCurrentRoom().roomDescription();
  }
}
