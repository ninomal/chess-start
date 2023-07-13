package boardlayer;

public class Bord {
	int rows;
	int columns;
	Piece [][] piece;

	public Bord(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		piece = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColums() {
		return columns;
	}

	public void setColums(int columns) {
		this.columns = columns;
	}

	
	public  Piece piece(int rows, int columns){
		return piece[rows][columns];
	}
	
	public Piece piece(Position position) {
		return piece[position.getRow()][position.getColumn()];
	}
	
	public void placePiece( Piece pieces, Position position){
		piece[position.getRow()][position.getColumn()] = pieces;
		pieces.position = position;
	}
	
}
