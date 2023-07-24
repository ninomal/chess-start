package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardlayer.Bord;
import boardlayer.Piece;
import boardlayer.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
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

	public boolean getCheckMate() {
		return checkMate;
	}

	public int getTurn() {
		return turn;
	}

	public boolean getCheck() {
		return check;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validadeSourcePosition(source);
		validateTargetPosition(source, target);
		Piece captured = makeMove(source, target);

		if (testCheck(currentPlayer)) {
			undoMove(source, target, captured);
			throw new ChessException("not alow put your self in check");
		}

		check = (testCheck(oponent(currentPlayer))) ? true : false;

		if (testCheckMate(oponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();
		}

		return (ChessPiece) captured;
	}

	private Piece makeMove(Position source, Position target) {
		ChessPiece pi = (ChessPiece) bord.removePiece(source);
		pi.increMoveCount();
		Piece capturedPiece = bord.removePiece(target);
		bord.placePiece(pi, target);

		if (capturedPiece != null) {
			piecesOnTheBord.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		// #specialmove castling kingside rook
		if (pi instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) bord.removePiece(sourceT);
			bord.placePiece(rook, targetT);
			rook.increMoveCount();
		}

		// #specialmove castling queenside rook
		if (pi instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) bord.removePiece(sourceT);
			bord.placePiece(rook, targetT);
			rook.increMoveCount();
		}

		return capturedPiece;
	}

	public void undoMove(Position source, Position target, Piece captured) {
		ChessPiece p = (ChessPiece) bord.removePiece(target);
		p.descreseMoveCount();
		bord.placePiece(p, source);

		if (captured != null) {
			bord.placePiece(captured, target);
			capturedPieces.remove(captured);
			piecesOnTheBord.add(captured);
		}

		// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) bord.removePiece(targetT);
			bord.placePiece(rook, sourceT);
			rook.descreseMoveCount();
		}

		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) bord.removePiece(targetT);
			bord.placePiece(rook, sourceT);
			rook.descreseMoveCount();
		}

	}

	public boolean[][] possiblesMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validadeSourcePosition(position);
		return bord.piece(position).possiblesMoves();
	}

	private void validadeSourcePosition(Position position) {
		if (!bord.thereIsAPiece(position)) {
			throw new ChessException("There no piece in source position");
		}
		if (currentPlayer != ((ChessPiece) (bord.piece(position))).getColor()) {
			throw new ChessException("This piece not yours");
		}

		if (!bord.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("No have any movent for this piece");
		}
	}

	public void validateTargetPosition(Position source, Position target) {
		if (!bord.piece(source).possiblesMove(target)) {
			throw new ChessException("Moviment not possible");
		}
	}

	public Color oponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
		List<Piece> list = piecesOnTheBord.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> oponentPieces = piecesOnTheBord.stream().filter(x -> ((ChessPiece) x).getColor() == oponent(color))
				.collect(Collectors.toList());
		for (Piece p : oponentPieces) {
			boolean[][] mat = p.possiblesMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBord.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possiblesMoves();
			for (int i = 0; i < bord.getRows(); i++) {
				for (int j = 0; j < bord.getColums(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece captured = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, captured);
						if (!testCheck) {

							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public void placeNewPiece(char column, int rows, ChessPiece piece) {
		bord.placePiece(piece, new ChessPosition(column, rows).toPosition());
		piecesOnTheBord.add(piece);
	}

	public void initialSetup() {
		placeNewPiece('E', 1, new King(bord, Color.WHITE, this));
		placeNewPiece('D', 1, new Queen(bord, Color.WHITE));
		placeNewPiece('C', 1, new Bishop(bord, Color.WHITE));
		placeNewPiece('F', 1, new Bishop(bord, Color.WHITE));
		placeNewPiece('B', 1, new Knight(bord, Color.WHITE));
		placeNewPiece('G', 1, new Knight(bord, Color.WHITE));
		placeNewPiece('A', 1, new Rook(bord, Color.WHITE));
		placeNewPiece('H', 1, new Rook(bord, Color.WHITE));
		placeNewPiece('A', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('B', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('C', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('D', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('E', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('F', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('G', 2, new Pawn(bord, Color.WHITE, this));
		placeNewPiece('H', 2, new Pawn(bord, Color.WHITE, this));

		placeNewPiece('E', 8, new King(bord, Color.BLACK, this));
		placeNewPiece('D', 8, new Queen(bord, Color.BLACK));
		placeNewPiece('C', 8, new Bishop(bord, Color.BLACK));
		placeNewPiece('F', 8, new Bishop(bord, Color.BLACK));
		placeNewPiece('B', 8, new Knight(bord, Color.BLACK));
		placeNewPiece('G', 8, new Knight(bord, Color.BLACK));
		placeNewPiece('A', 8, new Rook(bord, Color.BLACK));
		placeNewPiece('H', 8, new Rook(bord, Color.BLACK));
		placeNewPiece('A', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('B', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('C', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('D', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('E', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('F', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('G', 7, new Pawn(bord, Color.BLACK, this));
		placeNewPiece('H', 7, new Pawn(bord, Color.BLACK, this));

	}

}
