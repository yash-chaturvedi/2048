package com.example;

import com.example.model.Board;
import com.example.service.GameService;

/**
 * @author Yash Chaturvedi
 */
public class TwoZeroFourEight {
    public static void main(String[] args) {
        Board board = new Board(4);
        GameService gameService = new GameService(board, 2048, true, 2);
        gameService.startGame();
    }
}
