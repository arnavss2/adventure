package student.server;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A class to represent values in a game state.
 *
 * <p>Note: these fields should be JSON-serializable values, like Strings, ints, floats, doubles,
 * etc. Please don't nest objects, as the frontend won't know how to display them.
 *
 * <p>Good example: private String shoppingList;
 *
 * <p>Bad example: private ShoppingList shoppingList;
 */
@JsonSerialize
public class AdventureState {
  private int numberOfStepsLeft;

  /** Constructor for Adventure State */
  public AdventureState(int numberOfStepsLeft) {
    this.numberOfStepsLeft = numberOfStepsLeft;
  }

  public int getNumberOfStepsLeft() {
    return numberOfStepsLeft;
  }

  public void setNumberOfStepsLeft(int numberOfStepsLeft) {
    this.numberOfStepsLeft = numberOfStepsLeft;
  }
}
