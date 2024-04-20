package com.kodilla;

public class Main {
    public static void main(String[] args) {
        InputOutputController ioController = new InputOutputController();
        ioController.displayMessage("Witaj w grze!");

        String playerName = ioController.getPlayerName();
        char playerSymbol = ioController.getPlayerSymbol();
        int boardSize = ioController.getBoardSize();

        Player human = new Player(playerName, playerSymbol);
        Player computer = new Player("Komputer", (playerSymbol == 'X') ? 'O' : 'X');

        Game game = new Game(boardSize);
        GameService gameService = new GameService(human, computer, game, ioController);

        ioController.displayMessage("Rozpoczynamy grę z komputerem...");
        gameService.playGame();
    }
}