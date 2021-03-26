package boardGame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		// Tratamento de exce��o para verificar o tamanho do tabuleiro
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

	// M�todo retirado para indicar que o tabuleiro n�o pode ser alterado ap�s a
	// cria��o, programa��o defensiva
//	public void setRows(int rows) {
//		this.rows = rows;
//	}

	public int getColumns() {
		return columns;
	}

	// M�todo retirado para indicar que o tabuleiro n�o pode ser alterado ap�s a
	// cria��o, programa��o defensiva
//	public void setColumns(int columns) {
//		this.columns = columns;
//	}

	// Acessar uma pe�a que est� em uma determinada linha e coluna
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not found on the board");
		}
		return pieces[row][column];
	}

	// Acessar a posi��o da pe�a
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not found on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	//M�todo respons�vel por colocar a pe�a no tabuleiro numa dada posi��o
	public void placePiece(Piece piece, Position position) {
		
		// Verificar se existe uma pe�a nessa posi��o
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		
		// Pegando a matriz de pe�as na posi��o dada e atribuindo a ela a pe�a que foi informada
		
		pieces[position.getRow()][position.getColumn()] = piece;
		
		// Informando que a pe�a n�o est� mais na posi��o nula
		piece.position = position;
	}

	 
	
	/**
	 * M�todo para verificar se a posi��o existe atrav�s das linhas e colunas
	 */
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// M�todo para verificar se a posi��o existe passando a posi��o como argumento
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		
		//Verificar se essa posi��o existe
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}

}
