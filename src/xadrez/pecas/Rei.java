package xadrez.pecas;

import mesa.Mesa;
import mesa.Posicao;
import xadrez.Color;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;

public class Rei extends XadrezPeca {
	
	private XadrezPartida partida;
	
	//constructor//
	public Rei(Mesa mesa, Color cor, XadrezPartida partida) {
		super(mesa, cor);
		this.partida = partida;
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
	
	private boolean testeRook(Posicao posicao) {
		XadrezPeca p = (XadrezPeca)getMesa().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getcontaMovimento()==0;
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
		
		//movimento especial
		if(getcontaMovimento() == 0 && !partida.getCheck()) {
			Posicao poT1 = new Posicao(posicao.getLinha(), posicao.getColuna()+3);
			if(testeRook(poT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()+1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()+2);
				if(getMesa().peca(p1) == null && getMesa().peca(p2) == null) {
					aux[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
		}
		
		if(getcontaMovimento() == 0 && !partida.getCheck()) {
			Posicao poT2 = new Posicao(posicao.getLinha(), posicao.getColuna()-4);
			if(testeRook(poT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
				Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna()-2);
				Posicao p3 = new Posicao(posicao.getLinha(),posicao.getColuna()-3);
				if(getMesa().peca(p1) == null && getMesa().peca(p2) == null && getMesa().peca(p3) == null) {
					aux[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return aux;
	}
	
}
