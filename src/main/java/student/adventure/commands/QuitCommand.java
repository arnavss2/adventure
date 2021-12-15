package student.adventure.commands;

import student.adventure.Game;
import student.adventure.cli.CommandLine;
import student.adventure.schema.Command;

/** Command to quit the game. */
public class QuitCommand extends Command {
  public QuitCommand() {
    this.name = "quit";
    this.aliases = new String[] {"exit"};
  }

  @Override
  public String run(Game game, String[] args) {
    game.setIsRunning(false);
    return "";
  }
}
