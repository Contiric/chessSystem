package boardGame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece (int row, int column) {
		return pieces [row][column];
	}
	
	public Piece piece (Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	// Método responsável por colocar a peça no tabuleiro
	public void placePiece (Piece piece, Position position) {
		// Pegando a matriz de peças na posição dada e atribuindo a ela a peça que foi informada
		pieces[position.getRow()][position.getColumn()] = piece;
		// Informando que a peça não está mais na posição nula
		piece.position = position;
	}

}
