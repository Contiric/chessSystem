package chess;

import boardGame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	// Construtor verificando a extensão das colunas através da programação defensiva 
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating chessPosition. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	// Converter a posição do xadrez para a matriz
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	// Converter posiçao da matriz para o xadrez
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	// Ex: a1, b2, ....
	// O " " é usado para o complilador entender que é uma concatenação de String
	public String toString() {
		return " " + row + column;
	}
}
