package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.XadrezPeca;
import xadrez.Color;

public class Rainha extends XadrezPeca {

	public Rainha(Mesa mesa, Color cor) {
		super(mesa, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux = new boolean[getMesa().getLinha()][getMesa().getColuna()];
		
		Posicao p = new Posicao(0, 0);
		
		// cima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getLinha()] = true;
		}
		
		// esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// right
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// nw
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// ne
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// se
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		// sw
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		return aux;
	}
}