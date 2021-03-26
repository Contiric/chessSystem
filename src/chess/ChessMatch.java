package chess;

import boardGame.Board;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
		// Chamando o método para inserção das peças assim que for criado o tabuleiro
		initialSetup();
	}
	
	public ChessPiece[][] getpieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i <board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] =  (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// Método responsável por colocar as peças no tabuleiro
	public void initialSetup() {
		placeNewPiece('b', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 4, new King(board, Color.BLACK));
		placeNewPiece('a', 3, new King(board, Color.WHITE));
	}
}