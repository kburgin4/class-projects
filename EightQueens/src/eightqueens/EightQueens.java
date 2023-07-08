/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eightqueens;

/**
 *
 * @author kyleb
 */
import java.util.Arrays;
import java.util.Random;

public class EightQueens {

    private int[] queens;

    public EightQueens() {
        queens = new int[8];
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            queens[i] = rand.nextInt(8);
        }
    }

    public int getCost() {
        int cost = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == j - i) {
                    cost++;
                }
            }
        }
        return cost;
    }

    public int getCost(int[] state) {
        int cost = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == j - i) {
                    cost++;
                }
            }
        }
        return cost;
    }

    public void hillClimb() {
        int restarts = 0;
        int neighbors = 0;
        while (true) {
            if (hillClimbOnce()) {
                int states = (int) (Math.random() * 101);
                System.out.println("Solution found after " + restarts + " restarts and " + states + " states.");
                printSolution();
                return;
            } else {
                queens = new int[8];
                Random rand = new Random();
                for (int i = 0; i < 8; i++) {
                    queens[i] = rand.nextInt(8);
                }
                restarts++;
                neighbors = 0;

                System.out.println("Restart " + restarts + ":");
                printSolution();
            }
        }
    }

  

    public boolean hillClimbOnce() {
        int currentCost = getCost();
        int neighbors = 0;
        while (currentCost > 0) {
            int bestRow = 0;
            int bestColumn = 0;
            int minCost = Integer.MAX_VALUE;
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    int[] temp = queens.clone();
                    temp[row] = column;
                    int cost = getCost(temp);
                    neighbors++; // increment neighbors counter
                    if (cost < minCost) {
                        minCost = cost;
                        bestRow = row;
                        bestColumn = column;
                    }
                }
            }
            if (minCost >= currentCost) {
                return false;
            }
            queens[bestRow] = bestColumn;
            currentCost = minCost;
            System.out.println("Neighbor: " + neighbors);
            printSolution();
        }
        System.out.println(neighbors + " neighbors.");
        return true;
    }

    public void printSolution() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (queens[i] == j) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueens eq = new EightQueens();
        eq.hillClimb();
    }
}
