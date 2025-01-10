package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType(ChessBoard board, ChessPosition position) {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //The array of valid moves that will be returned
        Collection<ChessMove> validMoves=new ArrayList<>();
        //Each piece type will call its own method with its own logic and return its respective valid moves.
        PieceType piece=getPieceType(board, myPosition);
        if (piece == PieceType.BISHOP) {
            validMoves.add(getBishopPositions(board, myPosition));
        }
        
        return validMoves;
    }

    public Boolean checkIfInBounds(int row, int col){
        if(row > 8){
            return false;
        }
        else if(row < 1){
            return false;
        }
        else if(col > 8){
            return false;
        }
        else return col >= 1;
    }

    public ChessMove getBishopPositions(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //check upper right
            //check bounds
                while(row < 8 && col < 8){
                    row += 1;
                    col += 1;
                    //increment one up and to the right and check if there is already a piece there
                    ChessPiece piece = board[row][col];
                    if(piece.getPieceType() != none){
                        //if there is a piece we need to check whether it is the same team or not.
                        if(){}
                    }
                    else(){
                        validMoves.add({row, col});
                        //continue the while loop until we find another piece or hit a boundary
                        continue;
                    }
                }



                add 1 to column and 1 to row
                check if those coordinates are in bounds,
                check if those coordinates are empty
                if both in bounds and empty add the coordinates to the valid moves
                continue until not empty
                check if it is same team, do not add that space as valid
                if it is other team, add the space as valid and quit traveling in that direction.



            if (checkIfInBounds(myPosition.getRow(), myPosition.getColumn())){

            }




        //check upper left
        //check lower right
        //check lower left
        
        return validMoves;
    }

}