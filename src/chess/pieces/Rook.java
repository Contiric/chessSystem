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
	// Criamos uma matriz de booleanos da mesma dimens�o do tabuleiro
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Verificando espa�o acima para o Rook andar
		Position p = new Position(0, 0);
		// Setando os valores da posi��o da pe�a para cima atrav�s da subtra��o das linhas
		p.setValues(position.getRow() - 1, position.getColumn());

		// Enquanto a posi��o existis e n�o tiver uma pe�a o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posi��o na matriz ser� verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a pe�a para cima
			p.setRow(p.getRow() - 1);
		}

		// Se a posi��o estiver vazia ou livre e se tiver pe�a advers�ria o Rook ganhar� a pe�a oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}

		// Verificando espa�o � esquerda para o Rook andar
		// Setando os valores da posi��o da pe�a para esquerda atrav�s da subtra��o das colunas
		p.setValues(position.getRow(), position.getColumn() - 1);

		// Enquanto a posi��o existis e n�o tiver uma pe�a o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posi��o na matriz ser� verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a pe�a para esquerda
			p.setColumn(p.getColumn() - 1);
		}

		// Se a posi��o estiver vazia ou livre e se tiver pe�a advers�ria o Rook ganhar�
		// a pe�a oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}
		
		// Verificando espa�o � direita para o Rook andar
		// Setando os valores da posi��o da pe�a para cima atrav�s da adi��o das colunas
		p.setValues(position.getRow(), position.getColumn() + 1);

		// Enquanto a posi��o existis e n�o tiver uma pe�a o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posi��o na matriz ser� verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a pe�a para direita
			p.setColumn(p.getColumn() + 1);
		}

		// Se a posi��o estiver vazia ou livre e se tiver pe�a advers�ria o Rook ganhar� a pe�a oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}
		
		
		
		// Setando os valores da posi��o da pe�a para baixo atrav�s da subtra��o das linhas
		p.setValues(position.getRow() + 1, position.getColumn());

		// Enquanto a posi��o existis e n�o tiver uma pe�a o Rook vai andar
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// Sendo assim a posi��o na matriz ser� verdadeira
			mat[p.getRow()][p.getColumn()] = true;

			// Movimentando a pe�a para cima
			p.setRow(p.getRow() + 1);
		}

		// Se a posi��o estiver vazia ou livre e se tiver pe�a advers�ria o Rook ganhar� a pe�a oponente.
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;

		}

		return mat;
	}
}
