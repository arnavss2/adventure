package student.adventure.schema;

import student.adventure.Game;

/** Abstract class schema for the CLI commands. */
public abstract class Command {
  protected String name;
  protected String[] aliases = new String[] {};

  public String getName() {
    return name;
  }

  public String[] getAliases() {
    return aliases;
  }


  /**
   * Abstract method containing the implementation for each command. Is called by the CommandHandler
   * when the user runs the command.
   *
   * @param args Full command entered by the user, including the command name
   */
  public abstract String run(Game game, String[] args);
}
