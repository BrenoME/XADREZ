package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.XadrezPeca;
import xadrez.Color;

public class Bispo extends XadrezPeca {

	public Bispo(Mesa mesa, Color cor) {
		super(mesa, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux = new boolean[getMesa().getLinha()][getMesa().getColuna()];
		
		Posicao p = new Posicao(0, 0);
		
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