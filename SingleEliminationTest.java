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
    IManager manager = IManagerFactory.getManager("SingleElimination");
    manager.setPlayers(teams);

  }

  @Test
  public void hasNextMatchTest() { 
    assertEquals("Is there another match", manager.hasNextMatch(), true);
  }

}
