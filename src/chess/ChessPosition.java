package chess;

import boardlayer.Position;

public class ChessPosition {
	private char column;
	private int rows;
	
	public ChessPosition(char column, int rows) {
		if (rows < 1 || rows > 8 || column < 'A' || column > 'H'){
			throw new ChessException("Error instance chess position , valid values A1 to H8");
		}
		this.column = column;
		this.rows = rows;
	}

	public char getColumn() {
		return column;
	}


	public int getRows() {
		return rows;
	}

	protected Position toPosition(){
		return new Position(8 - rows , column - 'A');
	}
	
	protected static ChessPosition fromPosition(Position position){
		return new ChessPosition((char)('A' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString(){
		return ""+ column + rows;
	}
	
	
}
