package com.kodilla;

import java.util.Random;

public class GameService {

    private Player human;
    private Player computer;
    private Game game;
    private InputOutputController ioController;

    public GameService(Player human, Player computer, Game game, InputOutputController ioController) {
        this.human = human;
        this.computer = computer;
        this.game = game;
        this.ioController = ioController;
    }

    public void playGame() {
        do {
            ioController.displayBoard(game.getBoard());
            ioController.displayMessage("Twój ruch, " + human.getName() + ".");
            int[] playerMoveArray = ioController.getPlayerMove();
            int row = playerMoveArray[0];
            int column = playerMoveArray[1];
            game.addMove(row, column, human.getType());

            if (!isValidMove(row, column)) {
                ioController.displayMessage("Ruch niedozwolony. Spróbuj ponownie.");
                continue;
            }


            char winner = game.findWinner();
            if (winner != ' ') {
                ioController.displayBoard(game.getBoard());
                ioController.displayWinner(winner);
                break; // Jeśli jest zwycięzca, przerwij pętlę
            }

            ioController.displayMessage("Ruch komputera...");
            int[] computerMove = generateComputerMove();
            game.addMove(computerMove[0], computerMove[1], computer.getType());
            winner = game.findWinner();

            if (winner != ' ') {
                ioController.displayBoard(game.getBoard());
                ioController.displayWinner(winner);
                break; // Jeśli jest zwycięzca, przerwij pętlę
            }
            System.out.println("isValidMove: true");
        } while (!game.isGameOver());
    }


    private boolean isValidMove(int row, int column) {
        boolean valid = row >= 0 && row < game.getBoard().length && column >= 0 && column < game.getBoard().length && game.getBoard()[row][column] == ' ';
        if (!valid) {
            ioController.displayMessage("Niepoprawne współrzędne ruchu. Ruch nie został zapisany na planszy.");
        }
        System.out.println("isValidMove: " + valid);
        return valid;
    }




    private int[] generateComputerMove() {
        Random random = new Random();
        int row;
        int column;
        do {
            row = random.nextInt(game.getBoard().length);
            column = random.nextInt(game.getBoard().length);
        } while (!isValidMove(row, column));
        return new int[]{row, column};
    }
}
