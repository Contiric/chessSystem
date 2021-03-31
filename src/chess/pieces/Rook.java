package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	// Criamos uma matriz de booleanos da mesma dimensão do tabuleiro
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Verificando espaço acima para o Rook andar
		Position p = new Position(0, 0);
		// Setando os valores da posição da peça para cima através da subtração das linhas
		p.setValues(position.getRow() - 1, position.getColumn());

		// Enquanto a posição existis e não tiver uma peça o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posição na matriz será verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a peça para cima
			p.setRow(p.getRow() - 1);
		}

		// Se a posição estiver vazia ou livre e se tiver peça adversária o Rook ganhará a peça oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}

		// Verificando espaço à esquerda para o Rook andar
		// Setando os valores da posição da peça para esquerda através da subtração das colunas
		p.setValues(position.getRow(), position.getColumn() - 1);

		// Enquanto a posição existis e não tiver uma peça o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posição na matriz será verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a peça para esquerda
			p.setColumn(p.getColumn() - 1);
		}

		// Se a posição estiver vazia ou livre e se tiver peça adversária o Rook ganhará
		// a peça oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}
		
		// Verificando espaço à direita para o Rook andar
		// Setando os valores da posição da peça para cima através da adição das colunas
		p.setValues(position.getRow(), position.getColumn() + 1);

		// Enquanto a posição existis e não tiver uma peça o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posição na matriz será verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a peça para direita
			p.setColumn(p.getColumn() + 1);
		}

		// Se a posição estiver vazia ou livre e se tiver peça adversária o Rook ganhará a peça oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}
		
		
		
		// Setando os valores da posição da peça para baixo através da subtração das linhas
		p.setValues(position.getRow() + 1, position.getColumn());

		// Enquanto a posição existis e não tiver uma peça o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posição na matriz será verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a peça para cima
			p.setRow(p.getRow() + 1);
		}

		// Se a posição estiver vazia ou livre e se tiver peça adversária o Rook ganhará a peça oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}

		return mat;
	}
}
