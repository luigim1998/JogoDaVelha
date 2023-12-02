package com.luigim1998.JogoDaVelha.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Objeto que representa o tabuleiro da jogada.
 */
public class Tabuleiro {
	private List<List<PecaEnum>> matriz;
	
	/**
	 * Cria um tabuleiro 3x3 de valores null.
	 */
	public Tabuleiro() {
		this.matriz = new ArrayList<List<PecaEnum>>();
		
		for (int linhaTab = 0; linhaTab < 3; linhaTab++) {
			this.matriz.add(new ArrayList<PecaEnum>());
			for (int colunaTab = 0; colunaTab < 3; colunaTab++) {
				this.matriz.get(linhaTab).add(null);
			}
		}
	}
	
	/**
	 * Cria um tabuleiro 3x3 copiando o tabuleiro no parâmetro.
	 * @param original Tabuleiro original para ser copiado.
	 */
	public Tabuleiro(Tabuleiro original) {
		this.matriz = new ArrayList<List<PecaEnum>>();

		for (int linhaTab = 0; linhaTab < 3; linhaTab++) {
			this.matriz.add( new ArrayList<PecaEnum>(original.matriz.get(linhaTab)) );
		}
	}

	/** Retorna a matriz do tabuleiro.
	 * @return Matriz do tabuleiro.
	 */
	public List<List<PecaEnum>> getMatriz() {
		return this.matriz;
	}

	/** Muda a matriz do tabuleiro pela matriz no parâmetro, sem criar cópia da matriz.
	 * @param matrizPecas Nova matriz.
	 */
	public void setMatriz(List<List<PecaEnum>> matrizPecas) {
		if (matrizPecas.size() != 3) {
			throw new IllegalArgumentException("A matriz não pode ter uma quantidade diferente de três linhas.");
		}
		if (matrizPecas.get(0).size() != 3 || matrizPecas.get(1).size() != 3 || matrizPecas.get(2).size() != 3) {
			throw new IllegalArgumentException("As linhas da matriz não podem ter uma quantidade diferente de três colunas.");
		}
		this.matriz = matrizPecas;
	}
	
	/** Retorna a PecaEnum da linha e coluna dada no parâmetro.
	 * @param linha Linha do tabuleiro.
	 * @param coluna Coluna do tabuleiro.
	 * @return PecaEnum da linha e coluna dada.
	 */
	public PecaEnum getPeca(int linha, int coluna) {
		return this.matriz.get(linha).get(coluna);
	}
	
	/** Muda a PecaEnum da linha e coluna dada no parâmetro pela PecaEnum no parâmetro.
	 * @param linha Linha do tabuleiro.
	 * @param coluna Coluna do tabuleiro.
	 * @param peca Nova PecaEnum.
	 * @return Referência do tabuleiro.
	 */
	public Tabuleiro setPeca(int linha, int coluna, PecaEnum peca) {
		this.matriz.get(linha).set(coluna, peca);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matriz);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tabuleiro other = (Tabuleiro) obj;
		return Objects.equals(matriz, other.matriz);
	}

	@Override
	public String toString() {
		StringJoiner texto = new StringJoiner(",");
		List<StringBuilder> linhas = new ArrayList<StringBuilder>();

		for (int linha = 0; linha < this.matriz.size(); linha++) {
			linhas.add(new StringBuilder(3));
			for (int peca = 0; peca < this.matriz.get(linha).size(); peca++) {
				linhas.get(linha).append(imprimirPeca(linha, peca));
			}
		}
		linhas.stream().map(t -> t.toString()).forEach(t -> texto.add(t));

		return "[" + texto.toString() + "]";
	}
	
	/** Imprime a peça da linha e coluna dada nos parâmetros, se o objeto na coordenada for null, retorna '-'.
	 * @param linha Linha do tabuleiro.
	 * @param coluna Coluna do tabuleiro.
	 * @return Retorna texto da peça.
	 */
	public String imprimirPeca(int linha, int coluna) {
		return this.matriz.get(linha).get(coluna) == null ? "-" : this.matriz.get(linha).get(coluna).toString();
	};
}
