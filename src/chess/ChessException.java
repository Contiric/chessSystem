package chess;

import boardGame.BoardException;

// Simplificar as exce��es dando acesso n�o somente a Chess Exception como a BoardException
public class ChessException extends BoardException {

	private static final long serialVersionUID = 1L;
	
	public ChessException (String msg) {
		super(msg);
	}

}
