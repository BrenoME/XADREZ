package xadrez;

import java.security.InvalidParameterException;
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
	private XadrezPeca promocao;
	
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
	
	public XadrezPeca getPromocao(){
		return promocao;
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
		
		if(testedeCheck(jogadorAtual)) {
			desfazerMovimento(Origem, Destino, pecaCapturada);
			throw new  ExcecaoXadrez("Você não pode se colocar em check");
		}
		
		XadrezPeca pecaMovida = (XadrezPeca) mesa.peca(Destino);
		
		promocao = null;
		
		if(pecaMovida instanceof Peao) {
			if((pecaMovida.getCor() == Color.WHITE && Destino.getLinha() == 0) || (pecaMovida.getCor() == Color.BLACK && Destino.getLinha() == 7)) {
				promocao = (XadrezPeca) mesa.peca(Destino);
				promocao =XadrezPeca RealocarPecaPromovida("Q");
			}
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
	
	public XadrezPeca RealocarPecaPromovida(String tipo) {
		if(promocao == null) {
			throw new IllegalStateException("Não é uma peça para ser promovida");
		}
		if(!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")) {
			throw new InvalidParameterException("Tipo inválido para a promoção");		
		}
		
		Posicao pos = promocao.getXadrezPosicao().paraPosicao();
		Peca p = mesa.remocaoPeca(pos);
		pecasMesa.remove(p);
		
		XadrezPeca nova = novaPeca(tipo, promocao.getCor());
		
		mesa.pecaLugar(nova, pos);
		pecasMesa.add(nova);
		
		return nova;
		
	}
	
	private XadrezPeca novaPeca(String tipo, Color cor) {
		if(tipo.equals("B")) return new Bispo(mesa, cor);
		if(tipo.equals("N")) return new Cavalo(mesa, cor);
		if(tipo.equals("Q")) return new Rainha(mesa, cor);
		return new Torre(mesa, cor);
		
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
		
		if(p instanceof Peao) {
			if(Origem.getColuna() != Destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if(p.getCor() == Color.WHITE) {
					posicaoPeao = new Posicao(Destino.getLinha() + 1, Destino.getColuna());
				}else {
					posicaoPeao = new Posicao(Destino.getLinha() - 1, Destino.getColuna());
				}
				pecaCapturada = mesa.remocaoPeca(posicaoPeao);
				this.pecaCapturada.add(pecaCapturada);
				pecasMesa.remove(pecaCapturada);
			}
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
		
		if(p instanceof Peao) {
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulnerable) {
				XadrezPeca peao = (XadrezPeca) mesa.remocaoPeca(destino);
				Posicao posicaoPeao;
				if(p.getCor() == Color.WHITE) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				}else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				mesa.pecaLugar(peao, posicaoPeao);
			}
		}
		
	}
	
	private void validarposicaoOrigem(Posicao posicao) {
		if(!mesa.PecaAqui(posicao)) {
			throw new ExcecaoXadrez("Nï¿½o  existe peca na posicao  de origem");
		}
		
		if(jogadorAtual != ((XadrezPeca)mesa.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peï¿½a escolhida nï¿½o ï¿½ sua");
		}
		
		if(!mesa.peca(posicao).aquiummovimentoPossivel()) {
			throw new ExcecaoXadrez("Nï¿½o hï¿½ movimentos possï¿½veis para essa peï¿½a");
		}
	}
	
	private void validarposicaoDestino(Posicao origem, Posicao destino) {
		if(!mesa.peca(origem).movimentosPossiveis(destino)) {
			throw new ExcecaoXadrez("A peca escolhida nï¿½o  pode se mover para a posicao de destino");
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
		throw new IllegalStateException("Nï¿½o existe Rei da cor "+ cor +" no jogo.");
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
		LugarPecaNovo('a',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('b',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('c',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('d',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('e',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('f',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('g',2,new Peao(mesa,Color.WHITE,this));
		LugarPecaNovo('h',2,new Peao(mesa,Color.WHITE,this));
		
		LugarPecaNovo('a',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('b',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('c',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('d',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('e',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('f',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('g',7,new Peao(mesa,Color.BLACK,this));
		LugarPecaNovo('h',7,new Peao(mesa,Color.BLACK,this));
	}
	
}
