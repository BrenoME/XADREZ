package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.Color;
import xadrez.XadrezPeca;

public class Rei extends XadrezPeca {
	
	//constructor//
	public Rei(Mesa mesa, Color cor) {
		super(mesa, cor);
	}
	//toString//
	@Override
	public String toString(){
		return "K";
	}
	
	private boolean podeMover(Posicao posicao) {
		XadrezPeca p= (XadrezPeca)getMesa().peca(posicao);
		
		return p == null ||  p.getCor() != getCor();
		
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux =  new boolean[getMesa().getLinha()][getMesa().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		//cima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		//baixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;		
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;			
		}
		
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		//noroeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		//nordeste
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		//sul do oeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		//sudeste
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		return aux;
	}
	
}
