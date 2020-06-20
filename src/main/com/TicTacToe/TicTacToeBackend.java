package com.TicTacToe;

import java.util.Scanner;

public class TicTacToeBackend {
    static Scanner sc = new Scanner(System.in);
    private int box[][] = new int[3][3];
    private int currentPlayer = 1;


    public void readInput() {
        System.out.println("Please enter x and y values for Player " + currentPlayer);
        //TODO: map jbuttons to xy
        int x = sc.nextInt();
        int y = sc.nextInt();
        fillTheBox(x, y);

    }

    public void fillTheBox(int x, int y) {
        box[x][y] = currentPlayer; //fill box
    }

    public void print() {
        StringBuilder printValue = new StringBuilder();
        for (int r = 0; r < 3; r++) {
            printValue.append("|")
                    .append(converter(box[r][0]))
                    .append("|")
                    .append(converter(box[r][1]))
                    .append("|")
                    .append(converter(box[r][2]))
                    .append("|\n");
        }
        System.out.print(printValue);
    }

    public String converter(int boxValue) {
        switch (boxValue) {
            case 1:
                return "O";
            case 2:
                return "X";
            default:
                return " ";
        }
    }

    public boolean isPlayerWinning() {
        for (int col = 0; col < 3; col++) {
            if (box[0][col] == currentPlayer && box[1][col] == currentPlayer && box[2][col] == currentPlayer)
                return true;
        }
        for (int row = 0; row < 3; row++) {
            if (box[row][0] == currentPlayer && box[row][1] == currentPlayer && box[row][2] == currentPlayer)
                return true;
        }
        if (box[0][0] == currentPlayer && box[1][1] == currentPlayer && box[2][2] == currentPlayer)
            return true;
        if (box[0][2] == currentPlayer && box[1][1] == currentPlayer && box[2][0] == currentPlayer)
            return true;
        return false;
    }

    public boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (box[row][col] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isResultDeclared() {
        if (isPlayerWinning()) {
            System.out.println("Player " + currentPlayer + " Wins");
            return true;
        } else if (isDraw()) {
            System.out.println("Match drawn");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToeBackend t = new TicTacToeBackend();
        
        while(true) {
            System.out.println("Start Game");
            t.box = new int[3][3];

            for (int i = 1; i <= 9; i++) {
                t.readInput();
                t.print();

                if (t.isResultDeclared())
                    break;

                t.toggleCurrentPlayer();
            }
            System.out.println("Press 1 to continue, Press 2 to exit!");
            if(sc.nextInt() != 1) {
                break;
            }
        }
    }

    public void toggleCurrentPlayer() {
        currentPlayer = (currentPlayer == 1 ? 2 : 1);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
