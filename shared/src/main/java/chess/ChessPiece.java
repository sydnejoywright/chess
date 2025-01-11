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
    public PieceType getPieceType() {
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
        PieceType piece = getPieceType();
        if (piece == PieceType.BISHOP) {
            validMoves.addAll(getBishopPositions(board, myPosition));
        }
        
        return validMoves;
    }


    public Boolean progressBishopOrStop(ChessBoard board, ChessPosition newPosition, ChessPosition myPosition, Collection<ChessMove> validMoves) {
        ChessPiece piece = board.getPiece(newPosition);
        if(piece != null) {
            //if there is a piece we need to check whether it is the same team or not.
            if (piece.getTeamColor() != this.getTeamColor()) {
                ChessMove possibleMove = new ChessMove(myPosition, newPosition, null);
                validMoves.add(possibleMove);
            }
            return false;
        }
        //if there is no piece already in that space we can go ahead and add it as a valid move
        else {
            ChessMove possibleMove = new ChessMove(myPosition, newPosition, null);
            validMoves.add(possibleMove);
            return true;
            //continue the while loop until we find another piece or hit a boundary
        }
    }

    public Collection<ChessMove> getBishopPositions(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

    //check upper right
        //check bounds
            while(row < 8 && col < 8){
                row ++;
                col ++;
                ChessPosition newPosition = new ChessPosition(row,col);
                if(!progressBishopOrStop(board, newPosition, myPosition, validMoves)){
                    break;
                }
            }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        // check upper left
            while(row < 8 && col > 1){
                row ++;
                col --;
                ChessPosition newPosition = new ChessPosition(row,col);
                if(!progressBishopOrStop(board, newPosition, myPosition, validMoves)){
                    break;
                }
            }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        //check lower right
            while(row > 1 && col < 8){
                row --;
                col ++;
                ChessPosition newPosition = new ChessPosition(row,col);
                if(!progressBishopOrStop(board, newPosition, myPosition, validMoves)){
                    break;
                }
            }

        row = myPosition.getRow();
        col = myPosition.getColumn();

        //check lower left
            while(row > 1 && col > 1){
                row --;
                col --;
                ChessPosition newPosition = new ChessPosition(row,col);
                if(!progressBishopOrStop(board, newPosition, myPosition, validMoves)){
                    break;
                }
            }
        
        return validMoves;
    }

}