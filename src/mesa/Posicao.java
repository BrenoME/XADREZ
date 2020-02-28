package mesa;

public class Posicao {
	
	//atributos//
	private Integer linha;
	private Integer coluna;
	
	public Posicao(Integer linha, Integer coluna){
		
		this.linha = linha;
		this.coluna = coluna;
		
	}

	//getters e setters//
	public Integer getLinha() {
		return linha;
	}
	
	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	
	public Integer getColuna() {
		return coluna;
	}
	
	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
	
	public void setValores(int linha, int coluna) {
		this.coluna = coluna;
		this.linha = linha;
	}
	
	//toString//
	@Override
	public String toString() {
		return linha + ", " + coluna;
	}
		
}
