package chess;

import boardlayer.Bord;
import boardlayer.Piece;

public class ChessPiece extends Piece{
	private Color color;
	
	
	public ChessPiece(Bord bord, Color color) {
		super(bord);
		this.color = color;
		
	}

	public Color getColor() {
		return color;
	}


	
	
}
