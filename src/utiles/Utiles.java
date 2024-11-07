package utiles;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Utiles {

	// Dar formato al número resultado
	public static String darFormatoNumero(String numero) {

		BigDecimal b1;
		DecimalFormatSymbols simbolo;
		DecimalFormat formato;

		if (numero.equals("")) {

			numero = "0";
		}
		simbolo = new DecimalFormatSymbols();

		simbolo.setGroupingSeparator('.');
		simbolo.setDecimalSeparator(',');

		formato = new DecimalFormat("#,###.##########", simbolo);

		b1 = new BigDecimal(numero);

		numero = formato.format(b1);

		return numero;
	}
}
