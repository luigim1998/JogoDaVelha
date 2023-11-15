package com.luigim1998.JogoDaVelha.game;

import java.util.ArrayList;
import java.util.List;

public class Jogada implements Comparable<Jogada>{

	private Tabuleiro tabuleiro;
	private PecaEnum jogadorAtual;
	private int linhaJogada;
	private int colunaJogada;
	private Jogada jogadaAnterior;
	private List<Jogada> jogadasProximas;
	private VencedorEnum vencedor;
	private int pontosEmpate = 0;
	private int pontosO = 0;
	private int pontosX = 0;

	private Jogada(Jogada jogoAnterior, PecaEnum jogador, int linha, int coluna) {
		this.jogadaAnterior = jogoAnterior;
		this.jogadorAtual = jogador;
		this.linhaJogada = linha;
		this.colunaJogada = coluna;
		this.jogadasProximas = new ArrayList<Jogada>();
		
		if (jogoAnterior == null) {
			this.setTabuleiro(new Tabuleiro());
		} else {
			this.setTabuleiro(new Tabuleiro(jogoAnterior.tabuleiro));
		}
	}

	public Jogada(PecaEnum jogador) {
		this(null, PecaEnum.pecaOposta(jogador), -1, -1);
	}

	public Jogada(Tabuleiro tabuleiro, PecaEnum jogador) {
		this.jogadaAnterior = null;
		this.jogadorAtual = PecaEnum.pecaOposta(jogador);
		this.linhaJogada = -1;
		this.colunaJogada = -1;
		this.jogadasProximas = new ArrayList<Jogada>();

		this.setTabuleiro(new Tabuleiro(tabuleiro));
	}

	public static VencedorEnum verificarVitoria(Tabuleiro tabuleiro) {
		for (int linha = 0; linha < 3; linha++) {
			if (tabuleiro.getPeca(linha, 0) != null) {
				if (Jogada.verificaTresIguais(tabuleiro.getPeca(linha, 0), tabuleiro.getPeca(linha, 1), tabuleiro.getPeca(linha, 2))) {
					return tabuleiro.getPeca(linha, 0) == PecaEnum.O ? VencedorEnum.O : VencedorEnum.X;
				}
			}
		}

		for (int coluna = 0; coluna < 3; coluna++) {
			if (tabuleiro.getPeca(0, coluna) != null) {
				if (Jogada.verificaTresIguais(tabuleiro.getPeca(0, coluna), tabuleiro.getPeca(1, coluna), tabuleiro.getPeca(2, coluna))) {
					return tabuleiro.getPeca(0, coluna) == PecaEnum.O ? VencedorEnum.O : VencedorEnum.X;
				}
			}
		}

		if (tabuleiro.getPeca(0, 0) != null) {
			if (Jogada.verificaTresIguais(tabuleiro.getPeca(0, 0), tabuleiro.getPeca(1, 1), tabuleiro.getPeca(2, 2))) {
				return tabuleiro.getPeca(0, 0) == PecaEnum.O ? VencedorEnum.O : VencedorEnum.X;
			}
		}

		if (tabuleiro.getPeca(2, 0) != null) {
			if (Jogada.verificaTresIguais(tabuleiro.getPeca(2, 0), tabuleiro.getPeca(1, 1), tabuleiro.getPeca(0, 2))) {
				return tabuleiro.getPeca(2, 0) == PecaEnum.O ? VencedorEnum.O : VencedorEnum.X;
			}
		}

		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				if (tabuleiro.getPeca(linha, coluna) == null) {
					return VencedorEnum.NAO_FINALIZADO;
				}
			}
		}
		return VencedorEnum.EMPATE;
	}

	public static boolean verificaTresIguais(PecaEnum peca1, PecaEnum peca2, PecaEnum peca3) {
		return peca1 == peca2 && peca2 == peca3;
	}

	public void contabilizarPontos() {
		this.pontosO = 0;
		this.pontosX = 0;
		this.pontosEmpate = 0;
		for (Jogada jogo : this.jogadasProximas) {
			this.pontosO += jogo.pontosO;
			this.pontosX += jogo.pontosX;
			this.pontosEmpate += jogo.pontosEmpate;
		}
	}
	
	public int getColunaJogada() {
		return colunaJogada;
	}

	public Jogada getJogadaAnterior() {
		return jogadaAnterior;
	}

	public List<Jogada> getJogadasProximas() {
		return jogadasProximas;
	}

	public PecaEnum getJogadorAtual() {
		return jogadorAtual;
	}

	public int getLinhaJogada() {
		return linhaJogada;
	}

	public int getPontosEmpate() {
		return pontosEmpate;
	}

	public int getPontosO() {
		return pontosO;
	}

	public int getPontosX() {
		return pontosX;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public VencedorEnum getVencedor() {
		return vencedor;
	}

	public List<Jogada> preverProximasJogada() {
		List<Jogada> jogadas = new ArrayList<Jogada>();

		for (int linha = 0; linha < this.tabuleiro.getMatriz().length; linha++) {
			for (int coluna = 0; coluna < this.tabuleiro.getMatriz()[linha].length; coluna++) {
				if (this.tabuleiro.getPeca(linha, coluna) == null) {
					jogadas.add(new Jogada(this, PecaEnum.pecaOposta(jogadorAtual), linha, coluna));
				}
			}
		}
		return jogadas;
	}

	public Jogada selecionarMelhorJogada() {
		ArrayList<Jogada> jogadasOrdenadas = new ArrayList<Jogada>(this.jogadasProximas);
		jogadasOrdenadas.sort(null);
		return jogadasOrdenadas.get(jogadasOrdenadas.size() - 1);
	}

	
	public Jogada selecionarProximaJogada(int linha, int coluna) {
		Jogada novaJogada = null;
		for (Jogada jogadaProxima : this.getJogadasProximas()) {
			if (jogadaProxima.linhaJogada == linha && jogadaProxima.colunaJogada == coluna) {
				novaJogada = jogadaProxima;
			}
		}
		return novaJogada;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = new Tabuleiro( tabuleiro);

		if (this.linhaJogada != -1 && this.colunaJogada != -1) {
			if (this.tabuleiro.getPeca(this.linhaJogada, this.colunaJogada) != null) {
				throw new IllegalArgumentException(
						"Linha " + this.linhaJogada + " e coluna " + this.colunaJogada + " já foram preenchidas");
			} else {
			this.tabuleiro.setPeca(this.linhaJogada, this.colunaJogada, this.jogadorAtual);
			}
		}

		this.vencedor = Jogada.verificarVitoria(this.tabuleiro);

		switch (this.vencedor) {
		case O:
			this.pontosO = 1;
			this.pontosX = 0;
			this.pontosEmpate = 0;
			break;
		case X:
			this.pontosO = 0;
			this.pontosX = 1;
			this.pontosEmpate = 0;
			break;
		case EMPATE:
			this.pontosO = 0;
			this.pontosX = 0;
			this.pontosEmpate = 1;
			break;
		case NAO_FINALIZADO:
			this.jogadasProximas = this.preverProximasJogada();
			this.contabilizarPontos();
			break;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jogada [tabuleiro=").append(this.tabuleiro).append(", jogadorAtual=")
				.append(jogadorAtual).append(", linhaJogada=").append(linhaJogada).append(", colunaJogada=")
				.append(colunaJogada).append(", vencedor=").append(vencedor).append(", pontosEmpate=")
				.append(pontosEmpate).append(", pontosO=").append(pontosO).append(", pontosX=").append(pontosX)
				.append(", Vitória O: ").append(String.format("%,.2f", this.pontosO/this.totalPontos()))
				.append(", Vitória X: ").append(String.format("%,.2f", this.pontosX/this.totalPontos()))
				.append("]");
		return builder.toString();
	}

	public double totalPontos() {
		return this.pontosEmpate + this.pontosO + this.pontosX;
	}


	@Override
	public int compareTo(Jogada j2) {
		PecaEnum peca = this.jogadorAtual;
		
		int ponto = 0;

		double j1JogadaX = this.pontosX / this.totalPontos();
		double j1JogadaO = this.pontosO / this.totalPontos();
		double j2JogadaX = j2.pontosX / j2.totalPontos();
		double j2JogadaO = j2.pontosO / j2.totalPontos();

		if (peca == PecaEnum.O) {
			if (j1JogadaO < j2JogadaO) {
				ponto = -1;
			} else if (j1JogadaO > j2JogadaO) {
				ponto = 1;
			} else {
				if (j1JogadaX < j2JogadaX) {
					ponto = 1;
				} else if (j1JogadaX > j2JogadaX) {
					ponto = -1;
				} else {
					ponto = 0;
				}
			}
		} else {
			if (j1JogadaX < j2JogadaX) {
				ponto = -1;
			} else if (j1JogadaX > j2JogadaX) {
				ponto = 1;
			} else {
				if (j1JogadaO < j2JogadaO) {
					ponto = 1;
				} else if (j1JogadaO > j2JogadaO) {
					ponto = -1;
				} else {
					ponto = 0;
				}
			}
		}
		return ponto;

	}
}
