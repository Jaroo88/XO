package com.kodilla;

import java.util.Scanner;

public class InputOutputController {
    private final Scanner scanner;

    public InputOutputController() {
        this.scanner = new Scanner(System.in);
    }
    public String getPlayerName() {
        System.out.print("Podaj swoje imię: ");
        return scanner.nextLine();
    }

    public char getPlayerSymbol() {
        char symbol;
        do {
            System.out.print("Wybierz swój symbol ('X' lub 'O'): ");
            symbol = scanner.nextLine().toUpperCase().charAt(0);
        } while (symbol != 'X' && symbol != 'O');
        return symbol;
    }

    public int getBoardSize() {
        System.out.print("Podaj rozmiar planszy: ");
        int size = scanner.nextInt();
        scanner.nextLine();
        return size;
    }

    public void displayBoard(char[][] board) {
        System.out.print("   ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i < board.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");


            System.out.print("  ");
            for (int k = 0; k < board.length; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }

    public int[] getPlayerMove() {
        System.out.println("Podaj współrzędne ruchu (wiersz, kolumna): ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Wprowadzone współrzędne: " + row + ", " + column);
        return new int[]{row, column};
    }



    public void displayWinner(char winner) {
        if (winner == ' ') {
            System.out.println("Remis!");
        } else {
            System.out.println("Wygrał gracz: " + winner);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
