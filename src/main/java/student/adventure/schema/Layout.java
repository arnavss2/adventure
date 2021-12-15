package student.adventure.schema;

import java.util.Map;

/** Layout class consisting of the starting and ending rooms and a list of all the rooms. */
public class Layout {
  private int startingRoomId;
  private int endingRoomId;
  private int maxSteps;
  private Map<Integer, Room> rooms;

  public int getStartingRoomId() {
    return startingRoomId;
  }

  public int getEndingRoomId() {
    return endingRoomId;
  }

  public int getMaxSteps() { return maxSteps; }

  public Map<Integer, Room> getRooms() {
    return rooms;
  }

  public void checkLayout() throws IllegalArgumentException {
    for (Integer roomId : rooms.keySet()) {
      for (Direction direction : rooms.get(roomId).getDirections()) {
        if (direction.getRoomId() > endingRoomId || direction.getRoomId() < startingRoomId) {
          throw new IllegalArgumentException("Invalid layout!");
        }
      }
    }
  }
}
