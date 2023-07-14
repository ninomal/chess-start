package chess.pieces;

import boardlayer.Bord;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Bord bord, Color color) {
		super(bord, color);	
	}
	
	@Override
	public String toString(){
		return "K";
	}

	@Override
	public boolean[][] possiblesMoves() {
		boolean [][] mat = new boolean[getBord().getRows()][getBord().getColums()];
		return mat;
	}
}
