package xadrez;

import mesa.Posicao;

public class XadrezPosicao {
	//atributos//
	private char coluna;
	private int linha;
	
	//constructor//
	public XadrezPosicao(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecaoXadrez("Erro ao declara a posição: Valores válidos são de a1 à h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	//Getters & Setters//
	public char getColuna() {
		return coluna;
	}
	
	public int getLinha() {
		return linha;
	}
	
	//Metodo//
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha,coluna - 'a');
	}
	
	protected static XadrezPosicao dePosicao(Posicao posicao) {
		return new XadrezPosicao((char)('a' + posicao.getColuna()),8 - posicao.getLinha());
	}
	
	//toString//
	@Override
	public String toString(){
		return "" + coluna + linha;
	}
}
