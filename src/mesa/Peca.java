package mesa;

public abstract class Peca {
	//atributos//
	protected Posicao posicao;
	private Mesa mesa;
	//constructor//
	public Peca(Mesa mesa) {
		this.mesa = mesa;
		posicao = null;
	}
	//Getter//
	protected Mesa getMesa() {
		return mesa;
	}
	//metodos
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentosPossiveis(Posicao posicao) {
		
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
		
	}
	
	public boolean aquiummovimentoPossivel() {
		
		boolean[][] aux = movimentosPossiveis();
		
		for(int i=0;i<aux.length;i++) {
			for(int  j=0;j<aux.length;j++) {
				
				if(aux[i][j]) {
					return true;
				}
				
			}
		}
		return false;
	}
}
