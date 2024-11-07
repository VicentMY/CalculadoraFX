package excepciones;

public class RaizNegativaException extends Exception {

	private static final long serialVersionUID = 1L;

	public String error() {

		return "Raíz Negativa";
	}
}
