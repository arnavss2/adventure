package student.adventure;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import student.adventure.commands.DropCommand;
import student.adventure.commands.ExamineCommand;
import student.adventure.commands.GoCommand;
import student.adventure.commands.QuitCommand;
import student.adventure.commands.TakeCommand;
import student.adventure.schema.Item;
import student.adventure.schema.Layout;
import student.adventure.schema.Room;
import student.server.Command;

/** This class serves as the main game class where the game is initialized. */
public class Game {
  private boolean isRunning = true;
  private Layout layout;
  private int currentRoomId;
  private List<Item> currentItems = new ArrayList<>();
  private int currentSteps = 0;

  public Game() {
    start();
  }

  public boolean getIsRunning() {
    return isRunning;
  }

  public Layout getLayout() {
    return layout;
  }

  public int getCurrentRoomId() {
    return currentRoomId;
  }

  public List<Item> getCurrentItems() {
    return currentItems;
  }

  public void setIsRunning(boolean setIsRunning) {
    isRunning = setIsRunning;
  }

  public void setCurrentRoomId(int newRoomId) {
    currentRoomId = newRoomId;
  }

  /** Starts the game in the command line. */
  public void start() {
    if (initializeGson("src/main/resources/adventure.json")) {
      initializeCommands();
      assignLadder();
      currentRoomId = layout.getStartingRoomId();
      CommandHandler.runCommand(this, "examine");
    }
  }

  /**
   * This method initializes the Gson for the game to begin.
   *
   * @param filePath the String for the file's path
   */
  public boolean initializeGson(String filePath) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        throw new IOException("The file was not found.");
      }
      if (!file.canRead()) {
        throw new IOException("The file can't be read.");
      }
      Scanner sc = null;
      sc = new Scanner(file);
      sc.useDelimiter("\\Z");
      String myJson = sc.next();
      Gson gson = new Gson();
      layout = gson.fromJson(myJson, Layout.class);
      layout.checkLayout();
      return true;
    } catch (JsonSyntaxException jsonSyntaxException) {
      System.err.println("JSON syntax is incorrect!");
      return false;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  /** Initializes commands in the CommandHandler. Add new commands here to register them */
  public void initializeCommands() {
    CommandHandler.addCommand(new ExamineCommand());
    CommandHandler.addCommand(new QuitCommand());
    CommandHandler.addCommand(new DropCommand());
    CommandHandler.addCommand(new GoCommand());
    CommandHandler.addCommand(new TakeCommand());
  }

  /** Assigns the item "Ladder" to a random room. */
  private void assignLadder() {
    Random random = new Random();
    int ladderRoomId = random.nextInt(layout.getRooms().size() - 2) + 1;
    layout.getRooms().get(ladderRoomId).getItems().add(new Item("Ladder"));
  }

  /** Executes the command by passing it to the CommandHandler. */
  public void executeCommand(Command command) {
    CommandHandler.runCommand(this, command.getCommandName() + " " + command.getCommandValue());
  }

  /**
   * Checks if the game is complete.
   *
   * @return true, if complete, else, false
   */
  public boolean isCompleted() {
    if (currentItems.contains(new Item("Ball"))) {
      currentRoomId = 0;
      return true;
    }
    return false;
  }

  /** Increases current step by 1. */
  public void increaseCurrentSteps() {
    currentSteps++;
  }

  /**
   * Calculates the number of steps the game has left.
   *
   * @return the number of steps left
   */
  public int numberOfStepsLeft() {
    return layout.getMaxSteps() - currentSteps;
  }

  /**
   * Checks if the game has been lost if there are no steps left.
   *
   * @return true, if lost, else, false
   */
  public boolean isLost() {
    if (numberOfStepsLeft() < 1) {
      currentRoomId = -1;
      return true;
    }
    return false;
  }

  /**
   * Returns the current room the user is in.
   *
   * @return current room
   */
  public Room getCurrentRoom() {
    return layout.getRooms().get(currentRoomId);
  }
}
