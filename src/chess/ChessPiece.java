package chess;

import boardlayer.Bord;
import boardlayer.Piece;

public class ChessPiece extends Piece{
	private Color color;
	private Integer moveCount;
	
	public ChessPiece(Bord bord, Color color, Integer moveCount) {
		super(bord);
		this.color = color;
		this.moveCount = moveCount;
	}

	public Color getColor() {
		return color;
	}


	public Integer getMoveCount() {
		return moveCount;
	}

	
	
}
