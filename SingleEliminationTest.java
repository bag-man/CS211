import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

public class SingleEliminationTest {

  private SingleElimination manager;
  private ArrayList<String> teams;

  @Before
  public void init() {

    teams = CompetitionManager.readPlayers("/root/CS211/teams.txt");
    System.out.print(teams.size());
    manager = new SingleElimination();

  }

/*
  @Test
  public void testWon() { 
    model.tryWord("ahoy");
    assertEquals("Game has not been won.", model.won(), true);
  }
*/

}

