import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.io.IOException;

public class SingleEliminationTest {

  private IManager manager;
  private ArrayList<String> teams;

  @Before
  public void init() {

    try {
      teams = CompetitionManager.readPlayers("teams.txt");
    } catch (IOException e) {
       System.out.println("Error: " + e);
    }
    //manager = new SingleElimination();
    manager = IManagerFactory.getManager("SingleElimination");
    manager.setPlayers(teams);

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

}
