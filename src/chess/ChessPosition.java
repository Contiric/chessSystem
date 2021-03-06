package chess;

import boardGame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	// Construtor verificando a extens?o das colunas atrav?s da programa??o defensiva 
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
	
	// Converter a posi??o do xadrez para a matriz
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	// Converter posi?ao da matriz para o xadrez
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}
	
	// Ex: a1, b2, ....
	// O " " ? usado para o complilador entender que ? uma concatena??o de String
	public String toString() {
		return " " + row + column;
	}
}
