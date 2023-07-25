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
		
		while(!ChessMatch.getCheckMate()) {
			try {
				UI.clearScreen();	
				UI.printMatch(ChessMatch, captured);
				System.out.println();
				System.out.printf("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = ChessMatch.possiblesMoves(source);
				UI.clearScreen();
				UI.printBord(ChessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.printf("target: ");
				ChessPosition target = UI.readChessPosition(sc);			
				ChessPiece capturedPiece = ChessMatch.performChessMove(source, target);
				if (capturedPiece != null){
					captured.add(capturedPiece);
				}

				if (ChessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					ChessMatch.replacePromotedPiece(type);			
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
		UI.clearScreen();
		UI.printMatch(ChessMatch, captured);
		
	}

}
