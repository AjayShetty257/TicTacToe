package TicTacToe;

import java.util.Scanner;

public class TicTacToeNew {
    static Scanner sc = new Scanner(System.in);
    int box[][] = new int[3][3];

    public void readInput(int playerNo) {
        System.out.println("Please enter x and y values for Player " + playerNo);
        int x = sc.nextInt();
        int y = sc.nextInt();
        fillTheBox(playerNo, x, y);
    }

    private void fillTheBox(int playerNo, int x, int y) {
        box[x][y] = playerNo; //fill box
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

    public boolean isPlayerWinning(int playerNo) {
        for (int col = 0; col < 3; col++) {
            if (box[0][col] == playerNo && box[1][col] == playerNo && box[2][col] == playerNo)
                return true;
        }
        for (int row = 0; row < 3; row++) {
            if (box[row][0] == playerNo && box[row][1] == playerNo && box[row][2] == playerNo)
                return true;
        }
        if (box[0][0] == playerNo && box[1][1] == playerNo && box[2][2] == playerNo)
            return true;
        if (box[0][2] == playerNo && box[1][1] == playerNo && box[2][0] == playerNo)
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

    public boolean isResultDeclared(int playerNo) {
        if (isPlayerWinning(playerNo)) {
            System.out.println("Player " + playerNo + " Wins");
            return true;
        } else if (isDraw()) {
            System.out.println("Match drawn");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToeNew t = new TicTacToeNew();
        int FIRST_PLAYER = 1;
        int SECOND_PLAYER = 2;

        while(true) {
            System.out.println("Start Game");
            t.box = new int[3][3];

            for (int i = 1; i <= 5; i++) {
                t.readInput(FIRST_PLAYER);
                t.print();

                if (t.isResultDeclared(FIRST_PLAYER))
                    break;


                t.readInput(SECOND_PLAYER);
                t.print();

                if (t.isResultDeclared(SECOND_PLAYER))
                    break;
            }
            System.out.println("Press 1 to continue, Press 2 to exit!");
            if(sc.nextInt() != 1) {
                break;
            }
        }
    }
}
