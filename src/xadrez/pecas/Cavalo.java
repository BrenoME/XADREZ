package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.Color;
import xadrez.XadrezPeca;

public class Cavalo extends XadrezPeca {
	
	//constructor//
	public Cavalo(Mesa mesa, Color cor) {
		super(mesa, cor);
	}
	//toString//
	@Override
	public String toString(){
		return "H";
	}
	
	private boolean podeMover(Posicao posicao) {
		XadrezPeca p= (XadrezPeca)getMesa().peca(posicao);
		
		return p == null ||  p.getCor() != getCor();
		
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux =  new boolean[getMesa().getLinha()][getMesa().getColuna()];
		
		Posicao p = new Posicao(0,0);
		
		
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;		
		}
		
		
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;			
		}
		
		
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		
		if(getMesa().ExistenciaPosicao(p) && podeMover(p)) {
			aux[p.getLinha()][p.getColuna()] = true;	
		}
		
		return aux;
	}
	
}
