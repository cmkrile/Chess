public class Knight {
    private final int row;
    private final int col;
    private final boolean isBlack;

    public Knight(char character, int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)){
            int finalRow = Math.abs(endRow - row);
            int finalCol = Math.abs(endCol - col);
            if((finalRow == 2 && finalCol == 1) || (finalRow == 1 && finalCol == 2)){
                return true;
            }
        }
        return false;
    }
    public boolean isBlack() {
        return isBlack;
    }
}
