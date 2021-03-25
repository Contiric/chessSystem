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
	
	// M�todo respons�vel por colocar a pe�a no tabuleiro
	public void placePiece (Piece piece, Position position) {
		// Pegando a matriz de pe�as na posi��o dada e atribuindo a ela a pe�a que foi informada
		pieces[position.getRow()][position.getColumn()] = piece;
		// Informando que a pe�a n�o est� mais na posi��o nula
		piece.position = position;
	}

}
