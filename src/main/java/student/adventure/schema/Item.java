package student.adventure.schema;

import java.util.Objects;

/** The item class for the items that can be placed in a room and their behavior. */
public class Item {
  private String itemName;

  public Item(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public String toString() {
    return itemName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(itemName.toLowerCase(), item.itemName.toLowerCase());
  }

  public String getItemName() {
    return itemName;
  }
}
