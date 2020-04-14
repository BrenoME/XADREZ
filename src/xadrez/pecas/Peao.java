package xadrez.pecas;

import xadrez.XadrezPeca;
import xadrez.Color;
import xadrez.XadrezPartida;
import mesa.Mesa;
import mesa.Posicao;

public class Peao extends XadrezPeca {
	
	private XadrezPartida xadrezPartida;
	
	//constructor//
	public Peao(Mesa mesa, Color cor, XadrezPartida xadrezPartida) {
		super(mesa, cor);
		this.xadrezPartida = xadrezPartida;
	}
	//toString//
	@Override
	public String toString(){
		return "P";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] aux =  new boolean[getMesa().getLinha()][getMesa().getColuna()];
		Posicao posi = new Posicao(0,0);
		
		if(getCor() == Color.WHITE) {
			
			posi.setValores(posicao.getLinha()-1, posicao.getColuna());
			if(getMesa().ExistenciaPosicao(posi) && !getMesa().PecaAqui(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
				
			}
			posi.setValores(posicao.getLinha()-2, posicao.getColuna());
			Posicao posi2 = new Posicao(posicao.getLinha()-1,posicao.getColuna());
			
			if(getMesa().ExistenciaPosicao(posi) && !getMesa().PecaAqui(posi) && getMesa().ExistenciaPosicao(posi2) && !getMesa().PecaAqui(posi2) && getcontaMovimento() == 0) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
				
			}
			posi.setValores(posicao.getLinha()-1,posicao.getColuna()-1);
			if(getMesa().ExistenciaPosicao(posi) && pecaOponente(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
			}
			posi.setValores(posicao.getLinha()-1,posicao.getColuna()+1);
			if(getMesa().ExistenciaPosicao(posi) && pecaOponente(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
			}
			
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if(getMesa().ExistenciaPosicao(esquerda) && pecaOponente(esquerda) && getMesa().peca(esquerda) == xadrezPartida.getEnPassantVulnerable()) {
					aux[esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if(getMesa().ExistenciaPosicao(direita) && pecaOponente(direita) && getMesa().peca(direita) == xadrezPartida.getEnPassantVulnerable()) {
					aux[direita.getLinha() -1][direita.getColuna()] = true;
				}
			}
			
		}else{
			posi.setValores(posicao.getLinha()+1, posicao.getColuna());
			if(getMesa().ExistenciaPosicao(posi) && !getMesa().PecaAqui(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
				
			}
			posi.setValores(posicao.getLinha()+2, posicao.getColuna());
			Posicao posi2 = new Posicao(posicao.getLinha()+1,posicao.getColuna());
			
			if(getMesa().ExistenciaPosicao(posi) && !getMesa().PecaAqui(posi) && getMesa().ExistenciaPosicao(posi2) && !getMesa().PecaAqui(posi2) && getcontaMovimento() == 0) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
				
			}
			posi.setValores(posicao.getLinha()+1,posicao.getColuna()-1);
			if(getMesa().ExistenciaPosicao(posi) && pecaOponente(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
			}
			posi.setValores(posicao.getLinha()+1,posicao.getColuna()+1);
			if(getMesa().ExistenciaPosicao(posi) && pecaOponente(posi)) {
				aux[posi.getLinha()][posi.getColuna()] =  true;
			}
			
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if(getMesa().ExistenciaPosicao(esquerda) && pecaOponente(esquerda) && getMesa().peca(esquerda) == xadrezPartida.getEnPassantVulnerable()) {
					aux[esquerda.getLinha() +1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if(getMesa().ExistenciaPosicao(direita) && pecaOponente(direita) && getMesa().peca(direita) == xadrezPartida.getEnPassantVulnerable()) {
					aux[direita.getLinha() +1][direita.getColuna()] = true;
				}
			}
		}
		
		return aux;
	}
}
