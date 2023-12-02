package com.luigim1998.JogoDaVelha.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TabuleiroTest {

	@Test
	void testTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		assertAll("Peças devem começar nulas",
				() -> assertNull(tab.getPeca(0, 0)),
				() -> assertNull(tab.getPeca(2, 0)),
				() -> assertNull(tab.getPeca(0, 1)));
	}

	@Test
	void testTabuleiroCopia() {
		Tabuleiro tab = new Tabuleiro();
		tab.setPeca(0, 0, PecaEnum.O).setPeca(0, 1, PecaEnum.X);
		tab.setPeca(1, 0, PecaEnum.O).setPeca(1, 2, PecaEnum.X);
		tab.setPeca(2, 0, PecaEnum.O);
		Tabuleiro tabCopia = new Tabuleiro(tab);
		assertAll("Peças devem ser iguais",
				() -> assertEquals(tabCopia.getPeca(0, 0), PecaEnum.O, "[0, 0] = O"),
				() -> assertEquals(tabCopia.getPeca(0, 1), PecaEnum.X, "[0, 1] = X"),
				() -> assertEquals(tabCopia.getPeca(1, 0), PecaEnum.O, "[1, 0] = O"),
				() -> assertNull(tabCopia.getPeca(2, 1)),
				() -> assertNull(tabCopia.getPeca(0, 2)),
				() -> assertNull(tabCopia.getPeca(2, 2)),
				() -> assertEquals(tabCopia.getPeca(2, 0), PecaEnum.O, "[2, 0] = O")
				);
	}

	@Test
	void testSetMatriz3x3() {
		List<List<PecaEnum>> mat = new ArrayList<List<PecaEnum>>();
		for (int linhaTab = 0; linhaTab < 3; linhaTab++) {
			mat.add(new ArrayList<PecaEnum>());
			for (int colunaTab = 0; colunaTab < 3; colunaTab++) {
				mat.get(linhaTab).add(null);
			}
		}
		Tabuleiro tab = new Tabuleiro();
		assertDoesNotThrow(() -> tab.setMatriz(mat));
	}
	
	@Test
	void testSetMatriz4x3() {
		List<List<PecaEnum>> mat = new ArrayList<List<PecaEnum>>();
		for (int linhaTab = 0; linhaTab < 4; linhaTab++) {
			mat.add(new ArrayList<PecaEnum>());
			for (int colunaTab = 0; colunaTab < 3; colunaTab++) {
				mat.get(linhaTab).add(null);
			}
		}
		Tabuleiro tab = new Tabuleiro();
		assertThrows(IllegalArgumentException.class, () -> tab.setMatriz(mat));
	}
	
	@Test
	void testSetMatriz3x4() {
		List<List<PecaEnum>> mat = new ArrayList<List<PecaEnum>>();
		for (int linhaTab = 0; linhaTab < 3; linhaTab++) {
			mat.add(new ArrayList<PecaEnum>());
			for (int colunaTab = 0; colunaTab < 4; colunaTab++) {
				mat.get(linhaTab).add(null);
			}
		}
		Tabuleiro tab = new Tabuleiro();
		assertThrows(IllegalArgumentException.class, () -> tab.setMatriz(mat));
	}

}
