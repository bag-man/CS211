import java.util.ArrayList;

public class DoubleElimination implements IManager {

  private ArrayQueue winQueue, lossQueue;
  private String team1;
  private String team2;

  public void setPlayers(ArrayList<String> teams) {

    winQueue = new ArrayQueue(teams.size());
    lossQueue = new ArrayQueue(teams.size());

    for(String team: teams) {
      winQueue.enQ(team);
    }

  }

  public boolean hasNextMatch() {

    if(winQueue.length() > lossQueue.length()) {
      return true;
    } else {
      return false;
    }

  }

  public Match nextMatch() throws NoNextMatchException {

    if(hasNextMatch() == true){
      team1 = winQueue.deQ();
      team2 = winQueue.deQ();
      return new Match(team1, team2);
    } else{
      team1 = lossQueue.deQ();
      team2 = lossQueue.deQ();
      return new Match(team1, team2);
    }

  }

  public void setMatchWinner(boolean team) {
    
    if(team) {
      winQueue.enQ(team1);
      lossQueue.enQ(team2);
    } else {
      lossQueue.enQ(team1);
      //lossQueue.deQ();
    }

  }

  public String getPosition(int n) {

    if(hasNextMatch() || n > 0) {
      return null;
    } else {
      return winQueue.position(n);
    }

  }

}
