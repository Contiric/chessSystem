package chess.pieces;

import boardGame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	
	public String toString() {
		return "K";	
	}
	
	@Override
	//Criamos uma matriz de booleanos da mesma dimens�o do tabuleiro para as poss�veis trocas de posi��es
	public boolean [][] possibleMoves() {
		boolean [][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		return null;
	}

}
