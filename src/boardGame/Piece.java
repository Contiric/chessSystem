package boardGame;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;		
	}

	protected Board getBoard() {
		return board;
	}
	
	//Método abstrato pois a peça é um tipo generico, cada peça tem seu movimento particular 
	public abstract boolean [][] possibleMoves();
	
	// Operação para verificar se uma peça pode ser movida para uma dada posição
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Operação para verificar se existe um movimento possível para essa peça
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for (int i =0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
