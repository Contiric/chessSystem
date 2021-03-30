package chess;

import boardGame.BoardException;

// Simplificar as exceções dando acesso não somente a Chess Exception como a BoardException
public class ChessException extends BoardException {

	private static final long serialVersionUID = 1L;
	
	public ChessException (String msg) {
		super(msg);
	}

}
