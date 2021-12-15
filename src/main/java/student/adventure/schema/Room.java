package student.adventure.schema;

import java.util.List;

/** This class serves as the room class for representing the rooms of the adventure game. */
public class Room {
  private String name;
  private String description;
  private List<Direction> directions;
  private List<Item> items;
  private String imageUrl;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<Direction> getDirections() {
    return directions;
  }

  public List<Item> getItems() {
    return items;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Prints the room description, possible directions, and available items.
   *
   * @return the room description of the room object
   */
  public String roomDescription() {
    String roomDesc = "\n" + getDescription() + "\nFrom here, you can go: ";
    List<Direction> directions = getDirections();
    for (int index = 0; index < directions.size() - 1; index++) {
      roomDesc += "" + directions.get(index).getDirectionName() + ", ";
    }
    if (directions.size() > 0) {
      roomDesc +=
          "" + directions.get(directions.size() - 1).getDirectionName() + "\nItems visible: ";
    }
    List<Item> items = getItems();
    for (int index = 0; index < items.size() - 1; index++) {
      roomDesc += "" + items.get(index).getItemName() + ", ";
    }
    if (items.size() > 0) {
      roomDesc += "" + items.get(items.size() - 1).getItemName();
    }
    return roomDesc;
  }
}
