package excepciones;

public class DivisionPorCeroException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String error() {
		
		return "División por Cero";
	}
}
