package com.kodilla;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class GameService {

    private Player human;
    private Player computer;
    private Game game;
    private InputOutputController ioController;
    private Random random;

    public GameService(Player human, Player computer, Game game, InputOutputController ioController) {
        this.human = human;
        this.computer = computer;
        this.game = game;
        this.ioController = ioController;
        this.random = new Random();
    }

    public void playGame() {
        do {
            processUserMove(this.human, "Twój ruch, ");

            if (game.isGameOver()) {
                this.saveGameToFile("C:\\Programowanie\\Kodilla kurs\\Zapis gry XO\\ZapisGry.txt");
                break;
            }

            ioController.displayMessage("Ruch komputera...");
            processUserMove(this.computer, null);

        } while (!game.isGameOver());
    }

    private void processUserMove(Player player, String message) {
        ioController.displayBoard(game.getBoard());
        if (message != null) {
            ioController.displayMessage(message + player.getName() + ".");
        }
        int[] move = (player == this.human) ? ioController.getPlayerMove() : generateRandomMove();
        boolean validMove = game.addMove(move[0], move[1], player.getType());

        if (!validMove) {
            ioController.displayMessage("Ruch niepoprawny!");
            return;
        }

        char winner = game.findWinner();
        if (game.isGameOver()) {
            ioController.displayBoard(game.getBoard());
            ioController.displayWinner(winner);
        } else {
            ioController.displayBoard(game.getBoard());
        }
    }

    private int[] generateRandomMove() {
        int row;
        int column;
        do {
            row = random.nextInt(game.getBoardSize());
            column = random.nextInt(game.getBoardSize());
        } while (!game.isValidMove(row, column));
        return new int[]{row, column};
    }

    public void saveGameToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("----- Nowa gra -----\n");
            writer.write("Date and time: " + LocalDateTime.now() + "\n");
            writer.write("Human player: " + human.getName() + " (" + human.getType() + ")\n");
            writer.write("Computer player: " + computer.getName() + " (" + computer.getType() + ")\n");
            writer.write("Winner: " + game.findWinner() + "\n");
            writer.write("Board:\n");

            char[][] board = game.getBoard();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    writer.write(board[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.write("--------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

