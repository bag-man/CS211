import java.util.ArrayList;

public class DoubleElimination implements IManager {

  private ArrayQueue winQueue, lossQueue;
  private String team1;
  private String team2;
  private boolean finale, league, fin;

  public void setPlayers(ArrayList<String> teams) {

    winQueue = new ArrayQueue(teams.size());
    lossQueue = new ArrayQueue(teams.size());

    for(String team: teams) {
      winQueue.enQ(team);
    }

  }

  public boolean hasNextMatch() {

    if((winQueue.length() < 1 && lossQueue.length() < 1) || fin ) { // (winQueue.length() == 2 && lossQueue.length() == 0 )) {
      return false;
    } else {
      return true;
    }

  }

  public Match nextMatch() throws NoNextMatchException {

    if(hasNextMatch() == true){
      if(winQueue.length() > lossQueue.length()) {
	team1 = winQueue.deQ();
	team2 = winQueue.deQ();
	league = true;
	return new Match(team1, team2);
      } else if((winQueue.length() == 1) && (lossQueue.length() == 1)){
	team1 = winQueue.deQ();
	team2 = lossQueue.deQ();
	finale = true;
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
    if(finale){
      if(team) {
	winQueue.enQ(team1); // Winner of win Queue
	winQueue.enQ(team2); // Looser of win Queue
      } else {
	winQueue.enQ(team2); // Winner of win Queue
	winQueue.enQ(team1); // Winner of loss Queue
      } 
      fin = true;
    } else if(league) {
      if(team) {
	winQueue.enQ(team1); // Winner of win Queue
	lossQueue.enQ(team2); // Looser of win Queue
      } else {
	winQueue.enQ(team2); // Winner of win Queue
	lossQueue.enQ(team1); // Winner of loss Queue
      } 
    } else {
      if(team) {
	lossQueue.enQ(team1); // Looser of win Queue
      } else {
	lossQueue.enQ(team2); // Winner of loss Queue
      } 
    }

    league = false;

  }

  public String getPosition(int n) {

    if(hasNextMatch() || n > 1) {
      return null;
    } else {
      return winQueue.position(n);
    }

  }


}
