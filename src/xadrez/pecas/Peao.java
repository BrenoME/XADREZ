package xadrez.pecas;

import xadrez.XadrezPeca;
import xadrez.Color;
import mesa.Mesa;
import mesa.Posicao;

public class Peao extends XadrezPeca {
	
	//constructor//
	public Peao(Mesa mesa, Color cor) {
		super(mesa, cor);
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
		}
		
		return aux;
	}
}
