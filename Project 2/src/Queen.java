public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(char character, int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                return true;
            } else if(board.verifyVertical(this.row, this.col, endRow, endCol)) {
                return true;
            } else if(board.verifyHorizontal(this.row, this.col, endRow, endCol)){
                return true;
            }
        }
        return false;
    }

}