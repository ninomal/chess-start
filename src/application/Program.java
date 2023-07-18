package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch ChessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(true) {
			try {
				UI.clearScreen();	
				UI.printMatch(ChessMatch, captured);
				System.out.println();
				System.out.printf("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = ChessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBord(ChessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.printf("target: ");
				ChessPosition target = UI.readChessPosition(sc);			
				ChessPiece capturedPiece = ChessMatch.performChessMove(source, target);
				if (capturedPiece != null){
					captured.add(capturedPiece);
				}
			}
			catch(ChessException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		
	}

}
