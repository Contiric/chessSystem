package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
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
		
		
		return mat;
		
	}

}
