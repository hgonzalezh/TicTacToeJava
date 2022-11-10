package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = new String[9];
        Arrays.fill(data," ");
        TicTacToe(data, scanner);


    }

    public static void printDashes() {
        String dash = "-";
        System.out.println(dash.repeat(9));
    }

    public static void TicTacToe(String[] data, Scanner scanner) {

        boolean gameOver = false;
        boolean playerOneTurn = true;

        printDashes();
        printGameBoard(data);
        printDashes();

        while (!gameOver) {

            playerOneTurn = promptUserInput(data, scanner, playerOneTurn);
            gameOver = validateGame(data);
        }
    }

    public static void printGameBoard(String[] data) {

        for (int i = 0; i < data.length; i++) {
            if (i % 3 == 0 || i == 0) {
                System.out.print("| ");
                System.out.print(data[i] + " ");
            } else if ((i + 1) % 3 == 0) {
                System.out.print(data[i] + " ");
                System.out.println("|");
            } else {
                System.out.print(data[i] + " ");
            }
        }
    }

    public static boolean promptUserInput(String[] data, Scanner scanner, boolean playerOneTurn) {

        int coordinate = 0;
        boolean validInput = false;
        String symbol = playerOneTurn ? "X" : "O";

        while (!validInput) {
            try {
                coordinate = Integer.parseInt(scanner.nextLine().trim().replace(" ",""));
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
            }

            switch (coordinate) {
                case 11:
                    if (" ".equals(data[0])) {
                        data[0] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 12:
                    if (" ".equals(data[1])) {
                        data[1] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 13:
                    if (" ".equals(data[2])) {
                        data[2] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 21:
                    if (" ".equals(data[3])) {
                        data[3] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 22:
                    if (" ".equals(data[4])) {
                        data[4] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 23:
                    if (" ".equals(data[5])) {
                        data[5] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 31:
                    if (" ".equals(data[6])) {
                        data[6] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 32:
                    if (" ".equals(data[7])) {
                        data[7] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                case 33:
                    if (" ".equals(data[8])) {
                        data[8] = symbol;
                        validInput = true;
                    } else System.out.println("This cell is occupied! Choose another one!");
                    break;
                default:
                    System.out.println("Coordinates should be from 1 to 3!");
                    break;
            }
        }
        printDashes();
        printGameBoard(data);
        printDashes();

        return !playerOneTurn;
    }

    public static boolean validateGame(String[] data) {
        /* winner is a two element array
         * winner[0] indicates game result, +1 for X, -1 for O, zero for no winner.
         * winner[1] counts if there's more than one winner (Illegal state) or if the game's not finished (-1)*/
        int[] winner = {0, 0};

        checkDiagonals(data, winner);
        checkColumns(data, winner);
        checkRows(data, winner);

        if (winner[1] > 1) System.out.println("Impossible");
        else if (winner[0] == 0 && winner[1] == 0) {
            System.out.println("Draw");
            return true;
        }
        else if (winner[0] == 0 && winner[1] == -1) {
            //System.out.println("Game not finished");
        }
        else if (winner[0] == 1 && winner[1] == 1) {
            System.out.println("X wins");
            return true;
        }
        else if (winner[0] == -1 && winner[1] == 1) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }

    public static void checkRows(String[] data, int[] winner) {
        int moveCount = 0;
        int xCount = 0;
        int oCount = 0;


        int[] container = {0, 0, 0};

        for (int i = 0; i < 3; i++) {
            switch (data[i]) {
                case "X":
                    moveCount++;
                    xCount++;
                    container[0] += 1;
                    break;
                case "O":
                    moveCount++;
                    oCount++;
                    container[0] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        for (int i = 3; i < 6; i++) {
            switch (data[i]) {
                case "X":
                    moveCount++;
                    xCount++;
                    container[1] += 1;
                    break;
                case "O":
                    moveCount++;
                    oCount++;
                    container[1] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        for (int i = 6; i < 9; i++) {
            switch (data[i]) {
                case "X":
                    moveCount++;
                    xCount++;
                    container[2] += 1;
                    break;
                case "O":
                    moveCount++;
                    oCount++;
                    container[2] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        // Calculate winner
        for (int i = 0; i < 3; i++) {
            if (container[i] == 3) {
                winner[0] = 1;
                winner[1]++;
            } else if (container[i] == -3) {
                winner[0] = -1;
                winner[1]++;
            }
        }

        // Determine if the game is not finished
        if (winner[0] == 0 && moveCount == 9) {
            winner[1] = 0; // Draw
        } else if (winner[0] == 0 && moveCount < 9) {
            winner[1] = -1; // Game not finished
        }

        if (xCount > oCount + 1 || oCount > xCount + 1) {
            winner[1] = 99;
        }

    }

    public static void checkColumns(String[] data, int[] winner) {
        int[] container = {0, 0, 0};

        for (int i = 0; i < 9; i += 3) {
            switch (data[i]) {
                case "X":
                    container[0] += 1;
                    break;
                case "O":
                    container[0] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        for (int i = 1; i < 9; i += 3) {
            switch (data[i]) {
                case "X":
                    container[1] += 1;
                    break;
                case "O":
                    container[1] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        for (int i = 2; i < 9; i += 3) {
            switch (data[i]) {
                case "X":
                    container[2] += 1;
                    break;
                case "O":
                    container[2] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        // Calculate winner
        for (int i = 0; i < 3; i++) {
            if (container[i] == 3) {
                winner[0] = 1;
                winner[1]++;
            } else if (container[i] == -3) {
                winner[0] = -1;
                winner[1]++;
            }
        }
    }

    public static void checkDiagonals(String[] data, int[] winner) {
        int[] container = {0, 0};

        for (int i = 0; i < 9; i += 4) {
            switch (data[i]) {
                case "X":
                    container[0] += 1;
                    break;
                case "O":
                    container[0] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        for (int i = 2; i < 8; i += 2) {
            switch (data[i]) {
                case "X":
                    container[1] += 1;
                    break;
                case "O":
                    container[1] -= 1;
                    break;
                case "_":

                    break;
            }
        }

        // Calculate winner
        for (int i = 0; i < 2; i++) {
            if (container[i] == 3) {
                winner[0] = 1;
                winner[1]++;
            } else if (container[i] == -3) {
                winner[0] = -1;
                winner[1]++;
            }
        }

    }

}
