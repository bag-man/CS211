import java.util.ArrayList;

public class DoubleElimination implements IManager {

  private ArrayQueue winQueue, lossQueue;
  private String team1;
  private String team2;

  public void setPlayers(ArrayList<String> teams) {

    winQueue = new ArrayQueue(teams.size(), "winners");
    lossQueue = new ArrayQueue(teams.size(), "loosers");

    for(String team: teams) {
      winQueue.enQ(team);
    }

  }

  public boolean hasNextMatch() {

    if(winQueue.length() != 1) {
      return true;
    } else {
      return false;
    }

  }

  public Match nextMatch() throws NoNextMatchException {

    if(hasNextMatch() == true){
      if(winQueue.length() > lossQueue.length()) {
	team1 = winQueue.deQ();
	team2 = winQueue.deQ();
	return new Match(team1, team2);
      } else if((winQueue.length() == 1) && (lossQueue.length() == 1)){
	team1 = winQueue.deQ();
	team2 = lossQueue.deQ();
	return new Match(team1, team2);
      } else {
	team1 = lossQueue.deQ();
	team2 = lossQueue.deQ();
	return new Match(team1, team2);
      }
    } else {
      throw new NoNextMatchException("Game Over.");
    }

  }

  public void setMatchWinner(boolean team) {
    
    if(team) {
      winQueue.enQ(team1); // Winner of win Queue
      lossQueue.enQ(team2); // Looser of win Queue
    } else {
      lossQueue.enQ(team1); // Winner of loss Queue
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
