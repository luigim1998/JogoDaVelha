package jogo;

import static org.junit.jupiter.api.Assertions.*;

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
				() -> assertEquals(tabCopia.getPeca(0, 0), PecaEnum.O),
				() -> assertEquals(tabCopia.getPeca(0, 1), PecaEnum.X),
				() -> assertEquals(tabCopia.getPeca(1, 0), PecaEnum.O),
				() -> assertNull(tabCopia.getPeca(2, 1)),
				() -> assertNull(tabCopia.getPeca(0, 2)),
				() -> assertNull(tabCopia.getPeca(2, 2)),
				() -> assertEquals(tabCopia.getPeca(2, 0), PecaEnum.O)
				);
	}

	@Test
	void testSetMatriz3x3() {
		PecaEnum[][] mat = new PecaEnum[3][3];
		Tabuleiro tab = new Tabuleiro();
		assertDoesNotThrow(() -> tab.setMatriz(mat));
	}
	
	@Test
	void testSetMatriz4x3() {
		PecaEnum[][] mat = new PecaEnum[4][3];
		Tabuleiro tab = new Tabuleiro();
		assertThrows(IllegalArgumentException.class, () -> tab.setMatriz(mat));
	}
	
	@Test
	void testSetMatriz3x4() {
		PecaEnum[][] mat = new PecaEnum[3][4];
		Tabuleiro tab = new Tabuleiro();
		assertThrows(IllegalArgumentException.class, () -> tab.setMatriz(mat));
	}

}
