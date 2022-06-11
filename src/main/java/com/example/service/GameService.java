package com.example.service;

import com.example.model.Board;
import com.example.model.SlideDirection;
import com.example.model.Tile;

import java.util.Scanner;

/**
 * @author Yash Chaturvedi
 */
public class GameService {

    private final Board board;
    private final int winValue;
    private final boolean endGameAtWinValue;
    private final int multiplicationFactor;
    private final int initialTiles = 2;

    public GameService(Board board, int winValue, boolean endGameAtWinValue, int multiplicationFactor) {
        this.board = board;
        this.winValue = winValue;
        this.endGameAtWinValue = endGameAtWinValue;
        this.multiplicationFactor = multiplicationFactor;
    }

    public void startGame() {
        for(int i=0; i<initialTiles; i++) {
            spawnTile();
        }
        board.print();
        Scanner sc = new Scanner(System.in);
        while(!board.boardFilled() || board.slidePossible()) {
            System.out.println("Enter Slide Direction :");
            SlideDirection slideDirection = SlideDirection.valueOf(sc.nextInt());
            board.slide(slideDirection);
            if(checkIfPlayerWon()) {
                break;
            }
            boolean spawnSuccessful = spawnTile();
            if(spawnSuccessful) {
                board.print();
            }
            else {
                System.out.println("Game over");
                break;
            }
        }
    }

    private boolean spawnTile() {
//        double power = Math.floor((Math.random() * 10) + 1);
//        int value = (int) Math.pow(multiplicationFactor, power);
        Tile tile = new Tile(2);
        return board.addTile(tile);
    }

    private boolean checkIfPlayerWon() {
        Tile maxTile = board.getMaxTile();
        if(maxTile.getValue() == winValue) {
            System.out.println("Congratulations");
            return true;
        }
        return false;
    }
}
