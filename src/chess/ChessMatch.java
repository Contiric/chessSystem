package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
		// Chamando o m�todo para inser��o das pe�as assim que for criado o tabuleiro
		initialSetup();
	}
	
	public ChessPiece[][] getpieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i <board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] =  (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean [][] possibleMoves (ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	//Opera��o para retirar a pe�a do lugar de origem e colocar na posi��o de destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		// Validar posi��o de origem, se ela n�o existir vai lan�ar uma mensagem de exce��o
		validateSourcePosition(source);
		
		//Validar a posi��o de destino
		validateTargetPosition(source, target);
		
		//Opera��o respons�vel por realizar o movimento da pe�a
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
 	}
	
	//
	private Piece makeMove(Position source, Position target) {
		// Retirou a pe�a da origem 
		Piece p = board.removePiece(source);
		//Remover a poss�vel pe�a que esteja na posi��o de destino
		Piece capturedPiece = board.removePiece(target);
		//Colocar a posi��o de origem na de destino
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	// Se n�o tive uma pe�a nessa posi��o vai ser lan�ada uma exce��o
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");	
		}
		//Testando se tem algum movimento poss�vel para esta pe�a
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	//Se pra pe�a de origem o movimento n�o � poss�vel significa n�o posso mover minha pe�a
	private void validateTargetPosition (Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can�t  move to target position");
		}
	}
	
	// Opera��o de colocar a pe�a passando uma posi��o nas coordernadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// M�todo respons�vel por colocar as pe�as no tabuleiro
	// Passar as posi��es do xadrez
	public void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}