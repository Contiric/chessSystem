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
		
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		// Caso aconteça o xeque mate, o programa automaticamente vai ser finalizado informando o vencedor.
		while (!chessMatch.getcheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				//Após o usuário escolher a peça de início o sistema vai verificar e mostar quais são as jogadas possíveis
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getpieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion (B/N/R/Q):");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && type.equals("N") && !type.equals("R") & type.equals("Q")) {
						System.out.println("Invalid Value! Enter piece for promotion (B/N/R/Q):");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch (ChessException e ) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
			catch (InputMismatchException e ) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}

}
