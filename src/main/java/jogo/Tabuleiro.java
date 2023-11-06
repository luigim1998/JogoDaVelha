package jogo;

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
	
	public void setPeca(int linha, int coluna, PecaEnum peca) {
		this.matriz[linha][coluna] = peca;
	}
}
