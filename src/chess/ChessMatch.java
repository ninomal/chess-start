package chess;

import boardlayer.Bord;

public class ChessMatch {
	private Bord bord;
	
	public  ChessMatch(){	
		bord = new Bord(8, 8);		
	}
		
	public  ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[bord.getRows()][bord.getColums()];
		for(int row = 0; row < bord.getRows(); row++){
			for (int column =0; column < bord.getColums(); column++){
				mat[row][column] = (ChessPiece) bord.piece(row, column); 
			}
		}
		return mat;
		
	}


	
	
}
