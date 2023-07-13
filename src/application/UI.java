package application;

import chess.ChessPiece;

public class UI {
	public static void printBord(ChessPiece[][] piece){
		for(int row = 0 ; row < piece.length; row++ ){
			System.out.print(8 - row + " ");
			for(int column =0; column <piece.length; column++){
				printPiece(piece[row][column]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printPiece(ChessPiece piece){
		if (piece == null){
			System.out.print("-");
		}
		else{
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
}
