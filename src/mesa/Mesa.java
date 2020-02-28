package mesa;

public class Mesa {
	//atributos//
	private Integer linha;
	private Integer coluna;
	private Peca[][] pecas;
	//constructor//
	public Mesa(Integer linha, Integer coluna) {
		if(linha < 1 || coluna < 1) {
			throw new ExcecaoTabuleiro("Erro ao criar tabuleiro: É necessário que haja pelo menos uma linha e uma coluna");
		}
		this.linha = linha;
		this.coluna = coluna;
		pecas = new Peca[linha][coluna];
	}
	//Getters & Setters//
	public Integer getLinha() {
		return linha;
	}
	public Integer getColuna() {
		return coluna;
	}
	//Metodos//	
	public Peca peca(Posicao posicao){
		if(!ExistenciaPosicao(posicao)) {
			throw new ExcecaoTabuleiro("Esta posição não existe");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public Peca peca(int linha, int coluna) {
		if(!ExistenciaPosicao(linha,coluna)) {
			throw new ExcecaoTabuleiro("Esta posição não existe");
		}
		return pecas[linha][coluna];
	}
	
	public void pecaLugar(Peca peca,Posicao posicao){
		if(PecaAqui(posicao)) {
			throw new ExcecaoTabuleiro("Uma peça ja existe na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
		
	}
	
	public Peca remocaoPeca(Posicao posicao) {
		if (!ExistenciaPosicao(posicao)) {
			throw new ExcecaoTabuleiro("Esta posição não existe");
		}
		if(peca(posicao) == null) {
			return null;
		}
		
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean ExistenciaPosicao(int linha,int coluna){
		return  linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna;
	}
	
	public boolean ExistenciaPosicao(Posicao posicao){
		return ExistenciaPosicao(posicao.getLinha(),posicao.getColuna()) ;
	}
	
	public boolean PecaAqui(Posicao posicao){
		if(!ExistenciaPosicao(posicao)) {
			throw new ExcecaoTabuleiro("Esta posição não existe");
		}
		return peca(posicao) != null;
	}
}
