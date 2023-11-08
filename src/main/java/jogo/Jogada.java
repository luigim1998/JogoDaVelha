package jogo;

import java.util.ArrayList;
import java.util.List;

import com.luigim1998.JogoDaVelha.UI;

public class Jogada {

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

	Jogada(Jogada jogoAnterior, PecaEnum jogador, int linha, int coluna) {
		this.jogadorAtual = jogador;
		this.setLinhaJogada(linha);
		this.setColunaJogada(coluna);
		this.setJogadaAnterior(jogoAnterior);
		this.setJogadasProximas(new ArrayList<Jogada>());

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
		this(null, PecaEnum.pecaOposta(jogador), -1, -1);

		this.setTabuleiro(new Tabuleiro(tabuleiro));
	}

	public static VencedorEnum verificarVitoria(Tabuleiro tabuleiro) {
		for (int linha = 0; linha < 3; linha++) {
			if (tabuleiro.getPeca(linha, 0) != null) {
				if (Jogada.verificaTresIguais(tabuleiro.getPeca(linha, 0), tabuleiro.getPeca(linha, 1),
						tabuleiro.getPeca(linha, 2))) {
					return tabuleiro.getPeca(linha, 0) == PecaEnum.O ? VencedorEnum.O : VencedorEnum.X;
				}
			}
		}

		for (int coluna = 0; coluna < 3; coluna++) {
			if (tabuleiro.getPeca(0, coluna) != null) {
				if (Jogada.verificaTresIguais(tabuleiro.getPeca(0, coluna), tabuleiro.getPeca(1, coluna),
						tabuleiro.getPeca(2, coluna))) {
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

	public int compararJogada(Jogada j1, Jogada j2) {
		PecaEnum peca = j1.jogadorAtual;
		
		int ponto = 0;

		double j1JogadaX = j1.pontosX / j1.totalPontos();
		double j1JogadaO = j1.pontosO / j1.totalPontos();
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
		this.jogadasProximas.sort((j1, j2) -> this.compararJogada(j1, j2));
		
		return this.jogadasProximas.get(0);
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

	public void setColunaJogada(int colunaJogada) {
		this.colunaJogada = colunaJogada;
	}

	public void setJogadaAnterior(Jogada jogadaAnterior) {
		this.jogadaAnterior = jogadaAnterior;
	}

	public void setJogadasProximas(List<Jogada> jogadasProximas) {
		this.jogadasProximas = jogadasProximas;
	}

	public void setJogadorAtual(PecaEnum jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}

	public void setLinhaJogada(int linhaJogada) {
		this.linhaJogada = linhaJogada;
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
			this.setJogadasProximas(this.preverProximasJogada());
			this.contabilizarPontos();
			break;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jogada [tabuleiro=").append(UI.imprimirTabuleiroLinhaUnica(this, 0)).append(", jogadorAtual=")
				.append(jogadorAtual).append(", linhaJogada=").append(linhaJogada).append(", colunaJogada=")
				.append(colunaJogada).append(", vencedor=").append(vencedor).append(", pontosEmpate=")
				.append(pontosEmpate).append(", pontosO=").append(pontosO).append(", pontosX=").append(pontosX)
				.append("]");
		return builder.toString();
	}

	public double totalPontos() {
		return this.pontosEmpate + this.pontosO + this.pontosX;
	}
}
