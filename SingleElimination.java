import java.util.ArrayList;

public class SingleElimination implements IManager {

  private ArrayList<String> teamList;

  public void setPlayers(ArrayList<String> players) {

     teamList = players;

  }

  public boolean hasNextMatch() {

    return true;

  }

  public Match nextMatch() throws NoNextMatchException {

    return new Match("One", "Two");

  }

  public void setMatchWinner(boolean player1) {

  }

  public String getPosition(int n) {

    return "String";

  }

}
