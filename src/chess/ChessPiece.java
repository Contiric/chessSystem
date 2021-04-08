package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	//Coverter matriz para xadrez
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//Opera��o para verificar se existe uma pe�a advers�ria. O m�todo est� protected para somente dar acesso as sublaclasse King, Rook,  etc..
	protected boolean isThereOpponentPiece (Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color; 
	}
	
	

}
