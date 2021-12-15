package student.adventure.commands;

import java.util.List;
import student.adventure.CommandHandler;
import student.adventure.Game;
import student.adventure.schema.Command;
import student.adventure.schema.Direction;
import student.adventure.schema.Item;
import student.adventure.schema.PossibleDirections;

/** Command to move the player across the house. */
public class GoCommand extends Command {
  public GoCommand() {
    super();
    this.name = "go";
  }

  /**
   * Method containing the implementation for go command. Is called by the CommandHandler
   * when the user runs the command.
   *
   * @param args Full command entered by the user, including the command name
   */
  @Override
  public String run(Game game, String[] args) {
    try {
      PossibleDirections goDirection = PossibleDirections.valueOf(args[1].toUpperCase());
      List<Direction> directionList = game.getCurrentRoom().getDirections();
      int newRoomId = 0;
      for (Direction direction : directionList) {
        if (direction.getDirectionName().toUpperCase().equals(goDirection.toString())) {
          newRoomId = direction.getRoomId();
        }
      }
      if (newRoomId == 0) {
        throw new IllegalArgumentException();
      } else {
        return changeRoom(game, newRoomId);
      }
    } catch (IllegalArgumentException e) {
      return "You can't go \"" + args[1] + "\"!";
    }
  }

  /**
   * Changes the current room to the new room corresponding to the newRoomId.
   *
   * @param game the game instance whose room needs to be changed
   * @param newRoomId id of the new room
   * @return the string with the new room description or message
   */
  private String changeRoom(Game game, int newRoomId) {
    if (newRoomId == game.getLayout().getEndingRoomId()) {
      if (game.getCurrentItems().contains(new Item("Ladder"))) {
        game.setCurrentRoomId(newRoomId);
        game.increaseCurrentSteps();
        return CommandHandler.runCommand(game, "examine");
      } else {
        return "You need the ladder to go to the roof.";
      }
    } else {
      game.setCurrentRoomId(newRoomId);
      game.increaseCurrentSteps();
      return CommandHandler.runCommand(game, "examine");
    }
  }
}
