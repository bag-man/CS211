package uk.ac.aber.dcs.owg1.cs21120.assignment1;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class SingleEliminationTest {

  private IManager manager;
  private ArrayList<String> teams;
  private ArrayQueue queue;

  @Before
  public void init() {

    try {
      teams = CompetitionManager.readPlayers("teams.txt");
    } catch (IOException e) {
       System.out.println("Error: " + e);
    }

    manager = IManagerFactory.getManager("SingleElimination");
    manager.setPlayers(teams);

    try {
      Field f = manager.getClass().getDeclaredField("queue"); 
      f.setAccessible(true);
      queue = (ArrayQueue) f.get(manager); 
    } catch(Exception e) {
      System.out.println("Error: " + e);
    }

  }

  @Test
  public void setPlayersTest() {
    assertEquals("Queues are not equal", queue.front(), teams.get(0));
    assertEquals("Queues are not equal", queue.length(), teams.size());
  }

  @Test
  public void hasNextMatchTest() { 
    assertEquals("There should be another match", manager.hasNextMatch(), true);
  }

  @Test
  public void nextMatchTest() {
    Match testMatch, genMatch;
    testMatch = new Match(teams.get(0), teams.get(1));
    genMatch = manager.nextMatch();

    assertEquals("First players don't match", testMatch.getPlayer1(), genMatch.getPlayer1());
    assertEquals("Second players don't match", testMatch.getPlayer2(), genMatch.getPlayer2());
  }

  @Test
  public void setMatchWinnerTest() {
    Match genMatch = manager.nextMatch();
    manager.setMatchWinner(true);
    assertEquals("Wrong team entered back in queue", genMatch.getPlayer1(), queue.position(6));
  }

  @Test
  public void getPositionTest() {
    while(manager.hasNextMatch()) {
      queue.deQ();
    }
    assertEquals("Wrong team found", manager.getPosition(0), queue.position(0));
  }

}
