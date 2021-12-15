package student.adventure.cli;

import static student.adventure.CommandHandler.runCommand;

import java.util.Scanner;
import student.adventure.CommandHandler;
import student.adventure.Game;
import student.adventure.GameManager;

/** CommandLine class for executing the game on the command line. */
public class CommandLine {
  private Game game;

  public Game getGame() {
    return game;
  }

  /** Starts the game on the command line. */
  public void startGame() {
    game = new Game();
    int id = GameManager.addGame(game);
    System.out.println(CommandHandler.runCommand(game, "examine"));
    while (game.getIsRunning()) {
      if (game.isLost() || game.isCompleted()) {
        game.setIsRunning(false);
      }
      acceptAndRunCommands(id);
    }
  }

  /** Accepts input from the cli and runs the appropriate command. */
  public void acceptAndRunCommands(int id) {
    Scanner sc = new Scanner(System.in);
    System.out.print("> ");
    String command = sc.nextLine();
    System.out.println(runCommand(GameManager.getGame(id), command));
  }
}
