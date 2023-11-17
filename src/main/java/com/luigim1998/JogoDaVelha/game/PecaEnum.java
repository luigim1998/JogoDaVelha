package com.luigim1998.JogoDaVelha.game;

/**
 * Enum para representar peças do Jogo da Velha.
 */
public enum PecaEnum {
	X,
	O;
	
	/** Encontra a peça oposta a informada no parâmetro.
	 * @param peca Peça que se deseja encontrar sua oposta.
	 * @return A peça oposta a peça do parâmetro.
	 */
	public static PecaEnum pecaOposta(PecaEnum peca) {
		if(peca == null) {
			throw new IllegalArgumentException("Peca não pode ser null.");
		}
		return peca == PecaEnum.O ? PecaEnum.X : PecaEnum.O ;
	}
}
