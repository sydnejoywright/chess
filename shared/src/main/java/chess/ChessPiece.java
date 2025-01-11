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
            validMoves.addAll(getBishopPositions(board, myPosition));
        }
        
        return validMoves;
    }


//    public void bishopsHelper(ChessBoard board, ChessPosition testPosition, ChessPosition currentRow, ChessPosition currentCol) {
//        Collection<ChessMove> intValidMoves=new ArrayList<>();
//        //these are the coordinates of the space that I want to test.
//        int row = testPosition.getRow();
//        int col = testPosition.getColumn();
//
//        ChessPiece current = board[currentRow][currentCol];
//
//        ChessPiece piece = board[row][col];
//        if(piece.getPieceType() != null) {
//            //if there is a piece we need to check whether it is the same team or not.
//            if (piece.getTeamColor() != current.getTeamColor) {
//                int
//                intValidMoves.add({row, col});
//            } else () {
//                break;
//            }
//        }
//        //if there is no piece already in that space we can go ahead and add it as a valid move
//        else(){
//            intValidMoves.add({row, col});
//            //continue the while loop until we find another piece or hit a boundary
//            continue;
//        }
//    }

    public Collection<ChessMove> getBishopPositions(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //check upper right
            //check bounds
                while(row < 8 && col < 8){
                    ChessPiece current = board.getPiece(myPosition);
                    row ++;
                    col ++;
                    //increment one up and to the right and check if there is already a piece there
                    ChessPosition newPosition = new ChessPosition(row,col);
                    ChessPiece piece = board.getPiece(newPosition);
                    if(piece.getPieceType(board, myPosition) != null) {
                        //if there is a piece we need to check whether it is the same team or not.
                        if (piece.getTeamColor() != current.getTeamColor()) {
                            ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                            validMoves.add(possibleMove);
                        }
                      break;
                    }
                    //if there is no piece already in that space we can go ahead and add it as a valid move
                    else {
                        ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        validMoves.add(possibleMove);
                        //continue the while loop until we find another piece or hit a boundary
                    }
                }

        // check upper left
            while(row < 8 && col > 1){
                ChessPiece current = board.getPiece(myPosition);
                row ++;
                col --;

                ChessPosition newPosition = new ChessPosition(row,col);
                ChessPiece piece = board.getPiece(newPosition);
                if(piece.getPieceType(board, myPosition) != null) {
                    //if there is a piece we need to check whether it is the same team or not.
                    if (piece.getTeamColor() != current.getTeamColor()) {
                        ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        validMoves.add(possibleMove);
                    }
                    break;
                }
                //if there is no piece already in that space we can go ahead and add it as a valid move
                else {
                    ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                    validMoves.add(possibleMove);
                    //continue the while loop until we find another piece or hit a boundary
                }
            }


        //check lower right
            while(row > 1 && col < 8){
                ChessPiece current = board.getPiece(myPosition);
                row --;
                col ++;
                ChessPosition newPosition = new ChessPosition(row,col);
                ChessPiece piece = board.getPiece(newPosition);
                if(piece.getPieceType(board, myPosition) != null) {
                    //if there is a piece we need to check whether it is the same team or not.
                    if (piece.getTeamColor() != current.getTeamColor()) {
                        ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        validMoves.add(possibleMove);
                    }
                    break;
                }
                //if there is no piece already in that space we can go ahead and add it as a valid move
                else {
                    ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                    validMoves.add(possibleMove);
                    //continue the while loop until we find another piece or hit a boundary
                }
            }

        //check lower left
            while(row > 1 && col > 1){
                ChessPiece current = board.getPiece(myPosition);
                row --;
                col --;

                ChessPosition newPosition = new ChessPosition(row,col);
                ChessPiece piece = board.getPiece(newPosition);
                if(piece.getPieceType(board, myPosition) != null) {
                    //if there is a piece we need to check whether it is the same team or not.
                    if (piece.getTeamColor() != current.getTeamColor()) {
                        ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        validMoves.add(possibleMove);
                    }
                    break;
                }
                //if there is no piece already in that space we can go ahead and add it as a valid move
                else {
                    ChessMove possibleMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                    validMoves.add(possibleMove);
                    //continue the while loop until we find another piece or hit a boundary
                }
            }
        
        return validMoves;
    }

}