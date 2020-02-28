package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.Color;
import xadrez.XadrezPeca;

public class Torre extends XadrezPeca {
	
	//constructor//
	public Torre(Mesa mesa, Color cor) {
		super(mesa, cor);
	}
	//toString//
	@Override
	public String toString(){
		return "R";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux =  new boolean[getMesa().getLinha()][getMesa().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		//cima
		p.setValores(posicao.getLinha() - 1, p.getColuna());
		
		while(getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), p.getColuna() - 1);
		
		while(getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValores(posicao.getLinha(), p.getColuna() + 1);
		
		while(getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}
		
		//baixo
		p.setValores(posicao.getLinha() + 1, p.getColuna());
		
		while(getMesa().ExistenciaPosicao(p) && !getMesa().PecaAqui(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getMesa().ExistenciaPosicao(p) && pecaOponente(p)) {
			aux[p.getLinha()][p.getColuna()] = true;
		}		
		return aux;
	}
}
