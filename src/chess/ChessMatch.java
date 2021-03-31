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
		// Chamando o método para inserção das peças assim que for criado o tabuleiro
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
	
	//Operação para retirar a peça do lugar de origem e colocar na posição de destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		// Validar posição de origem, se ela não existir vai lançar uma mensagem de exceção
		validateSourcePosition(source);
		
		//Validar a posição de destino
		validateTargetPosition(source, target);
		
		//Operação responsável por realizar o movimento da peça
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
 	}
	
	//
	private Piece makeMove(Position source, Position target) {
		// Retirou a peça da origem 
		Piece p = board.removePiece(source);
		//Remover a possível peça que esteja na posição de destino
		Piece capturedPiece = board.removePiece(target);
		//Colocar a posição de origem na de destino
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	// Se não tive uma peça nessa posição vai ser lançada uma exceção
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");	
		}
		//Testando se tem algum movimento possível para esta peça
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}
	
	//Se pra peça de origem o movimento não é possível significa não posso mover minha peça
	private void validateTargetPosition (Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can´t  move to target position");
		}
	}
	
	// Operação de colocar a peça passando uma posição nas coordernadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// Método responsável por colocar as peças no tabuleiro
	// Passar as posições do xadrez
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