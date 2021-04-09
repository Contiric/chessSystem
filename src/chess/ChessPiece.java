package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;
	

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getmoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	//Coverter matriz para xadrez
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//Operação para verificar se existe uma peça adversária. O método está protected para somente dar acesso as sublaclasse King, Rook,  etc..
	protected boolean isThereOpponentPiece (Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color; 
	}
	
	

}
