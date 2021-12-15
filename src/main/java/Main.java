import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import student.adventure.cli.CommandLine;
import student.server.AdventureResource;
import student.server.AdventureServer;

/** Main class that runs the game. */
public class Main {
  /** Main method that starts the game. */
  public static void main(String[] args) throws IOException {
    HttpServer server = AdventureServer.createServer(AdventureResource.class);
    server.start();
    //CommandLine.startGame();
  }
}
