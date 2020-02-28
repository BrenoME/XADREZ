package xadrez;

import mesa.Mesa;
import mesa.Peca;
import mesa.Posicao;

public abstract class XadrezPeca extends Peca{
	
	//atributo//
	private Color cor;
	private int contaMovimento;
	
	//constructor//
	public XadrezPeca(Mesa mesa, Color cor) {
		super(mesa);
		this.cor = cor;
	}
	
	//Getter//
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.dePosicao(posicao);
	}
	
	public Color getCor() {
		return cor;
	}
	
	public int getcontaMovimento(){
		return contaMovimento;
	}
	
	public void aumentarcontagemMovimento () {
		contaMovimento ++;
		}
	
	public void reduzircontagemMovimento () {
		contaMovimento --;
		}
	
	protected boolean pecaOponente(Posicao posicao) {
		XadrezPeca p = (XadrezPeca)getMesa().peca(posicao);
		
		return p != null && p.getCor() != cor;
	}
	
}
