package boardlayer;

public class Position {
	int row;
	int column;
	
	public Position(){
		
	}

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	
	public int getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColum(int column) {
		this.column = column;
	}

	public void setValues(int row, int column){
		this.column = column;
		this.row = row;
	}
	
	@Override
	public String toString(){
		return row + "," + column;
	}
	
	
}
