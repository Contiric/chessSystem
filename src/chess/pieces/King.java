package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	// Adicionando uma dependência para a partida 
	private ChessMatch chessMatch;
	
	//Associar a partida com o Rei a partir do construtor
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	
	public String toString() {
		return "K";	
	}
	
	//Se o rei pode mover para determinada posipição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
		
	}
	
	//Método para verificar se na posição que eu informar existe uma torre e se erra torre está apta para Roque
	// Ela vai estar apta quando a quantidade de movimento for igual a 0
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p!= null && p instanceof Rook && p.getColor() == getColor() && p.getmoveCount() == 0;
	}
	
	@Override
	//Criamos uma matriz de booleanos da mesma dimensão do tabuleiro para as possíveis trocas de posições
	public boolean [][] possibleMoves() {
		boolean [][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//Em cima
		p.setValues(position.getRow() -1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;	
		}
		//Embaixo
			p.setValues(position.getRow() +1, position.getColumn());
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;		
			
		}	
			//Esquerda
			p.setValues(position.getRow(), position.getColumn()-1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;	
				
			
		}
			//Direita
			p.setValues(position.getRow(), position.getColumn()+1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;		
			
		}	
			//Noroeste (Diagonal)
			p.setValues(position.getRow() -1, position.getColumn()-1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;		
			
		}
			
			//Nordeste (Diagonal)
			p.setValues(position.getRow() -1, position.getColumn()+1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;		
			}
			
			//Sudoeste (Diagonal)
			p.setValues(position.getRow() +1, position.getColumn()-1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;	
			}
			
			//Sudeste (Diagonal)
			p.setValues(position.getRow() +1, position.getColumn()+1);
			if (getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;	
			}
			
			
			// Special move castling
			if (getmoveCount() == 0 && !chessMatch.getCheck()) {
				//Special move castling kingside rook
				Position pos1 = new Position(position.getRow(), position.getColumn() + 3);
				if (testRookCastling(pos1)) {
					Position p1 = new Position(position.getRow(), position.getColumn() + 1);
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
						mat[position.getRow()][position.getColumn() + 2] = true;
					}
				}
			
			
			//Special move castling queenside rook
			Position pos3 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(pos3)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}	
		}
		return mat;
		
	}

}
