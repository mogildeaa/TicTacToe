package demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TicTacToe extends HttpServlet {

	private String[][] table = new String[3][3];
	private String currentPlayer;

	public TicTacToe() {
        initializeGame();
    
	}

    private void initializeGame() {
        currentPlayer = "x";
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                table[i][j] = "_";
            }
        }
    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String player = request.getParameter("player");
        String line = request.getParameter("line");
        String column = request.getParameter("column");

	    if (!checkRequestParameters(player, line, column)) {
	        return;
        }

        int lineNumber = Integer.valueOf(line);
        int columnNumber = Integer.valueOf(column);

        if(!checkMoveInsideTable(lineNumber, columnNumber)) {
            return;
        }

        if (!checkEmptyPosition(lineNumber, columnNumber)) {
            return;
        }

        move(player, lineNumber, columnNumber);
        printTable();
        System.out.println();
        if (isWinner(player)) {
            System.out.println("Game over, winner is player: " + player);
            initializeGame();
            return;
        }

        if(fullTable()) {
            System.out.println("Game over, the result is a draw");
            initializeGame();
            return;
        }
	}

    private void move(String player, int lineNumber, int columnNumber) {
        table[lineNumber][columnNumber] = player;
        if ("x".equals(currentPlayer)) {
            currentPlayer = "0";
        } else {
            currentPlayer = "x";
        }
    }

    private void printTable() {
	    for (int i=0; i<3; i++) {
	        for (int j=0; j<3; j++) {
	            System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkCurrentPlayer(String player) {
        if (!currentPlayer.equals(player)) {
            System.out.println("Not the player at move");
            return false;
        }
        return true;
    }

    private boolean missingParameter(String parameterName) {
	    return (parameterName == null);
    }

    private boolean checkMoveInsideTable(int lineNumber, int columnNumber) {
        if ((lineNumber < 0) ||(lineNumber>2)) {
            System.out.println("Line number outside the table");
            return false;
        }

        if ((columnNumber < 0) ||(columnNumber>2)) {
            System.out.println("Column number outside the table");
            return false;
        }

        return true;
    }

    private boolean checkEmptyPosition(int lineNumber, int columnNumber) {
        if (!"_".equals(table[lineNumber][columnNumber])) {
            System.out.println("Not an empty position");
            return false;
        }

        return true;
    }

    private boolean checkRequestParameters(String player, String line, String column) {
        if (missingParameter(player)) {
            System.out.println("Missing player");
            return false;
        } else {
            if (!checkCurrentPlayer(player)) {
                return false;
            }
        }

        if (missingParameter(line)) {
            System.out.println("Missing line");
            return false;
        }

        if (missingParameter(column)) {
            System.out.println("Missing column");
            return false;
        }
	    return true;
    }

    private boolean threeInLine(String player, int lineNumber) {
	    return (player.equals(table[lineNumber][0]) &&
                player.equals(table[lineNumber][1]) &&
                player.equals(table[lineNumber][2]));
    }

    private boolean threeInColumn(String player, int columnNumber) {
        return (player.equals(table[0][columnNumber]) &&
                player.equals(table[1][columnNumber]) &&
                player.equals(table[2][columnNumber]));
    }

    private boolean threeOnFirstDiagonal(String player) {
        return (player.equals(table[0][0]) &&
                player.equals(table[1][1]) &&
                player.equals(table[2][2]));
    }

    private boolean threeOnSecondDiagonal(String player) {
        return (player.equals(table[0][2]) &&
                player.equals(table[1][1]) &&
                player.equals(table[2][0]));
    }

    private boolean isWinner(String player) {
	    for (int i=0; i<3; i++) {
	        if (threeInLine(player, i) || threeInColumn(player, i)) {
	            return true;
            }
        }
        return (threeOnFirstDiagonal(player) || threeOnSecondDiagonal(player));
    }

    private boolean fullTable() {
	    for (int i=0; i<3; i++) {
	        for (int j=0; j<3; j++) {
	            if(!"x".equals(table[i][j]) && !"0".equals(table[i][j])) {
	                return false;
                }
            }
        }
        return true;
    }

}
