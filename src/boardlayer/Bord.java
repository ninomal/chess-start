package boardlayer;

public class Bord {
	private int rows;
	private int columns;
	private Piece [][] pieces;

	public Bord(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BordException("error no allow table measure");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColums() {
		return columns;
	}
	
	public  Piece piece(int rows, int columns){
		if (!positionExists(rows, columns)){
			throw new BordException("Position not on in bord");
		}
		return pieces[rows][columns];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)){
			throw new BordException("Position not on in bord");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece( Piece piece, Position position){
		if (thereIsAPiece(position)){
			throw new BordException("there existe piece in position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position){
		if (!positionExists(position)){
			throw new BordException("Position not on in bord");
		}
		if (piece(position) == null){
			return null;
		}
		Piece aux =  piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;	
	}

	public boolean positionExists(int row, int column){
		return row >= 0 && row < rows && column >= 0 && column < columns;		
	}
	
	public boolean positionExists(Position position){
		return positionExists(position.getRow(), position.getColumn());		
	}
	
	public boolean thereIsAPiece(Position position){
		if (!positionExists(position)){
			throw new BordException("Position not on in bord");
		}
		return piece(position) != null;
	}
	
	
}
