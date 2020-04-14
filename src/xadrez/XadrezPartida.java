package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mesa.Mesa;
import mesa.Peca;
import mesa.Posicao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class XadrezPartida {
	
	//atributos//
	private int turno;
	private  Color jogadorAtual;
	private Mesa mesa;
	private boolean check;
	private boolean checkMate;
	private XadrezPeca enPassantVulnerable;
	
	private List<Peca> pecasMesa = new ArrayList<>();
	private List<Peca> pecaCapturada = new ArrayList<>();
	
	public XadrezPartida(){
		mesa = new Mesa(8,8);
		turno = 1;
		jogadorAtual = Color.WHITE;
		check = false;
		
		configInicial();
	}
	
	//Getters & Setters
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public int getTurno() {
		return turno;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
	}

	public Mesa getMesa() {
		return mesa;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public XadrezPeca getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	//metodo//
	public XadrezPeca[][] getPeca(){
		XadrezPeca[][] aux = new XadrezPeca[mesa.getLinha()][mesa.getColuna()];
		for(int i=0;i < mesa.getLinha();i++){
			for(int j=0;j < mesa.getColuna();j++){
				aux[i][j] = (XadrezPeca) mesa.peca(i,j);
			}
		}		
		return aux;
	}
	
	public boolean[][] movimentosPossiveis(XadrezPosicao posicaoOrigem){
		Posicao posicao = posicaoOrigem.paraPosicao();
		validarposicaoOrigem(posicao);
		
		return mesa.peca(posicao).movimentosPossiveis();
	}
	
	public XadrezPeca performanceMovimento(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoFinal) {
		
		Posicao Origem = posicaoOrigem.paraPosicao();
		Posicao Destino = posicaoFinal.paraPosicao();
		
		validarposicaoOrigem(Origem);
		validarposicaoDestino(Origem, Destino);
		
		Peca pecaCapturada = fazerMovimento(Origem, Destino);
		XadrezPeca pecaMovida = (XadrezPeca) mesa.peca(Destino);
		
		if(testedeCheck(jogadorAtual)) {
			desfazerMovimento(Origem, Destino, pecaCapturada);
			throw new  ExcecaoXadrez("Voc� n�o pode se colocar em check");
		}
		
		check = (testedeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}else {
		proximoTurno();
		}
		
		if(pecaMovida instanceof Peao && (Destino.getLinha() == Origem.getLinha() -2 || Destino.getLinha() == Origem.getLinha() +2)) {
			enPassantVulnerable = pecaMovida;	
		}else {
			enPassantVulnerable = null;
		}
		
		return (XadrezPeca)pecaCapturada;
	}
	
	private Peca fazerMovimento(Posicao Origem, Posicao Destino) {
		
		XadrezPeca p = (XadrezPeca)mesa.remocaoPeca(Origem);
		p.aumentarcontagemMovimento();
		
		Peca  pecaCapturada = mesa.remocaoPeca(Destino);
		
		mesa.pecaLugar(p,Destino);
		
		if(pecaCapturada != null) {
			pecasMesa.remove(pecaCapturada);
			this.pecaCapturada.add(pecaCapturada);
		}
		
		if(p instanceof Rei && Destino.getColuna() == Origem.getColuna() + 2) {
			Posicao origemT = new Posicao(Origem.getLinha(), Origem.getColuna() + 3);
			Posicao destinoT = new Posicao(Origem.getLinha(), Origem.getColuna() + 1);
			XadrezPeca rook = (XadrezPeca) mesa.remocaoPeca(origemT);
			mesa.pecaLugar(rook, destinoT);
			rook.aumentarcontagemMovimento();
			
		}
		
		if(p instanceof Rei && Destino.getColuna() == Origem.getColuna() - 2) {
			Posicao origemT = new Posicao(Origem.getLinha(), Origem.getColuna() - 4);
			Posicao destinoT = new Posicao(Origem.getLinha(), Origem.getColuna() - 1);
			XadrezPeca rook = (XadrezPeca) mesa.remocaoPeca(origemT);
			mesa.pecaLugar(rook, destinoT);
			rook.aumentarcontagemMovimento();
			
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem,Posicao destino, Peca pecaCapturada) {
		
		XadrezPeca p = (XadrezPeca)mesa.remocaoPeca(destino);
		p.reduzircontagemMovimento();
		
		mesa.pecaLugar(p, origem);
		
		if(pecaCapturada != null) {
			mesa.pecaLugar(pecaCapturada, destino);
			this.pecaCapturada.remove(pecaCapturada);
			pecasMesa.add(pecaCapturada);
		}
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			XadrezPeca rook = (XadrezPeca) mesa.remocaoPeca(destinoT);
			mesa.pecaLugar(rook, origemT);
			rook.aumentarcontagemMovimento();
			
		}
		
		if(p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			XadrezPeca rook = (XadrezPeca) mesa.remocaoPeca(destinoT);
			mesa.pecaLugar(rook, origemT);
			rook.reduzircontagemMovimento();
			
		}
		
	}
	
	private void validarposicaoOrigem(Posicao posicao) {
		if(!mesa.PecaAqui(posicao)) {
			throw new ExcecaoXadrez("N�o  existe peca na posicao  de origem");
		}
		
		if(jogadorAtual != ((XadrezPeca)mesa.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A pe�a escolhida n�o � sua");
		}
		
		if(!mesa.peca(posicao).aquiummovimentoPossivel()) {
			throw new ExcecaoXadrez("N�o h� movimentos poss�veis para essa pe�a");
		}
	}
	
	private void validarposicaoDestino(Posicao origem, Posicao destino) {
		if(!mesa.peca(origem).movimentosPossiveis(destino)) {
			throw new ExcecaoXadrez("A peca escolhida n�o  pode se mover para a posicao de destino");
		}
	}
	
	private void proximoTurno() {
		
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void LugarPecaNovo(char coluna,int linha,XadrezPeca peca) {
		
		mesa.pecaLugar(peca, new XadrezPosicao(coluna,linha).paraPosicao());
		pecasMesa.add(peca);
	}
	
	private Color oponente(Color cor) {
	
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private XadrezPeca rei(Color cor) {
		
		List<Peca> lista = pecasMesa.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		
		for(Peca p : lista) {
			if(p instanceof Rei) {
				return (XadrezPeca)p;
			}
		}
		throw new IllegalStateException("N�o existe Rei da cor "+ cor +" no jogo.");
	}
	
	private boolean  testedeCheck(Color cor) {
		
		Posicao posicaoRei = rei(cor).getXadrezPosicao().paraPosicao();
		
		List<Peca> pecasOponente = pecasMesa.stream().filter(x -> ((XadrezPeca)x).getCor() == oponente(cor)).collect(Collectors.toList());
		
		for(Peca  p : pecasOponente) {
			boolean[][] aux = p.movimentosPossiveis();
			if(aux[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Color cor) {
		
		if(!testedeCheck(cor)) {
			return false;
		}
		
		List<Peca> lista = pecasMesa.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		
		for(Peca p : lista) {
			boolean[][] aux = p.movimentosPossiveis();
			
			for(int i = 0;i< mesa.getLinha();i++) {
				for(int j = 0;j<mesa.getColuna();j++) {
					if(aux[i][j]) {
						Posicao origem = ((XadrezPeca)p).getXadrezPosicao().paraPosicao();
						Posicao destino = new Posicao(i,j);
						Peca pecaCapturada = fazerMovimento(origem, destino);
						boolean testedeCheck = testedeCheck(cor);
						if(!testedeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void configInicial(){
		//torre//
		LugarPecaNovo('a',1,new Torre(mesa,Color.WHITE));
		LugarPecaNovo('h',1,new Torre(mesa,Color.WHITE));
		
		LugarPecaNovo('a',8,new Torre(mesa,Color.BLACK));
		LugarPecaNovo('h',8,new Torre(mesa,Color.BLACK));
		//rei//
		LugarPecaNovo('d',1,new Rei(mesa,Color.WHITE,this));
		
		LugarPecaNovo('d',8,new Rei(mesa,Color.BLACK,this));
		//rainha//
		LugarPecaNovo('e',1,new Rainha(mesa,Color.WHITE));
		
		LugarPecaNovo('e',8,new Rainha(mesa,Color.BLACK));
		//bispo//
		LugarPecaNovo('c',1,new Bispo(mesa,Color.WHITE));
		LugarPecaNovo('f',1,new Bispo(mesa,Color.WHITE));
		
		LugarPecaNovo('c',8,new Bispo(mesa,Color.BLACK));
		LugarPecaNovo('f',8,new Bispo(mesa,Color.BLACK));
		//cavalo//
		LugarPecaNovo('b',1,new Cavalo(mesa,Color.WHITE));
		LugarPecaNovo('g',1,new Cavalo(mesa,Color.WHITE));
		
		LugarPecaNovo('b',8,new Cavalo(mesa,Color.BLACK));
		LugarPecaNovo('g',8,new Cavalo(mesa,Color.BLACK));
		//peao//
		LugarPecaNovo('a',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('b',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('c',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('d',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('e',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('f',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('g',2,new Peao(mesa,Color.WHITE));
		LugarPecaNovo('h',2,new Peao(mesa,Color.WHITE));
		
		LugarPecaNovo('a',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('b',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('c',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('d',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('e',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('f',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('g',7,new Peao(mesa,Color.BLACK));
		LugarPecaNovo('h',7,new Peao(mesa,Color.BLACK));
	}
	
}
