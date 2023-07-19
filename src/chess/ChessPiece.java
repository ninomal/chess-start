package chess;

import boardlayer.Bord;
import boardlayer.Piece;
import boardlayer.Position;

public abstract class ChessPiece extends Piece{
	private Color color;
	
	
	public ChessPiece(Bord bord, Color color) {
		super(bord);
		this.color = color;
		
	}

	public Color getColor() {
		return color;
	}

	protected boolean isThereOponentPiece(Position position){
		ChessPiece pi = (ChessPiece) getBord().piece(position);
		return pi!= null && pi.getColor() != color;
	} 
	
	public ChessPosition getChessPosition(){
		return ChessPosition.fromPosition(position);
	}
	
}
