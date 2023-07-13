package chess;

import boardlayer.Bord;
import boardlayer.Position;
import chess.pieces.King;

public class ChessMatch {
	private Bord bord;
	
	public  ChessMatch(){	
		bord = new Bord(8, 8);
		initialSetup();
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

	public void initialSetup() {
		bord.placePiece(new King(bord,Color.White),new Position(0,4));
	}

	
	
}
