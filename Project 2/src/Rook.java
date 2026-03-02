public class Rook {
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(char character, int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if(board.verifyVertical(this.row, this.col, endRow, endCol)) {
                return true;
            } else if(board.verifyHorizontal(this.row, this.col, endRow, endCol)){
                return true;
            }
        }
        return false;
    }
}
