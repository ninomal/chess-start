package boardlayer;

public class Bord {
	Integer rows;
	Integer colums;
	Piece [][] piece;

	public Bord(Integer rows, Integer colums) {
		this.rows = rows;
		this.colums = colums;
		piece = new Piece[rows][colums];
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColums() {
		return colums;
	}

	public void setColums(Integer colums) {
		this.colums = colums;
	}

	
	
	
	
}
