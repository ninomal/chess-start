package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch ChessMatch = new ChessMatch();
		 UI.printBord(ChessMatch.getPieces());

	}

}