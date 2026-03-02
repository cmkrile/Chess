import java.util.Scanner;

public class Board {

    // Instance variables
    private final Piece[][] board;
    private char character;
    private int cellCount;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        int row = 8;
        int col = 8;
        board = new Piece[row][col];
        this.cellCount = row*col;
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at  a given row and column
    public Piece getPiece(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board.length) {
            return board[row][col];
        } else {
            return null;
        }
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods
    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece startPiece = getPiece(startRow, startCol);
        Piece endPiece = getPiece(endRow, endCol);
        if (endPiece == null || startPiece.getIsBlack() != endPiece.getIsBlack()) {
            if (startPiece != null && startPiece.isMoveLegal(this, endRow, endCol)) {
                setPiece(endRow, endCol, startPiece);
                startPiece.setPosition(endRow, endCol);
                setPiece(startRow, startCol, null);
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int wKing = 0;
        int bKing = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece myPiece = board[i][j];
                if (myPiece != null) {
                    if (myPiece.toString().equals("♚")) {
                        bKing++;
                    }
                    if (myPiece.toString().equals("♔")) {
                        wKing++;
                    }
                }
            }
        }
        if (wKing != 1) {
            System.out.println("Black Wins!");
            return true;
        }
        if (bKing != 1) {
            System.out.println("White Wins!");
            return true;
        }
        return false;
    }
    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions
    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        Piece startPiece = getPiece(startRow, startCol);
        Piece endPiece = getPiece(endRow, endCol);
        if (startRow >= 0 && endRow < board.length && startCol >= 0 && endCol < board.length) {
            if (endRow >= 0 && startRow < board.length && endCol >= 0 && startCol < board.length) {
                if (startPiece != null) {
                    if (startPiece.getIsBlack() == isBlack) {
                        if (endPiece == null || endPiece.getIsBlack() != isBlack) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int finalRow = Math.abs(endRow - startRow);
        int finalCol = Math.abs(endCol - startCol);
        if ((startRow == endRow && startCol == endCol) || (finalRow == 1 && finalCol == 0)
                || (finalRow == 0 && finalCol == 1) || (finalRow == 1 && finalCol == 1)) {
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            if (startCol < endCol) {
                for (int i = startCol + 1; i < endCol; i++) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
            }
            if (startCol > endCol) {
                for (int i = startCol - 1; i > endCol; i--) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        int minRow = Math.min(startRow, endRow);
        int maxRow = Math.max(startRow, endRow);
        if (startRow == endRow && startCol == endCol) {
            return true;
        }
        if (startCol == endCol) {
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowIncrement;
        int colIncrement;
        if (Math.abs(endRow - startRow) != Math.abs(endCol - startCol)) {
            return false;
        }
        if (endRow - startRow > 0) {
            rowIncrement = 1;
        } else {
            rowIncrement = -1;
        }
        if (endCol - startCol > 0) {
            colIncrement = 1;
        } else {
            colIncrement = -1;
        }
        for (int i = 1; i < Math.abs(endRow - startRow); i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }

}

