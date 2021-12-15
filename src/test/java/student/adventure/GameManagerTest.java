package student.adventure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameManagerTest {

  @Before
  public void setUp() {
    GameManager.reset();
  }

  @Test
  public void addNoGameInstances() {
    assertEquals(0, GameManager.getInstanceCount());
  }

  @Test
  public void addMultipleGameInstances() {
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    assertEquals(5, GameManager.getInstanceCount());
  }

  @Test
  public void resetNoGameInstances() {
    GameManager.reset();
    assertEquals(0, GameManager.getInstanceCount());
  }

  @Test
  public void resetMultipleGameInstances() {
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.addGame(new Game());
    GameManager.reset();
    assertEquals(0, GameManager.getInstanceCount());
  }
  
  @Test
  public void getValidGame() {
    Game game1 = new Game();
    Game game2 = new Game();
    Game game3 = new Game();
    GameManager.addGame(game1);
    GameManager.addGame(game2);
    GameManager.addGame(game3);
    assertEquals(game1, GameManager.getGame(1));
  }

  @Test
  public void getInvalidGame() {
    Game game1 = new Game();
    Game game2 = new Game();
    GameManager.addGame(game1);
    GameManager.addGame(game2);
    assertEquals(null, GameManager.getGame(4));
  }

  @Test
  public void removeValidGame() {
    Game game1 = new Game();
    GameManager.addGame(game1);
    assertEquals(true, GameManager.removeGame(1));
  }

  @Test
  public void removeInValidGame() {
    assertEquals(false, GameManager.removeGame(1));
  }
}
