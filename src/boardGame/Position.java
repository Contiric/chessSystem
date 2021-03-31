package boardGame;

public class Position {
	
	protected int row;
	protected int column;
	
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getColumn() {
		return column;
	}


	public void setColumn(int column) {
		this.column = column;
	}
	
	// Posi��o para atualizar os valores de uma opera��o. Verificar m�todo possibleMoves() na classe Rook.
 	public void setValues (int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		
		return row + "," + column;
	}
	

}
