package student.adventure;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class JsonTest {
  private Game game;

  @Before
  public void setUp() {
    game = new Game();
  }

  @Test
  public void fileNotFound() {
    assertTrue(!game.initializeGson("src/main/resources/siebel.bat"));
  }

  @Test
  public void fileCannotBeRead() {
    assertTrue(!game.initializeGson("src/main/resources/siebel.f"));
  }

  @Test
  public void invalidJsonSyntax() {
    assertTrue(!game.initializeGson("src/main/resources/invalid_adventure_syntax.json"));
  }

  @Test
  public void invalidJsonSchema() {
    assertTrue(!game.initializeGson("src/main/resources/invalid_adventure_schema.json"));
  }

  @Test
  public void invalidJsonField() {
    assertTrue(!game.initializeGson("src/main/resources/invalid_adventure_field.json"));
  }

  @Test
  public void nullJsonFile() {
    assertTrue(!game.initializeGson(null));
  }
}
