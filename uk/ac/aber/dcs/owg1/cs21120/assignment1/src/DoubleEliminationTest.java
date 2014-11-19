import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class DoubleEliminationTest {

  private IManager manager;
  private ArrayList<String> teams;
  private ArrayQueue winQ, lossQ;

  @Before
  public void init() {

    try {
      teams = CompetitionManager.readPlayers("teams.txt");
    } catch (IOException e) {
       System.out.println("Error: " + e);
    }

    manager = IManagerFactory.getManager("DoubleElimination");
    manager.setPlayers(teams);

    try {
      Field f1 = manager.getClass().getDeclaredField("winQueue"); 
      Field f2 = manager.getClass().getDeclaredField("lossQueue"); 
      f1.setAccessible(true);
      f2.setAccessible(true);
      winQ = (ArrayQueue) f1.get(manager); 
      lossQ = (ArrayQueue) f2.get(manager); 
    } catch(Exception e) {
      System.out.println("Error: " + e);
    }

  }

  @Test
  public void setPlayersTest() {
    assertEquals("Queues are not equal", winQ.front(), teams.get(0));
    assertEquals("Queues are not equal", winQ.length(), teams.size());
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
    assertEquals("Wrong team entered back in queue", genMatch.getPlayer1(), winQ.position(6));
    assertEquals("Wrong team entered back in queue", genMatch.getPlayer2(), lossQ.position(0));
  }

  @Test
  public void getPositionTest() {
    while(manager.hasNextMatch()) {
      Match genMatch = manager.nextMatch();
      manager.setMatchWinner(true);
    }
    assertEquals("Wrong team found", manager.getPosition(0), winQ.position(0));
    assertEquals("Wrong team found", manager.getPosition(7), winQ.position(7));
  }

}
