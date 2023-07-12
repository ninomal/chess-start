package boardlayer;

public class Position {
	Integer row;
	Integer colum;
	
	public Position(){
		
	}

	public Position(Integer row, Integer colum) {
		this.row = row;
		this.colum = colum;
	}

	
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColum() {
		return colum;
	}

	public void setColum(Integer colum) {
		this.colum = colum;
	}

	public void setValues(Integer row, Integer colum){
		this.colum = colum;
		this.row = row;
	}
	
	@Override
	public String toString(){
		return row + "," + colum;
	}
	
}
