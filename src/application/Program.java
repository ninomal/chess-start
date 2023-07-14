package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch ChessMatch = new ChessMatch();
		
		while(true) {
			
			UI.printBord(ChessMatch.getPieces());
			System.out.println();
			System.out.printf("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
				
			System.out.println();
			System.out.printf("target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = ChessMatch.performChessMove(source, target);
		}
	}

}
