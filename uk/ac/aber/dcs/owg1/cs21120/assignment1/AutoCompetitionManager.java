package uk.ac.aber.dcs.owg1.cs21120.assignment1;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Main program to run a competition
 * @author bpt
 */
public class AutoCompetitionManager {
    /** Main method
     * 
     * @param args args[0] should be the name of the IManager class and args[1] the name of a file containing the list of players or teams, one per line
     * 
     * @throws FileNotFoundException thrown if the list file isn't found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length!=2) {
            System.out.println("Usage: CompetitionManager <IManager class name> <list file> ");
            System.exit(0);
        }
        IManager manager = IManagerFactory.getManager(args[0]);
        if (manager==null) {
            System.out.println("Can't find IManager class name: " + args[0]);
            System.exit(0);
        }
        ArrayList<String> competitors = readPlayers(args[1]);

        Scanner in = new Scanner(System.in);
        manager.setPlayers(competitors);
        while(manager.hasNextMatch()) {
            Match match = manager.nextMatch();
            //System.out.println("Player 1: " + match.getPlayer1()); 
            //System.out.println("Player 2: " + match.getPlayer2());  
            boolean notValidInput = true;
            boolean draw=true;
            int p1score=0, p2score=0;
            while (draw) {
	     /* 
                while (notValidInput) {
                    if (in.hasNextInt()) {
                        notValidInput = false;
                    } else if (in.hasNext()) {
                        String str = in.next();
                        System.out.println(str + " is not a valid input, please enter a number");
                    }
                } 
                p1score = in.nextInt();
                notValidInput = true;
                while (notValidInput) {
                    if (in.hasNextInt()) {
                        notValidInput = false;
                    } else if (in.hasNext()) {
                        String str = in.next();
                        System.out.println(str + " is not a valid input, please enter a number");
                    }
                } 
                p2score = in.nextInt();
                if (p1score == p2score) {
                    System.out.println("We need a result, not a draw!  Please have a rematch!");
                } else {
                    draw = false;
                }
	     */
	      p1score = score();
	      p2score = score();
	      if (p1score != p2score) {
		System.out.println("\n" + match.getPlayer1() + ": " + p1score);
		System.out.println(match.getPlayer2() + ": " + p2score);
		draw = false;
	      }
            }
            manager.setMatchWinner(p1score > p2score);
        }
   
        System.out.println("\nWinner is: " + manager.getPosition(0));
        System.out.println("Runner up is: " + manager.getPosition(1));
    }
    
    public static ArrayList<String> readPlayers(String filename) throws FileNotFoundException {
        FileReader fr = new FileReader(filename);
        Scanner sc = new Scanner(fr);
        ArrayList<String> players = new ArrayList<String>();
        while (sc.hasNextLine()) {
            players.add(sc.nextLine());
        }
        return players;

    }

    public static int score() {

	Random rand = new Random();

	return rand.nextInt((9 - 0) + 1) + 0;

    }
}
