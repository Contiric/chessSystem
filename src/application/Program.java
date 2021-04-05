package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		
		while (true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch);
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				//Ap�s o usu�rio escolher a pe�a de in�cio o sistema vai verificar e mostar quais s�o as jogadas poss�veis
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getpieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
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
	}

}
