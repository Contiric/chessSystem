package boardGame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		//Tratamento de exceção para verificar o tamanho do tabuleiro
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}
	
	// Método retirado para indicar que o tabuleiro não pode ser alterado após a criação, programação defensiva
//	public void setRows(int rows) {
//		this.rows = rows;
//	}

	public int getColumns() {
		return columns;
	}
	
	// Método retirado para indicar que o tabuleiro não pode ser alterado após a criação, programação defensiva
//	public void setColumns(int columns) {
//		this.columns = columns;
//	}
	
	// Acessar uma peça que está em uma determinada linha e coluna
	public Piece piece (int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not found on the board");
		}
		return pieces [row][column];
	}
	
	// Acessar a posição da peça
	public Piece piece (Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not found on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	// Método responsável por colocar a peça no tabuleiro
	public void placePiece (Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		// Pegando a matriz de peças na posição dada e atribuindo a ela a peça que foi informada
		pieces[position.getRow()][position.getColumn()] = piece;
		// Informando que a peça não está mais na posição nula
		piece.position = position;
	}
	
	// Método para verificar se a posição existe
	//Método auxiliar para acessar a linha e coluna 
	private boolean positionExists (int row, int column) {
		return row >= 0 && row < column && column >=0 && column < columns;
	}
	
	// Método para verificar se a posição existe
	public boolean positionExists (Position position) {
		return positionExists(position.getRow(), position.getColumn()); 
	}
	
	public boolean thereIsAPiece(Position position) {
		
		if (!positionExists(position)) {
			throw new BoardException("There is already a piece on position " +  position);
		}
		return piece(position) != null;
	}

}
