package chess;

import boardlayer.Bord;
import boardlayer.Piece;
import boardlayer.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Bord bord;

	public ChessMatch() {
		bord = new Bord(8, 8);
		initialSetup();
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validadeSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;		
	}
	
	public Piece makeMove(Position source, Position target){	
		Piece pi =  bord.removePiece(source);
		Piece capturedPiece = bord.removePiece(target);
		bord.placePiece(pi, target);
		return capturedPiece;	
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
		if (!bord.piece(position).isThereAnyPossibleMove()){
			throw new ChessException("No have any movent for this piece");
		}
	}
	
	public void validateTargetPosition(Position source, Position target){
		if(!bord.piece(source).possiblesMove(target)){
			throw new ChessException("Moviment not possible");
		}
	}
	
	public void placeNewPiece(char column, int rows, ChessPiece piece) {
		bord.placePiece(piece, new ChessPosition(column, rows).toPosition());
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
