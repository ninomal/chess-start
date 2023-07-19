package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardlayer.Bord;
import boardlayer.Piece;
import boardlayer.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Bord bord;
	private Color currentPlayer;
	private int turn;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBord = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	
	public ChessMatch() {
		bord = new Bord(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public boolean checkMate(){
		return checkMate;
	}
	
	public int getTurn(){
		return turn;
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public Color getCurrentPlayer(){
		return currentPlayer;
	}
	
	public void nextTurn(){
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ?  Color.BLACK : Color.WHITE;
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validadeSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)){
			undoMove(source, target, capturedPiece);
			throw new ChessException("not alow put your self in check");
		}

		check = (testCheck(oponent(currentPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece) capturedPiece;		
	}
	
	
	public Piece makeMove(Position source, Position target){	
		Piece pi =  bord.removePiece(source);
		Piece capturedPiece = bord.removePiece(target);
		
		if(capturedPiece != null) {
			piecesOnTheBord.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		bord.placePiece(pi, target);
		return capturedPiece;	
	}
	
	public void undoMove(Position source, Position target, Piece captured){
		Piece p =  bord.removePiece(target);
		bord.placePiece(p, source);
		
		if(capturedPieces != null){
			capturedPieces.remove(captured);
			piecesOnTheBord.add(captured);
		}
		
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validadeSourcePosition(position);
		return bord.piece(position).possiblesMoves();
	}
	
	private void validadeSourcePosition(Position position){
		if (!bord.thereIsAPiece(position)){
			throw new ChessException("There no piece in source position");
		}
		if(currentPlayer !=((ChessPiece) (bord.piece(position))).getColor()){
			throw new ChessException("This piece not yours");
		}
			
		if (!bord.piece(position).isThereAnyPossibleMove()){
			throw new ChessException("No have any movent for this piece");
		}
	}
	
	public void validateTargetPosition(Position source, Position target){
		if(!bord.piece(source).possiblesMove(target)){
			throw new ChessException("Moviment not possible");
		}
	}
	
	public Color oponent(Color color){
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	public void placeNewPiece(char column, int rows, ChessPiece piece) {
		bord.placePiece(piece, new ChessPosition(column, rows).toPosition());
		piecesOnTheBord.add(piece);
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[bord.getRows()][bord.getColums()];
		for (int i = 0; i < bord.getRows(); i++) {
			for (int j = 0; j < bord.getColums(); j++) {
				mat[i][j] = (ChessPiece) bord.piece(i, j);
			}
		}
		return mat;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBord.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBord.stream().filter(x -> ((ChessPiece)x).getColor() == oponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possiblesMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	public void initialSetup() {
		placeNewPiece('E', 1, new King(bord, Color.WHITE));
		placeNewPiece('C', 1, new Rook(bord, Color.WHITE));
		placeNewPiece('C', 2, new Rook(bord, Color.WHITE));
		placeNewPiece('D', 2, new Rook(bord, Color.WHITE));
		placeNewPiece('E', 2, new Rook(bord, Color.WHITE));
		placeNewPiece('D', 1, new King(bord, Color.WHITE));
		placeNewPiece('E', 8, new King(bord, Color.BLACK));
		placeNewPiece('C', 7, new Rook(bord, Color.BLACK));
		placeNewPiece('C', 8, new Rook(bord, Color.BLACK));
		placeNewPiece('D', 7, new Rook(bord, Color.BLACK));
		placeNewPiece('E', 7, new Rook(bord, Color.BLACK));
		placeNewPiece('D', 8, new King(bord, Color.BLACK));
	}

}
