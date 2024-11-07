package modelo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;

public class Calculadora {

	// Atributos
	private double num1;
	private double num2;

	private String operacion;
	private String numActual;
	private String numMemoria;

	private boolean debug;
	private boolean salidaOp;
	boolean porcentajeSumar;

	// Constructores
	public Calculadora() {

		this.num1 = 0;
		this.num2 = 0;
		this.operacion  = "";
		this.numActual  = "";
		this.numMemoria = "";
		this.debug = false;
		this.salidaOp = false;
	}

	// Getters (para Junit y pantalla)
	public String getNumActual() {

		return this.numActual;
	}

	public Double getNum1() {

		return this.num1;
	}

	public Double getNum2() {

		return this.num2;
	}

	// Trabajando con las variables

	public void insertarNumero(String numero) {

		try {

			if (salidaOp) {

				if (this.operacion.equals("%")) {
					this.num1 = 0;
					this.num2 = 0;
				}
				this.numActual = "";
				this.salidaOp = false;

				// Debug
				if (debug) debug();
			}

			if (numero.equals(".")) {

				if (this.numActual.equals("")) {

					this.numActual = "0.";
				}

				if (!this.numActual.contains(".")) {

					this.numActual += numero;
				}
			}
			else {

				this.numActual = concatenar(numero);

				if (!(this.num1 == 0)) {


					this.num2 = Double.parseDouble(this.numActual);
				}
				else {

					this.num1 = Double.parseDouble(this.numActual);
				}
			}


			// Debug
			if (debug) debug();

		} catch (NumberFormatException e) {}

	}

	public void asignarOperacion(String operacion) throws DivisionPorCeroException {


		try {

			if (operacion.equals("-") && this.numActual.equals("")) {

				this.numActual = "-";
			}
			else if (operacion.equals("/") && this.numActual.equals("")) {

				this.operacion = operacion;
			}
			else if (operacion.equals("%")) {

				if (this.operacion.equals("+")) {

					this.porcentajeSumar = true;
				}
				else {

					this.porcentajeSumar = false;
				}

				this.operacion = "%";

				calcular(this.porcentajeSumar);

				salidaOp = true;
			}
			else {

				if (this.num2 == 0 && this.operacion.equals("/")) {

					this.numActual = "";

					throw new DivisionPorCeroException();
				}

				else if (!(this.num1 == 0 && this.num2 == 0)) {

					if (!(this.num1 == 0 || this.num2 == 0)) {

						calcular(false);
					}

					this.num1 = Double.parseDouble(this.numActual);
					this.num2 = 0;
				}

				this.operacion = operacion;

				if (!operacion.equals("√")) {

					this.numActual = "";
				}
			}
			// Debug
			if (debug) debug();

		} catch (NumberFormatException e) {
		}
	}

	public void calcular(boolean porcentaje) throws DivisionPorCeroException {

		try {

			switch (this.operacion) {
			case "+": this.numActual = "" + sumar(); 	   break;
			case "-": this.numActual = "" + restar(); 	   break;
			case "*": this.numActual = "" + multiplicar(); break;
			case "/": this.numActual = "" + dividir(); 	   break;
			case "%": this.numActual = "" + porcentaje();  break;
			}

			BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(this.numActual));


			this.numActual = b1.stripTrailingZeros().toPlainString();

			this.salidaOp = true;

			// Debug
			if (debug) debug();

		} catch (NumberFormatException e) {}
	}

	// Acciones de memoria

	// Guardar en memoria
	public void memoryStorage() {

		this.numMemoria = this.numActual;

		// Debug
		if (debug) debug();
	}

	// Sacar de la memoria
	public void memoryRecall() {

		this.numActual = this.numMemoria;

		insertarNumero(this.numActual);

		// Debug
		if (debug) debug();
	}

	// Sumar al número en memoria
	public void memorySumar() {

		BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(this.numMemoria.equals("") ? "0" : this.numMemoria));
		BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(this.numActual.equals("") ? "0" : this.numActual));

		this.numMemoria = "" + b1.add(b2);

		// Debug
		if (debug) debug();
	}

	// Restar al número en memoria
	public void memoryRestar() {

		BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(this.numMemoria.equals("") ? "0" : this.numMemoria));
		BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(this.numActual.equals("") ? "0" : this.numActual));

		this.numMemoria = "" + b1.subtract(b2);

		// Debug
		if (debug) debug();
	}

	// Limpiar la memoria
	public void memoryClear() {

		this.numMemoria = "";

		// Debug
		if (debug) debug();
	}

	// Operaciones aritméticas
	public double sumar() {

		// Con Bigdecimal
		BigDecimal b1 = BigDecimal.valueOf(this.num1);
		BigDecimal b2 = BigDecimal.valueOf(this.num2);

		return b1.add(b2).doubleValue();
	}

	public double restar() {

		// Con Bigdecimal
		BigDecimal b1 = BigDecimal.valueOf(this.num1);
		BigDecimal b2 = BigDecimal.valueOf(this.num2);

		return b1.subtract(b2).doubleValue();
	}

	public double multiplicar() {

		// Con Bigdecimal
		BigDecimal b1 = BigDecimal.valueOf(this.num1);
		BigDecimal b2 = BigDecimal.valueOf(this.num2);

		return b1.multiply(b2).doubleValue();
	}

	public double dividir() throws DivisionPorCeroException {

		if ((this.num1 == 0 || this.num2 == 0) && this.operacion.equals("/")) {

			this.numActual = "";

			throw new DivisionPorCeroException();
		}

		// Con Bigdecimal
		BigDecimal b1 = BigDecimal.valueOf(this.num1);
		BigDecimal b2 = BigDecimal.valueOf(this.num2);

		try {
			BigDecimal b3 = b1.divide(b2);

			if ((b1 + "").contains("E")) {

				b3 = b1.divide(b2, RoundingMode.UP);
			}

			return b3.doubleValue();

		} catch (ArithmeticException e) {

			return b1.divide(b2,16, RoundingMode.HALF_UP).doubleValue();
		}	
	}

	public String porcentaje() throws DivisionPorCeroException {

		BigDecimal b1 = BigDecimal.valueOf(this.num1);
		BigDecimal b2 = BigDecimal.valueOf(this.num2);
		BigDecimal cien = BigDecimal.valueOf(100);

		if (porcentajeSumar) {

			return b1.add(b1.multiply(b2.divide(cien))).toPlainString();
		}
		else {

			return b1.subtract(b1.multiply(b2.divide(cien))).toPlainString();
		}
	}

	public void raiz() throws RaizNegativaException {

		BigDecimal b1 = new BigDecimal(this.numActual);
		MathContext mc = new MathContext(10);

		if (Double.parseDouble(numActual) < 0) {

			throw new RaizNegativaException();
		}

		this.numActual = b1.sqrt(mc).toPlainString();
	}

	public double inversa() throws DivisionPorCeroException {

		try {

			if (this.numActual.equals("0") || this.numActual.equals("")) {

				throw new DivisionPorCeroException();
			}

			double n = 1 / (Double.parseDouble(this.numActual));

			this.numActual = n + "";

			if (this.num2 == 0) {

				this.num1 = n;
			}
			else {

				this.num2 = n;
			}

			return n;

		} catch (NumberFormatException e) {

			throw new DivisionPorCeroException(); 
		}

	}

	// Cambiar signo al valor
	public void cambiarSigno() {

		try {

			if (!this.numActual.equals("0")) {

				double n = Double.parseDouble(this.numActual);

				if (this.num2 == 0) {

					this.num1 = n * -1;
				}
				else {
					this.num2 = n * -1;
				}

				this.numActual = "" + (n * -1);
			}

			// Debug
			if (debug) debug();

		} catch (NumberFormatException e) {}
	}

	// Resetear todas las variables
	public void clear() {

		this.num1 = 0;
		this.num2 = 0;
		this.numActual  = "0";
		this.numMemoria = "";
		this.operacion  = "";

		// Debug
		if (debug) debug();
	}

	// Eliminar el error (número actual)
	public void clearError() {

		this.numActual = "";
	}

	// Concatenar número al numActual
	public String concatenar(String numero) {

		if (this.numActual.equals("-") && numero.equals("0")) {

			return this.numActual;
		}
		else {

			return this.numActual + numero;
		}
	}

	// Eliminar el último número
	public void retroceder() {

		if (!(this.numActual.length() == 0)) {

			this.numActual = this.numActual.substring(0, this.numActual.length() -1);

			if (this.numActual.equals("-") || this.numActual.equals("")) {

				this.numActual = "0";
			}
		}
	}

	// Debug
	public void debugEnable() {

		if (debug) {

			this.debug = false;
		}
		else {

			this.debug = true;
		}
	}

	public void debug() {

		System.out.println("numActual  " + this.numActual);
		System.out.println("num1       " + this.num1);
		System.out.println("operacion  " + this.operacion);
		System.out.println("num2       " + this.num2);
		System.out.println();
		System.out.println("numMemoria " + this.numMemoria);
		System.out.println();
	}
}
