package com.luigim1998.JogoDaVelha.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma jogada, armazenando seus dados, a referência da jogada anterior (se houver) e as próximas jogadas.
 */
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

	
	/** Cria uma jogada utilizando o tabuleiro do jogo anterior e efetuando a jogada com a peça e coordenadas fornecidas nos parâmetros. Depois, as próximas jogadas são computadas.
	 * @param jogoAnterior Jogo anterior.
	 * @param jogador Peça a ser usada nessa jogada.
	 * @param linha Linha a ser jogada.
	 * @param coluna Coluna a ser jogada.
	 */
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

	/** Cria um tabuleiro vazio e as próximas jogadas são feitas com a peça no parâmetro.
	 * @param jogador Peça da primeira jogada.
	 */
	public Jogada(PecaEnum jogador) {
		this(null, PecaEnum.pecaOposta(jogador), -1, -1);
	}

	/**~Cria um tabuleiro usando como base o tabuleiro no parâmetro e as próximas jogadas são feitas usando a peça no parâmetro.
	 * @param tabuleiro Tabuleiro da jogada atual.
	 * @param jogador Peça a ser usada nas próximas jogadas.
	 */
	public Jogada(Tabuleiro tabuleiro, PecaEnum jogador) {
		this.jogadaAnterior = null;
		this.jogadorAtual = PecaEnum.pecaOposta(jogador);
		this.linhaJogada = -1;
		this.colunaJogada = -1;
		this.jogadasProximas = new ArrayList<Jogada>();

		this.setTabuleiro(new Tabuleiro(tabuleiro));
	}

	/** Verifica quem venceu no tabuleiro. Se ninguém ganhar, haverá empate se o tabuleiro estiver sem espaços vazios, se houver espaços vazios, a jogada não finalizou.
	 * @param tabuleiro Tabuleiro a ser avaliado.
	 * @return VencedorEnum.
	 */
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

	/** Verifica se as três peças dos parâmetros são iguais mutuamente.
	 * @param peca1 Primeira peça.
	 * @param peca2 Segunda peça.
	 * @param peca3 Terceira peça.
	 * @return True se forem iguais e False se forem diferentes.
	 */
	public static boolean verificaTresIguais(PecaEnum peca1, PecaEnum peca2, PecaEnum peca3) {
		return peca1 == peca2 && peca2 == peca3;
	}

	/**
	 * Contabiliza os pontos das partidas próximas.
	 */
	private void contabilizarPontos() {
		this.pontosO = 0;
		this.pontosX = 0;
		this.pontosEmpate = 0;
		for (Jogada jogo : this.jogadasProximas) {
			this.pontosO += jogo.pontosO;
			this.pontosX += jogo.pontosX;
			this.pontosEmpate += jogo.pontosEmpate;
		}
	}
	
	/** Retorna valor da coluna jogada.
	 * @return Valor da coluna jogada.
	 */
	public int getColunaJogada() {
		return colunaJogada;
	}

	/** Retorna a referência da jogada anterior.
	 * @return Referência da jogada anterior.
	 */
	public Jogada getJogadaAnterior() {
		return jogadaAnterior;
	}

	/** Retorna a lista das próximas jogadas possíveis.
	 * @return Lista das próximas jogadas possíveis.
	 */
	public List<Jogada> getJogadasProximas() {
		return jogadasProximas;
	}

	/** Retorna a peça da jogada atual.
	 * @return Peça da jogada atual.
	 */
	public PecaEnum getJogadorAtual() {
		return jogadorAtual;
	}

	/** Retorna valor da linha jogada.
	 * @return Valor da linha jogada.
	 */
	public int getLinhaJogada() {
		return linhaJogada;
	}

	/** Retorna valor de pontos de empate.
	 * @return Valor de pontos de empate.
	 */
	public int getPontosEmpate() {
		return pontosEmpate;
	}

	/** Retorna valor de pontos da peça O.
	 * @return Valor de pontos da peça O.
	 */
	public int getPontosO() {
		return pontosO;
	}

	/** Retorna valor de pontos da peça X.
	 * @return Valor de pontos da peça X.
	 */
	public int getPontosX() {
		return pontosX;
	}

	/** Retorna o objeto Tabuleiro da jogada.
	 * @return Objeto Tabuleiro da jogada.
	 */
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	
	/** Retorna o VencedorEnum referente a quem ganhou nessa jogada.
	 * @return VencedorEnum referente a quem ganhou nessa jogada.
	 */
	public VencedorEnum getVencedor() {
		return vencedor;
	}

	
	/**
	 * Preve as próximas jogadas baseado no tabuleiro da jogada atual.
	 */
	public void preverProximasJogada() {
		List<Jogada> jogadas = new ArrayList<Jogada>();

		for (int linha = 0; linha < this.tabuleiro.getMatriz().length; linha++) {
			for (int coluna = 0; coluna < this.tabuleiro.getMatriz()[linha].length; coluna++) {
				if (this.tabuleiro.getPeca(linha, coluna) == null) {
					jogadas.add(new Jogada(this, PecaEnum.pecaOposta(jogadorAtual), linha, coluna));
				}
			}
		}
		this.jogadasProximas = jogadas;
	}

	/** Retorna a melhor jogada baseada no jogador da lista de jogadas.
	 * @return Melhor jogada.
	 */
	public Jogada selecionarMelhorJogada() {
		ArrayList<Jogada> jogadasOrdenadas = new ArrayList<Jogada>(this.jogadasProximas);
		jogadasOrdenadas.sort(null);
		return jogadasOrdenadas.get(jogadasOrdenadas.size() - 1);
	}

	
	/** Seleciona uma jogada próxima que tiver a linha e coluna da jogada próxima igual a linha e coluna dos parâmetros. Retorna null se não encontrar.
	 * @param linha Linha da jogada
	 * @param coluna Coluna jogada.
	 * @return Jogada próxima.
	 */
	public Jogada selecionarProximaJogada(int linha, int coluna) {
		Jogada novaJogada = null;
		for (Jogada jogadaProxima : this.getJogadasProximas()) {
			if (jogadaProxima.linhaJogada == linha && jogadaProxima.colunaJogada == coluna) {
				novaJogada = jogadaProxima;
			}
		}
		return novaJogada;
	}

	/** Muda o tabuleiro da jogada atual, salva uma cópia do tabuleiro, executa a jogada, verifica quem ganha na partida e computa as próximas jogadas se o jogo não foi finalizado.
	 * @param tabuleiro Tabuleiro a ser copiado na jogada.
	 */
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
			this.preverProximasJogada();
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
				.append(", Vitória O: ").append(String.format("%,.2f", this.pontosO/(double) this.totalPontos()))
				.append(", Vitória X: ").append(String.format("%,.2f", this.pontosX/(double) this.totalPontos()))
				.append("]");
		return builder.toString();
	}

	/** Calcula o total de pontos.
	 * @return Total de pontos.
	 */
	public int totalPontos() {
		return this.pontosEmpate + this.pontosO + this.pontosX;
	}


	/**
	 * Compara duas jogadas baseado na peça do jogador atual.
	 * A comparação analisa quem mais tem chance de vencer com a peça do jogador atual, se forem iguais, é escolhida a jogada que tiver menos chance da peça oposta vencer.
	 */
	@Override
	public int compareTo(Jogada j2) {
		PecaEnum peca = this.jogadorAtual;
		
		int ponto = 0;

		double j1JogadaX = this.pontosX / (double) this.totalPontos();
		double j1JogadaO = this.pontosO / (double)this.totalPontos();
		double j2JogadaX = j2.pontosX / (double) j2.totalPontos();
		double j2JogadaO = j2.pontosO / (double) j2.totalPontos();

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
