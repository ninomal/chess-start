package boardlayer;

public class Position {
	Integer row;
	Integer column;
	
	public Position(){
		
	}

	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColum(Integer column) {
		this.column = column;
	}

	public void setValues(Integer row, Integer column){
		this.column = column;
		this.row = row;
	}
	
	@Override
	public String toString(){
		return row + "," + column;
	}
	
	
}
