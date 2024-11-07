package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import modelo.Calculadora;

class CalculadoraTest {

	Calculadora calculadora = new Calculadora();

	private void assertEqualsPreparat(String s) {

		//Obtener numero actual
		String numActual = calculadora.getNumActual();
		
		//Comprobar TEST
		assertEquals(s, numActual);
		
		//Clear para el siguiente test
		calculadora.clear();
	}

	private void asignarOperacion(String op) {		
		try {
			calculadora.asignarOperacion(op);
		} catch (DivisionPorCeroException e) {
			calculadora.clear();
		}
	}

	private void calcular(boolean porcentaje) {		
		try {
			calculadora.calcular(false);
		} catch (DivisionPorCeroException e) {
			calculadora.clear();
		}
	}

	private void raiz() {
		try {
			calculadora.raiz();
		} catch (RaizNegativaException e) {
			calculadora.clear();
		}	
	}

	private void inversa() {
		try {
			calculadora.inversa();
		} catch (DivisionPorCeroException e) {
			calculadora.clear();
		}	
	}
	
	private void porcentaje() {
		try {
			calculadora.porcentaje();
		} catch (DivisionPorCeroException e) {
			calculadora.clear();
		}
	}
	
	@Test
	void test01() {
		// 5 + 7 = 12
		calculadora.insertarNumero("5");
		asignarOperacion("+");		
		calculadora.insertarNumero("7");
		calcular(false);

		assertEqualsPreparat("12");
	}

	@Test
	void test02() {
		// 8 - 1 3 = -5 
		calculadora.insertarNumero("8");
		asignarOperacion("-");		
		calculadora.insertarNumero("1");
		calculadora.insertarNumero("3");
		calcular(false);

		assertEqualsPreparat("-5");
	}

	@Test
	void test03() {
		// 8 - 1 3 + 7 = 2
		calculadora.insertarNumero("8");
		asignarOperacion("-");		
		calculadora.insertarNumero("1");
		calculadora.insertarNumero("3");
		asignarOperacion("+");		
		calculadora.insertarNumero("7");
		calcular(false);

		assertEqualsPreparat("2");
	}


	@Test
	void test04() {
		// 8 - 1 3 + 7 = * 3 = 6
		calculadora.insertarNumero("8");
		asignarOperacion("-");		
		calculadora.insertarNumero("1");
		calculadora.insertarNumero("3");
		asignarOperacion("+");		
		calculadora.insertarNumero("7");
		calcular(false);
		asignarOperacion("*");
		calculadora.insertarNumero("3");		
		calcular(false);		

		assertEqualsPreparat("6");
	}

	@Test
	void test05() {
		// 4 * 5 - 7 + 2 = 15
		calculadora.insertarNumero("4");
		asignarOperacion("*");
		calculadora.insertarNumero("5");
		asignarOperacion("-");
		calculadora.insertarNumero("7");
		asignarOperacion("+");
		calculadora.insertarNumero("2");
		calcular(false);

		assertEqualsPreparat("15");		
	}

	@Test
	void test06() {
		// 5 + 7 = / 6 = 2
		calculadora.insertarNumero("5");
		asignarOperacion("+");
		calculadora.insertarNumero("7");
		calcular(false);
		asignarOperacion("/");
		calculadora.insertarNumero("6");
		calcular(false);

		assertEqualsPreparat("2");
	}

	@Test
	void test07() {
		// 8 + / 2 = 4
		calculadora.insertarNumero("8");
		asignarOperacion("+");		
		asignarOperacion("/");
		calculadora.insertarNumero("2");
		calcular(false);

		assertEqualsPreparat("4");		
	}

	@Test
	void test08() {
		// - 5 + - 5 = -10
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		asignarOperacion("+");
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		calcular(false);

		assertEqualsPreparat("-10");
	}

	@Test
	void test09() {
		// - 5 * - 5 = 25
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		asignarOperacion("*");
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		calcular(false);

		assertEqualsPreparat("25");		
	}

	@Test
	void test10() {
		// - 5 - 3 = * - 5 = 40
		asignarOperacion("-");
		calculadora.insertarNumero("5");		
		asignarOperacion("-");
		calculadora.insertarNumero("3");
		calcular(false);
		asignarOperacion("*");
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		calcular(false);

		assertEqualsPreparat("40");
	}	

	@Test
	void test11() {
		// 5 , 3 * 2 , 7 = 14,31
		calculadora.insertarNumero("5");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("3");
		asignarOperacion("*");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("7");
		calcular(false);

		assertEqualsPreparat("14.31");		
	}

	@Test
	void test12() {
		// 4 / 3 = 1,3333333333333333
		calculadora.insertarNumero("4");
		asignarOperacion("/");
		calculadora.insertarNumero("3");		
		calcular(false);

		assertEqualsPreparat("1.3333333333333333");
	}

	@Test
	void test13() {
		// 8 , 2 , 3 * 2 = 16,46	
		calculadora.insertarNumero("8");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("3");
		asignarOperacion("*");
		calculadora.insertarNumero("2");
		calcular(false);

		assertEqualsPreparat("16.46");
	}	

	@Test
	void test14() {
		// , 2 * , 3 = 0,06	
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("2");
		asignarOperacion("*");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("3");
		calcular(false);

		assertEqualsPreparat("0.06");
	}

	@Test
	void test15() {
		// 4 * 3 , 5 = 14  -> 14,0 - NO es correcto !
		calculadora.insertarNumero("4");
		asignarOperacion("*");
		calculadora.insertarNumero("3");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("5");
		calcular(false);

		assertEqualsPreparat("14");
	}

	@Test
	void test16() {
		// 8 , 9 * 6 = 53,4  -> BigDecimal 53,40000000006 - NO!
		calculadora.insertarNumero("8");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("9");
		asignarOperacion("*");
		calculadora.insertarNumero("6");
		calcular(false);

		assertEqualsPreparat("53.4");
	}

	@Test
	void test17() {
		// 5 + 5 = , 3 * 6 = 1,8   -> 1,799999999999 - NO!
		calculadora.insertarNumero("5");
		asignarOperacion("+");
		calculadora.insertarNumero("5");
		calcular(false);
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("3");
		asignarOperacion("*");
		calculadora.insertarNumero("6");
		calcular(false);

		assertEqualsPreparat("1.8");
	}

	@Test
	void test18() {
		// 1 6 + 2 , 0 1 = 18,01  -> 18,009999999999998 - NO!
		calculadora.insertarNumero("1");
		calculadora.insertarNumero("6");
		asignarOperacion("+");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("1");		
		calcular(false);

		assertEqualsPreparat("18.01");
	}

	@Test
	void test19() {
		// 5 + 7 = / 0 =    -> División por cero Excepciones 1
		calculadora.insertarNumero("5");		
		asignarOperacion("+");
		calculadora.insertarNumero("7");
		calcular(false);
		asignarOperacion("/");		
		calculadora.insertarNumero("0");		
		calcular(false);
		
		assertEqualsPreparat("0");
	}

	@Test
	void test20() {
		// 5 + 7 = / 0 = * 5 =	
		calculadora.insertarNumero("5");		
		asignarOperacion("+");
		calculadora.insertarNumero("7");
		calcular(false);
		asignarOperacion("/");		
		calculadora.insertarNumero("0");		
		calcular(false);
		asignarOperacion("*");
		calculadora.insertarNumero("5");
		calcular(false);
		
		assertEqualsPreparat("0");
	}

	@Test
	void test21() {
		// 9 sqrt 3 Raiz cuadrada	
		calculadora.insertarNumero("9");
		raiz();

		assertEqualsPreparat("3");
	}
	
	@Test
	void test22() {
		// - 9 sqrt Raiz Negativa Excepciones 2
		asignarOperacion("-");
		calculadora.insertarNumero("9");
		raiz();

		assertEqualsPreparat("0");		
	}

	@Test
	void test23() {
		// - 9 + 1 3 = sqrt   2
		asignarOperacion("-");
		calculadora.insertarNumero("9");
		asignarOperacion("+");
		calculadora.insertarNumero("1");
		calculadora.insertarNumero("3");
		calcular(false);
		raiz();

		assertEqualsPreparat("2");
	}
	
	@Test
	void test24() {
		// 0 ← Retroceder
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("0");
		calculadora.retroceder();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test25() {
		//	0 8 ←
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("8");
		calculadora.retroceder();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test26() {
		// - 5 ← NO "-"
		asignarOperacion("-");
		calculadora.insertarNumero("5");
		calculadora.retroceder();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test27() {
		// 8 , ← 2 , 1 , 2 * 5 = 410,6
		calculadora.insertarNumero("8");
		calculadora.insertarNumero(".");
		calculadora.retroceder();
		calculadora.insertarNumero("2");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("1");
		calculadora.insertarNumero(".");
		calculadora.insertarNumero("2");
		asignarOperacion("*");
		calculadora.insertarNumero("5");		
		calcular(false);
		
		assertEqualsPreparat("410.6");
	}

	@Test
	void test28() {
		// 4 1/x  ->  0,25 Inversa
		calculadora.insertarNumero("4");
		inversa();
				
		assertEqualsPreparat("0.25");
	}

	@Test
	void test29() {
		// 5 * 4 = 1/x 0,05
		calculadora.insertarNumero("5");
		asignarOperacion("*");		
		calculadora.insertarNumero("4");
		calcular(false);
		inversa();
				
		assertEqualsPreparat("0.05");
	}

	@Test
	void test30() {
		// 5 * 4 1/x = 1,25
		calculadora.insertarNumero("5");
		asignarOperacion("*");		
		calculadora.insertarNumero("4");		
		inversa();
		calcular(false);
		
		assertEqualsPreparat("1.25");		
	}

	@Test
	void test31() {
		// 9 +/- * 6 +/- = 54 Cambiar signo
		calculadora.insertarNumero("9");
		calculadora.cambiarSigno();
		asignarOperacion("*");
		calculadora.insertarNumero("6");
		calculadora.cambiarSigno();
		calcular(false);
		
		assertEqualsPreparat("54");
	}

	@Test
	void test32() {
		// 9 +/- * 6 +/- = +/- - 4 = -58
		calculadora.insertarNumero("9");
		calculadora.cambiarSigno();
		asignarOperacion("*");
		calculadora.insertarNumero("6");
		calculadora.cambiarSigno();
		calcular(false);
		calculadora.cambiarSigno();
		asignarOperacion("-");
		calculadora.insertarNumero("4");
		calcular(false);
		
		assertEqualsPreparat("-58");
	}

	@Test
	void test33() {
		// 8 - 8 = +/- NO "-0"
		calculadora.insertarNumero("8");
		asignarOperacion("-");
		calculadora.insertarNumero("8");
		calcular(false);
		calculadora.cambiarSigno();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test34() {
		// 2 + 9 CE 8 = 10 Clear / Clear Error
		calculadora.insertarNumero("2");
		asignarOperacion("+");
		calculadora.insertarNumero("9");
		calculadora.clearError();
		calculadora.insertarNumero("8");
		calcular(false);
		
		assertEqualsPreparat("10");
	}

	@Test
	void test35() {
		// 2 + 9 C =
		calculadora.insertarNumero("2");
		asignarOperacion("+");
		calculadora.insertarNumero("9");
		calculadora.clear();
		calcular(false);
		
		assertEqualsPreparat("0");		
	}

	@Test
	void test36() {
		// 8 = 8
		calculadora.insertarNumero("8");		
		calcular(false);
		
		assertEqualsPreparat("8");
	}

	@Test
	void test37() {
		// - 0 0 0 5   -5   -> NO "-0005"
		asignarOperacion("-");
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("0");
		calculadora.insertarNumero("5");
		
		assertEqualsPreparat("-5");
	}

	@Test
	void test38() {
		// 8 0 * 2 0 % 16 Porcentajes
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("*");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("16");
	}

	@Test
	void test39() {
		// 8 0 / 2 0 % 400
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("/");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("400");
	}

	@Test
	void test40() {
		// 8 0 + 2 0 % 96
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("+");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("96");
	}

	@Test
	void test41() {
		// 8 0 - 2 0 % 64
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("-");
		calculadora.insertarNumero("2");
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("64");
	}

	@Test
	void test42() {
		// 5 0 %
		calculadora.insertarNumero("5");
		calculadora.insertarNumero("0");		
		porcentaje();
		
		System.out.println(calculadora.getNumActual());
		assertEqualsPreparat("0.5");
	}

	@Test
	void test43() {
		// 8 0 * 0 %
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("*");		
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test44() {
		// 8 0 / 0 %  -> División por cero
		calculadora.insertarNumero("8");
		calculadora.insertarNumero("0");
		asignarOperacion("/");		
		calculadora.insertarNumero("0");
		porcentaje();
		
		assertEqualsPreparat("0");
	}

	@Test
	void test45() {
		// 5 MS - 3 = + MR = 7 Botones de memoria
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");		
		calcular(false);
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("7");
	}

	@Test
	void test46() {
		// 5 MS - 3 M+ = + MR = 10		
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");
		calculadora.memorySumar();
		calcular(false);
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("10");
	}

	@Test
	void test47() {
		// 5 MS - 3 M- = + MR = 4
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");
		calculadora.memoryRestar();
		calcular(false);
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("4");
	}

	@Test
	void test48() {
		// 5 MS - 3 = M+ + MR = 9
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");
		calcular(false);
		calculadora.memorySumar();
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("9");
	}

	@Test
	void test49() {
		// 5 MS - 3 = M- + MR = 5
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");
		calcular(false);
		calculadora.memoryRestar();
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("5");
	}

	@Test
	void test50() {
		// 5 MS - 3 = MC + MR = 2
		calculadora.insertarNumero("5");
		calculadora.memoryStorage();
		asignarOperacion("-");		
		calculadora.insertarNumero("3");
		calcular(false);
		calculadora.memoryClear();
		asignarOperacion("+");
		calculadora.memoryRecall();
		calcular(false);
				
		assertEqualsPreparat("2");		
	}	

}