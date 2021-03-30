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
	
	//M�todo abstrato pois a pe�a � um tipo generico, cada pe�a tem seu movimento particular 
	public abstract boolean [][] possibleMoves();
	
	// Opera��o para verificar se uma pe�a pode ser movida para uma dada posi��o
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Opera��o para verificar se existe um movimento poss�vel para essa pe�a
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
