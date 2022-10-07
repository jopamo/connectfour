/*
Connect Four
7 columns
6 rows
42 positions

gameBoard[6][7]
*/

package std;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scannerObj = new Scanner(System.in);

    String playerName = "";

    int fullPositions = 0;
    int intPosition;
    int maxColumns = 7;
    int maxRows = 6;
    int maxPositions = maxRows * maxColumns;
    int userColumn = -1;

    int[][] gameBoard = new int[maxRows][maxColumns];

    //valid user input
    while (userColumn > 6 || userColumn < 0) {
      //while no winner and board not full
      while (!isWinner(gameBoard) && fullPositions != maxPositions) {
        //red will always be divisible by 2 after yellow's turn
        if (fullPositions % 2 == 0) {
          playerName = "Red";
          intPosition = 1;
        } else {
          playerName = "Yellow";
          intPosition = 2;
        }

        System.out.print(playerName + " Player: Choose a column for your token (0–6): ");

        try {
          userColumn = Integer.parseInt(scannerObj.next());
        } catch (NumberFormatException e) {
          System.out.println("Invalid user input");
          System.out.println();
        }

        if (userColumn > 6 || userColumn < 0) {
          break;
        }

        //starting at the bottom row, find next available with default value of 0 and reassign
        for (int i = gameBoard.length - 1; i >= 0; i--) {
          if (gameBoard[i][userColumn] == 0) {
            gameBoard[i][userColumn] = intPosition;
            fullPositions++;
            break;
          }
          //gameBoard[0] is top row
          if (i == 0) {
            System.out.println("This column is full, choose another.");
          }
        }
        System.out.println("-----------------------------");
        System.out.println("  0   1   2   3   4   5   6  ");
        printGameBoard(gameBoard);
      }
    }

    if (isWinner(gameBoard)) {
      System.out.println(playerName + " player wins the game!");
    } else if (fullPositions == maxPositions) {
      System.out.println("Tie game.");
    }

  }

  public static void printGameBoard(int[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      //leading dividers for each row
      System.out.print("|");
      //reassign the integers back to characters
      for (int j = 0; j < gameBoard[i].length; j++) {
        char ch;

        if (gameBoard[i][j] == 1) {
          ch = 'R';
        } else if (gameBoard[i][j] == 2) {
          ch = 'Y';
        } else {
          ch = ' ';
        }
        //adds either the above letter and divider or just divider with space
        System.out.print(" " + ch + " |");
      }
      System.out.println();
    }
    System.out.println("-----------------------------");
    System.out.println();
  }

  public static boolean isWinner(int[][] gameBoard) {
    return diagonalWin(gameBoard) || columnWin(gameBoard) || rowWin(gameBoard);
  }

  public static boolean diagonalWin(int[][] gameBoard) {
    // down l to r
    //enough room to slope down
    for (int i = gameBoard.length - 4; i >= 0; i--) {
      //enough room from right
      for (int j = gameBoard[i].length - 4; j >= 0; j--) {
        if (gameBoard[i][j] == gameBoard[i+1][j+1] &&
            gameBoard[i][j] == gameBoard[i+2][j+2] &&
            gameBoard[i][j] == gameBoard[i+3][j+3] &&
            gameBoard[i][j] != 0) {
          return true;
        }
      }
    }

    // up l to r
    //3 because it needs for spaces for sloping up to 0
    for (int i = 3; i <= gameBoard.length - 1; i++) {
      //4 because it needs 4 spaces l to r
      for (int j = gameBoard[i].length - 4; j >= 0; j--) {
        if (gameBoard[i][j] == gameBoard[i-1][j+1] &&
            gameBoard[i][j] == gameBoard[i-2][j+2] &&
            gameBoard[i][j] == gameBoard[i-3][j+3] &&
            gameBoard[i][j] != 0) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean columnWin(int[][] gameBoard) {
    for (int i = 0; i < gameBoard[0].length; i++) {
      int startColumn = gameBoard[0][i];
      int num = 1;

      for (int j = 1; j < gameBoard.length; j++) {

        if (startColumn == gameBoard[j][i] && startColumn != 0) {
          num++;
        } else {
          startColumn = gameBoard[j][i];
          num = 1;
        }

        if (num == 4) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean rowWin(int[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      int startRow = gameBoard[i][0];
      int num = 1;

      for (int j = 1; j < gameBoard[i].length; j++) {
        if (startRow == gameBoard[i][j] && startRow != 0) {
          num++;
        } else {
          startRow = gameBoard[i][j];
          num = 1;
        }

        if (num == 4) {
          return true;
        }
      }
    }
    return false;
  }
}
