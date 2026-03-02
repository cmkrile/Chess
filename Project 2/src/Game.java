import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        Piece myPiece;
        //Initializes board and sets all pieces with corresponding toString symbol
        char blackPawn = '♟';
        char blackKnight = '♞';
        char blackBishop = '♝';
        char blackRook = '♜';
        char blackQueen = '♛';
        char blackKing = '♚';
        char whitePawn = '♙';
        char whiteKnight = '♘';
        char whiteBishop = '♗';
        char whiteRook = '♖';
        char whiteQueen = '♕';
        char whiteKing = '♔';
        Scanner scan = new Scanner(System.in);
        Board board = new Board();

        //Setting black pieces
        //Setting Black Knights
        Piece bk = new Piece(blackKnight,0,1,true);
        Piece bk2 = new Piece(blackKnight,0,6,true);
        board.setPiece(0,1,bk);
        board.setPiece(0,6,bk2);

        //Setting Black Pawns
        Piece bp = new Piece(blackPawn, 1,0,true);
        Piece bp2 = new Piece(blackPawn, 1,1,true);
        Piece bp3 = new Piece(blackPawn, 1,2,true);
        Piece bp4 = new Piece(blackPawn, 1,3,true);
        Piece bp5 = new Piece(blackPawn, 1,4,true);
        Piece bp6 = new Piece(blackPawn, 1,5,true);
        Piece bp7 = new Piece(blackPawn, 1,6,true);
        Piece bp8 = new Piece(blackPawn, 1,7,true);
        board.setPiece(1,0,bp);
        board.setPiece(1,1,bp2);
        board.setPiece(1,2,bp3);
        board.setPiece(1,3,bp4);
        board.setPiece(1,4,bp5);
        board.setPiece(1,5,bp6);
        board.setPiece(1,6,bp7);
        board.setPiece(1,7,bp8);

        //Setting Black Bishops
        Piece bb = new Piece(blackBishop,0,2,true);
        Piece bb2 = new Piece(blackBishop,0,5,true);
        board.setPiece(0,2,bb);
        board.setPiece(0,5,bb2);

        //Setting Black Rooks
        Piece br = new Piece(blackRook, 0,0,true);
        Piece br2 = new Piece(blackRook, 0,7,true);
        board.setPiece(0,0,br);
        board.setPiece(0,7,br2);

        //Setting Black Queen
        Piece bq = new Piece(blackQueen, 0,3,true);
        board.setPiece(0,3,bq);

        //Setting Black King
        Piece bking = new Piece(blackKing, 0,4,true);
        board.setPiece(0,4,bking);
        //Setting white pieces
        //Setting White Pawns
        Piece wp = new Piece(whitePawn, 6,0,false);
        Piece wp2 = new Piece(whitePawn, 6,1,false);
        Piece wp3 = new Piece(whitePawn, 6,2,false);
        Piece wp4 = new Piece(whitePawn, 6,3,false);
        Piece wp5 = new Piece(whitePawn, 6,4,false);
        Piece wp6 = new Piece(whitePawn, 6,5,false);
        Piece wp7 = new Piece(whitePawn, 6,6,false);
        Piece wp8 = new Piece(whitePawn, 6,7,false);
        board.setPiece(6,0,wp);
        board.setPiece(6,1,wp2);
        board.setPiece(6,2,wp3);
        board.setPiece(6,3,wp4);
        board.setPiece(6,4,wp5);
        board.setPiece(6,5,wp6);
        board.setPiece(6,6,wp7);
        board.setPiece(6,7,wp8);

        //Setting White Knights
        Piece wk = new Piece(whiteKnight,7,1,false);
        Piece wk2 = new Piece(whiteKnight,7,6,false);
        board.setPiece(7,1,wk);
        board.setPiece(7,6,wk2);

        //Setting White Bishops
        Piece wb = new Piece(whiteBishop,7,2,false);
        Piece wb2 = new Piece(whiteBishop,7,5,false);
        board.setPiece(7,2,wb);
        board.setPiece(7,5,wb2);

        //Setting White Rooks
        Piece wr = new Piece(whiteRook, 7,0,false);
        Piece wr2 = new Piece(whiteRook, 7,7,false);
        board.setPiece(7,0,wr);
        board.setPiece(7,7,wr2);

        //Setting White Queen
        Piece wq = new Piece(whiteQueen, 7,3,false);
        board.setPiece(7,3,wq);

        //Setting White King
        Piece wking = new Piece(whiteKing, 7,4,false);
        board.setPiece(7,4,wking);

        //While loop for playing the game until white or black king is "taken"
        int counter = 1;
        int blackRow = 0;
        int whiteRow = 7;
        int cellCount = 8*8;
        while(!board.isGameOver()) {
            System.out.println(board);
            System.out.println(cellCount);
            if(counter % 2 != 0) {
                System.out.println("White's Turn!");
                System.out.println("What's your move? [Start Row] [Start Column] [End Row] [End Column]");
                int startRow = scan.nextInt();
                int startCol = scan.nextInt();
                int endRow = scan.nextInt();
                int endCol = scan.nextInt();
                myPiece = board.getPiece(startRow, startCol);
                if (myPiece != null && !myPiece.getIsBlack()) {
                    if (board.movePiece(startRow, startCol, endRow, endCol)) {
                        System.out.println("Move successful!");
                        myPiece = board.getPiece(endRow, endCol);
                        myPiece.promotePawn(endRow, false);
                        counter++;
                        cellCount--;
                    } else {
                        System.out.println("Invalid move.");
                    }
                } else {
                    System.out.println("Invalid move.");
                }
            } else {
                System.out.println("Black's Turn!");
                System.out.println("What's your move? [Start Row] [Start Column] [End Row] [End Column]");
                int startRow = scan.nextInt();
                int startCol = scan.nextInt();
                int endRow = scan.nextInt();
                int endCol = scan.nextInt();
                myPiece = board.getPiece(startRow, startCol);
                if (myPiece != null && myPiece.getIsBlack()) {
                    if (board.movePiece(startRow, startCol, endRow, endCol)) {
                        System.out.println("Move successful!");
                        myPiece.promotePawn(endRow, true);
                        counter++;
                    } else {
                        System.out.println("Invalid move.");
                    }
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }
        System.out.println(board);
        System.out.println("Game over");
    }
}
