package student.adventure;

import student.adventure.schema.Command;

import java.util.HashMap;
import java.util.Map;

/** Stores, handles and runs commands. */
public class CommandHandler {
  private static Map<String, Command> commands = new HashMap<>();
  private static HashMap<String, Command> commandsWithAliases = new HashMap<>();


  /**
   * Adds a command to the handler.
   *
   * @param command Command to be added to the list
   */
  public static void addCommand(Command command) {
    commandsWithAliases.put(command.getName().toLowerCase(), command);
    commands.put(command.getName().toLowerCase(), command);
    for (String alias : command.getAliases()) {
      commandsWithAliases.put(alias.toLowerCase(), command);
    }
  }

  /**
   * Runs a command given its full input by the user.
   *
   * @param fullCommand full command text including args
   */
  public static String runCommand(Game game, String fullCommand) {
    if (game == null) {
      throw new NullPointerException("Game is null.");
    }
    if (fullCommand == null) {
      throw new NullPointerException("Enter a non-null command!");
    }
    fullCommand = fullCommand.trim().replaceAll("\\s+", " ").toLowerCase();
    String[] args = fullCommand.split(" ");
    if (commandsWithAliases.containsKey(args[0].toLowerCase())) {
      return commandsWithAliases.get(args[0]).run(game, args);
    } else {
      return "I don't understand " + fullCommand;
    }
  }
}
