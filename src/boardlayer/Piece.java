package boardlayer;

public abstract class Piece {
	protected Position position;
	private Bord bord;
	
	public Piece(Bord bord) {
		this.bord = bord;

	}

	protected Bord getBord() {
		return bord;
	}

	public abstract boolean[][] possiblesMoves();
	
	public boolean possiblesMove(Position position){
		return possiblesMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove(){
		boolean[][] mat = possiblesMoves();
		for (int i = 0 ; i < mat.length; i++){
			for (int j = 0; j < mat.length; j ++){
				if (mat[i][j]){
					return true;
				}
			}
		}
		return false;
	}


}
