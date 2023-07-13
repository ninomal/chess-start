package chess;

import boardlayer.Bord;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Bord bord;

	public ChessMatch() {
		bord = new Bord(8, 8);
		initialSetup();
	}

	public void placeNewPiece(char column, int rows, ChessPiece piece) {
		bord.placePiece(piece, new ChessPosition(column, rows).toPosition());
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[bord.getRows()][bord.getColums()];
		for (int row = 0; row < bord.getRows(); row++) {
			for (int column = 0; column < bord.getColums(); column++) {
				mat[row][column] = (ChessPiece) bord.piece(row, column);
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
