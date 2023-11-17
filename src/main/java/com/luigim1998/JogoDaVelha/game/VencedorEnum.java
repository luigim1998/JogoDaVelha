package com.luigim1998.JogoDaVelha.game;


/**
 * Enum que representa uma vitória, empate ou jogo não finalizado.
 */
public enum VencedorEnum {
	X,
	O,
	/**
	 * Não há possibilidade de alguém vencer.
	 */
	EMPATE,
	/**
	 * A jogada não é final.
	 */
	NAO_FINALIZADO;
}
