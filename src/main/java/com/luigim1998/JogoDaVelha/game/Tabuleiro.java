package com.luigim1998.JogoDaVelha.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Tabuleiro {
	private PecaEnum[][] matriz;
	
	public Tabuleiro() {
		this.matriz = new PecaEnum[3][3];
	}
	
	public Tabuleiro(Tabuleiro original) {
		this.matriz = new PecaEnum[3][3];

		for (int linhaTab = 0; linhaTab < 3; linhaTab++) {
			for (int colunaTab = 0; colunaTab < 3; colunaTab++) {
				this.matriz[linhaTab][colunaTab] = original.matriz[linhaTab][colunaTab];
			}
		}
	}

	public PecaEnum[][] getMatriz() {
		return this.matriz;
	}

	public void setMatriz(PecaEnum[][] matrizPecas) {
		if (matrizPecas.length != 3) {
			throw new IllegalArgumentException("A matriz não pode ter uma quantidade diferente de três linhas.");
		}
		if (matrizPecas[0].length != 3 || matrizPecas[1].length != 3 || matrizPecas[2].length != 3) {
			throw new IllegalArgumentException("As linhas da matriz não podem ter uma quantidade diferente de três colunas.");
		}
		this.matriz = matrizPecas;
	}
	
	public PecaEnum getPeca(int linha, int coluna) {
		return this.matriz[linha][coluna];
	}
	
	public Tabuleiro setPeca(int linha, int coluna, PecaEnum peca) {
		this.matriz[linha][coluna] = peca;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
		return result;
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
		return Arrays.deepEquals(matriz, other.matriz);
	}

	@Override
	public String toString() {
		StringJoiner texto = new StringJoiner(",");
		List<StringBuilder> linhas = new ArrayList<StringBuilder>();

		for (int linha = 0; linha < matriz.length; linha++) {
			linhas.add(new StringBuilder(3));
			for (int peca = 0; peca < matriz[linha].length; peca++) {
				linhas.get(linha).append(imprimirPeca(linha, peca));
			}
		}
		linhas.stream().map(t -> t.toString()).forEach(t -> texto.add(t));

		return "[" + texto.toString() + "]";
	}
	
	public String imprimirPeca(int linha, int coluna) {
		return matriz[linha][coluna] == null ? "-" : matriz[linha][coluna].toString();
	};
}
