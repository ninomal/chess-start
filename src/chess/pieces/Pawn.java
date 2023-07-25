package chess.pieces;

import boardlayer.Bord;
import boardlayer.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Bord board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possiblesMoves() {
		boolean[][] mat = new boolean[getBord().getRows()][getBord().getColums()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBord().positionExists(p) && !getBord().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBord().positionExists(p) && !getBord().thereIsAPiece(p) && getBord().positionExists(p2)
					&& !getBord().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBord().positionExists(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBord().positionExists(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBord().positionExists(left) && isThereOponentPiece(left)
						&& getBord().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBord().positionExists(right) && isThereOponentPiece(right)
						&& getBord().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		}

		else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBord().positionExists(p) && !getBord().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBord().positionExists(p) && !getBord().thereIsAPiece(p) && getBord().positionExists(p2)
					&& !getBord().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBord().positionExists(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBord().positionExists(p) && isThereOponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBord().positionExists(left) && isThereOponentPiece(left)
						&& getBord().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBord().positionExists(right) && isThereOponentPiece(right)
						&& getBord().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}

		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}