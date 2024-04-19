package com.kodilla;

public class Game {
    private char[][] board;
    private int boardSize;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean addMove(int row, int column, char type) {
        if (isValidMove(row, column)) {
            board[row][column] = type;
            return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int column) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize && board[row][column] == ' ';
    }

    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public char findWinner() {
        char winner = ' ';

        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        for (int i = 0; i < boardSize; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return board[0][i];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        return winner;
    }

    public boolean isGameOver() {
        return findWinner() != ' ' || isBoardFull();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}