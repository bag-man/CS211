package uk.ac.aber.dcs.owg1.cs21120.assignment1;
import java.util.ArrayList;

public class SingleElimination implements IManager {

  private ArrayQueue queue;
  private String team1;
  private String team2;

  public void setPlayers(ArrayList<String> teams) {

    queue = new ArrayQueue(teams.size());
    for(String team: teams) {
      queue.enQ(team);
    }

  }

  public boolean hasNextMatch() {

    if(queue.length() == 1) {
      return false;
    } else {
      return true;
    }

  }

  public Match nextMatch() throws NoNextMatchException {

    if(hasNextMatch() == false){
      throw new NoNextMatchException("No matches left");
    } else{
      team1 = queue.deQ();
      team2 = queue.deQ();
      return new Match(team1, team2);
    }

  }

  public void setMatchWinner(boolean team) {
    
    if(team) {
      queue.enQ(team1);
    } else {
      queue.enQ(team2);
    }

  }

  public String getPosition(int n) {

    if(hasNextMatch() || n > 0) {
      return null;
    } else {
      return queue.position(n);
    }

  }

}
