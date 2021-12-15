package student.adventure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.adventure.cli.CommandLine;
import student.adventure.schema.Item;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CommandTest {
  private Game game;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream backupOut = System.out;
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream backupErr = System.err;

  @Before
  public void setUp() {
    game = new Game();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  // ----Tests for parsing commands----

  @Test
  public void caseSensitivity() {
    assertTrue(CommandHandler.runCommand(game, "eXaMinE").contains("You are in the "));
  }

  @Test
  public void extraWhiteSpaces() {
    game.getCurrentItems().add(new Item("Key"));
    CommandHandler.runCommand(game, "     DrOp       KEY     ");
    assertEquals(new Item("Key"), game.getLayout().getRooms().get(1).getItems().get(0));
    assertEquals(0, game.getCurrentItems().size());
  }

  @Test
  public void emptyCommand() {
    assertTrue(CommandHandler.runCommand(game, "").contains("I don't understand "));
  }

  @Test
  public void nonExistentCommand() {
    assertTrue(
        CommandHandler.runCommand(game, "fly aeroplane")
            .contains("I don't understand fly aeroplane"));
  }

  @Test
  public void validCommandWithExtraWords() {
    assertTrue(CommandHandler.runCommand(game, "examine test").contains("You are in the "));
  }

  @Test(expected = NullPointerException.class)
  public void nullCommand() {
    CommandHandler.runCommand(game, null);
  }

  // ----Tests for quit commands----

  @Test
  public void quit() {
    CommandHandler.runCommand(game, "quit");
    assertEquals(false, game.getIsRunning());
  }

  @Test
  public void exit() {
    CommandHandler.runCommand(game, "exit");
    assertEquals(false, game.getIsRunning());
  }

  // ----Tests for go command----

  @Test
  public void goValidDirection() {
    assertTrue(CommandHandler.runCommand(game, "go front").contains("You are in the "));
    assertEquals(2, game.getCurrentRoomId());
  }

  @Test
  public void goInvalidDirection() {
    assertTrue(CommandHandler.runCommand(game, "go back").contains("You can't"));
  }

  @Test
  public void goWinRoom() {
    game.setCurrentRoomId(7);
    game.getCurrentItems().add(new Item("Ladder"));
    assertTrue(
        CommandHandler.runCommand(game, "go above")
            .contains("You've made "));
  }

  @Test
  public void goWinRoomWithoutLadder() {
    game.setCurrentRoomId(7);
    assertTrue(
        CommandHandler.runCommand(game, "go above")
            .contains("You need the ladder to go to the roof."));
  }

  // ----Tests for examine command----

  @Test
  public void examine() {
    assertTrue(CommandHandler.runCommand(game, "examine").contains("You are in the "));
  }

  @Test
  public void inspect() {
    assertTrue(CommandHandler.runCommand(game, "inspect").contains("You are in the "));
  }

  // ----Tests for take command----

  @Test
  public void takeValidItem() {
    game.getCurrentRoom().getItems().add(new Item("Key"));
    CommandHandler.runCommand(game, "take key");
    assertEquals(new Item("Key"), game.getCurrentItems().get(0));
    assertEquals(0, game.getCurrentRoom().getItems().size());
  }

  @Test
  public void takeNonExistentItem() {
    assertTrue(CommandHandler.runCommand(game, "take aeroplane").contains("There is no item"));
  }

  // ----Tests for drop commands----

  @Test
  public void dropUnpossessedItem() {
    assertTrue(CommandHandler.runCommand(game, "drop lock").contains("You don't have \"lock\"!"));
  }

  @Test
  public void dropDuplicateItem() {
    game.getCurrentItems().add(new Item("Key"));
    game.getLayout().getRooms().get(1).getItems().add(new Item("Key"));
    CommandHandler.runCommand(game, "drop Key");
    assertEquals(new Item("Key"), game.getLayout().getRooms().get(1).getItems().get(0));
    assertEquals(new Item("Key"), game.getLayout().getRooms().get(1).getItems().get(1));
  }

  @After
  public void restoreStreams() {
    System.setOut(backupOut);
    System.setErr(backupErr);
  }
}
