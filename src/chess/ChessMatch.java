package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	

	public ChessMatch() {
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		// Chamando o m�todo para inser��o das pe�as assim que for criado o tabuleiro
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck () {
		return check;
	}
	
	public boolean getcheckMate() {
		return checkMate;
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
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can�t put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
		
		nextTurn();
		
		}
		return (ChessPiece) capturedPiece;
 	}
	
	//
	private Piece makeMove(Position source, Position target) {
		// Retirou a pe�a da origem 
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		//Remover a poss�vel pe�a que esteja na posi��o de destino
		Piece capturedPiece = board.removePiece(target);
		//Colocar a posi��o de origem na de destino
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPice) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		board.placePiece(p, source);
		p.decreaseMoveCount();
		if (capturedPice != null) {
			board.placePiece(capturedPice, target);
			capturedPieces.remove(capturedPice);
			piecesOnTheBoard.add(capturedPice);
		}
	}
	
	// Se n�o tive uma pe�a nessa posi��o vai ser lan�ada uma exce��o
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");	
		}
		//Pego a pe�a do tabuleiro na posi�ao, fa�o o downcasting pra ChessPiece e testo a cor dela. Se a cor for diferente do jogador atual, a pe�a � do advers�rio 
		if (currentPlayer !=((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours.");
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
	
	//M�todo para trocar turno
	private void nextTurn() {
		turn ++;
		// Se o jogador atual for a Color.White ent�o agora ele vai ser Color.Black. Caso contr�rio vai ser Color.White
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent (Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king (Color color) {
		List <Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == (color)).collect(Collectors.toList());
		for (Piece p : list) {
		boolean [][] mat = p.possibleMoves();
		for (int i=0; i < board.getRows(); i++) {
			for (int j=0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position (i, j);
						Piece capturedPice = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPice);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	// Opera��o de colocar a pe�a passando uma posi��o nas coordernadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	// M�todo respons�vel por colocar as pe�as no tabuleiro
	// Passar as posi��es do xadrez
	public void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
      

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
       
	}
}		