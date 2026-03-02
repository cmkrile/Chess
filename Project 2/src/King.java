public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(char character, int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
                return true;
            }
        }
            return false;
    }
}

