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
        if (piece == PieceType.ROOK){
            validMoves.addAll(getRookPositions(board, myPosition));
        }
        if (piece == PieceType.QUEEN){
            validMoves.addAll(getQueenPositions(board, myPosition));
        }
        if (piece == PieceType.KING){
            validMoves.addAll(getKingPositions(board, myPosition));
        }
        if (piece == PieceType.KNIGHT){
            validMoves.addAll(getKnightPositions(board, myPosition));
        }
        if (piece == PieceType.PAWN){
            validMoves.addAll(getPawnPositions(board, myPosition));
        }
        
        return validMoves;
    }


    public Boolean progressPieceOrStop(ChessBoard board, ChessPosition newPosition, ChessPosition myPosition, Collection<ChessMove> validMoves) {
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

    public Collection<ChessMove> getPawnPositions(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row=myPosition.getRow();
        int col=myPosition.getColumn();
        //choose white team to start with
        if(getTeamColor() == ChessGame.TeamColor.WHITE) {
            //check if it's the first move for that pawn
            if(row == 2){
                //check if it can move forward 1
                row ++;
                ChessPosition firstPosition = new ChessPosition(row, col);
                ChessPiece piece = board.getPiece(firstPosition);
                if(piece == null) {
                    ChessMove firstMove = new ChessMove(myPosition, firstPosition, null);
                    validMoves.add(firstMove);

                    //check if it can move forward 2
                    row ++;
                    ChessPosition secondPosition = new ChessPosition(row, col);
                    ChessPiece secondPiece = board.getPiece(secondPosition);
                    if(secondPiece == null) {
                        ChessMove secondMove=new ChessMove(myPosition, secondPosition, null);
                        validMoves.add(secondMove);
                    }
                }
                //reset
                row =myPosition.getRow();

                //check if it can capture diagonally right (still assuming first move)
                row++;
                col++;
                //check bounds
                if (col < 8) {
                    //check if a piece is there
                    //if so, is it my same team
                    //if not i can capture that spot
                }

            //check if it can capture diagonally left
            }

        }




        //if not first move
        //check if it can move forward 1
        //check if it reached a final edge
        //check if it can capture right
        //check if it reached a final edge
        //check if it can capture left
        //check if it reached a final edge


        return validMoves;
    }


    public Collection<ChessMove> getKnightPositions(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row=myPosition.getRow();
        int col=myPosition.getColumn();


        //check up and right
        row +=2;
        col ++;
        if(row <= 8 && col <= 8){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check up and left
        row +=2;
        col --;
        if(row <= 8 && col >= 1){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check left and up
        row ++;
        col -=2;
        if(row <= 8 && col >= 1){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check left and down
        col -=2;
        row --;
        if(row >= 1 && col >= 1){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check right and up
        col +=2;
        row ++;
        if(row <= 8 && col <= 8){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check right and down
        col +=2;
        row --;
        if(row >= 1 && col <= 8){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check down and right
        row -=2;
        col ++;
        if(row >= 1 && col <= 8){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row=myPosition.getRow();
        col=myPosition.getColumn();

        //check down and left
        row -=2;
        col --;
        if(row >= 1 && col >= 1){
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }

        return validMoves;
    }
    public Collection<ChessMove> getKingPositions(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        // forward
        if(row < 8){
            row ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row = myPosition.getRow();

        // backward
        if(row > 1){
            row --;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row = myPosition.getRow();

        // right
        if(col < 8){
            col ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        col = myPosition.getColumn();

        // left
        if(col > 1){
            col --;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        col = myPosition.getColumn();

        // diagonal right and up
        if(col < 8 && row < 8){
            row ++;
            col ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row = myPosition.getRow();
        col = myPosition.getColumn();

        //diagonal right and down
        if(col < 8 && row > 1){
            col ++;
            row --;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row = myPosition.getRow();
        col = myPosition.getColumn();

        //diagonal left and up
        if(col > 1 && row < 8){
            col --;
            row ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }
        //reset
        row = myPosition.getRow();
        col = myPosition.getColumn();

        //diagonal left and down
        if(col > 1 && row > 1){
            col --;
            row --;
            ChessPosition newPosition = new ChessPosition(row,col);
            progressPieceOrStop(board, newPosition, myPosition, validMoves);
        }

        return validMoves;
    }
    public Collection<ChessMove> getQueenPositions(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> validMoves=new ArrayList<>();
        validMoves.addAll(getRookPositions(board, myPosition));
        validMoves.addAll(getBishopPositions(board, myPosition));
        return validMoves;
    }
    public Collection<ChessMove> getRookPositions(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> validMoves=new ArrayList<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        //check forward
        while(row < 8){
            row ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
                break;
            }
        }

        row = myPosition.getRow();
        col = myPosition.getColumn();
        //check backward
        while(row > 1){
            row --;
            ChessPosition newPosition = new ChessPosition(row,col);
            if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
                break;
            }
        }
        row = myPosition.getRow();
        col = myPosition.getColumn();
        //check right
        while(col < 8){
            col ++;
            ChessPosition newPosition = new ChessPosition(row,col);
            if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
                break;
            }
        }

        row = myPosition.getRow();
        col = myPosition.getColumn();
        //check left
        while(col > 1){
            col --;
            ChessPosition newPosition = new ChessPosition(row,col);
            if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
                break;
            }
        }
        return validMoves;
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
                if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
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
                if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
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
                if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
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
                if(!progressPieceOrStop(board, newPosition, myPosition, validMoves)){
                    break;
                }
            }
        
        return validMoves;
    }

}