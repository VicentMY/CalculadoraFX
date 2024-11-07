package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Calculadora;


public class CalculadoraController implements Initializable {

	// Creación de objetos
	Calculadora calc = new Calculadora();

	// Estilo
	@ FXML private AnchorPane fondo;
	private boolean enModoClaro;

	// Botones de Números y Coma
	@FXML private Button boton_0;
	@FXML private Button boton_1;
	@FXML private Button boton_2;
	@FXML private Button boton_3;
	@FXML private Button boton_4;
	@FXML private Button boton_5;
	@FXML private Button boton_6;
	@FXML private Button boton_7;
	@FXML private Button boton_8;
	@FXML private Button boton_9;
	@FXML private Button boton_coma;

	// Botones de Memoria
	@FXML private Button boton_MC;
	@FXML private Button boton_MR;
	@FXML private Button boton_MS;
	@FXML private Button boton_Mmas;
	@FXML private Button boton_Mmenos;

	// Botones de Operaciones
	@FXML private Button boton_sumar;
	@FXML private Button boton_restar;
	@FXML private Button boton_multiplicar;
	@FXML private Button boton_dividir;
	@FXML private Button boton_porcentaje;
	@FXML private Button boton_raizQ;
	@FXML private Button boton_operar;

	// Botones de funciones de la calculadora
	@FXML private Button boton_retroceso;
	@FXML private Button boton_limpiarEr;
	@FXML private Button boton_limpiar;
	@FXML private Button boton_cambioSigno;
	@FXML private Button boton_invertirFrac;

	// Menús
	// Ver
	@FXML private Menu menu_Ver;
	@FXML private MenuItem submenu_Historial;
	@FXML private MenuItem submenu_ModoClaroOscuro;
	@FXML private MenuItem submenu_Debug;
	@FXML private MenuItem submenu_Salir;
	// Ayuda
	@FXML private Menu menu_Ayuda;
	@FXML private MenuItem submenu_Ayuda;
	@FXML private MenuItem submenu_AcercaDe;

	// Pantallas
	@FXML private Label pantalla_operacion;
	@FXML private Label pantalla_resultado;
	@FXML private Label pantalla_memoria;
	@FXML private Label fecha;
	@FXML private Label creditos;


	// Teclado (Key Listenner)
	@FXML
	void pulsarTecla(KeyEvent event) {
		switch (event.getCode()) {
		// Numeros
		case DIGIT1: case NUMPAD1: insertarNumero("1"); break;
		case DIGIT2: case NUMPAD2: insertarNumero("2"); break;
		case DIGIT3: case NUMPAD3: insertarNumero("3"); break;
		case DIGIT4: case NUMPAD4: insertarNumero("4"); break;
		case DIGIT5: case NUMPAD5: insertarNumero("5"); break;
		case DIGIT6: case NUMPAD6: insertarNumero("6"); break;
		case DIGIT7: case NUMPAD7: insertarNumero("7"); break;
		case DIGIT8: case NUMPAD8: insertarNumero("8"); break;
		case DIGIT9: case NUMPAD9: insertarNumero("9"); break;
		case DIGIT0: case NUMPAD0: insertarNumero("0"); break;
		case COMMA:  case DECIMAL: insertarNumero("."); break;
		// Operaciones
		case ENTER: case FINAL:	   calcular(); 			  break;
		case PLUS:  case ADD: 	   asignarOperacion("+"); break;
		case MINUS: case SUBTRACT: asignarOperacion("-"); break;
		case MULTIPLY: 			   asignarOperacion("*"); break;
		case DIVIDE: 			   asignarOperacion("/"); break;
		// Funciones
		case BACK_SPACE: retroceder(); break;
		case DELETE: 	 clearError(); break;
		case ESCAPE:	 clear();	   break;
		default: break;
		}

		// Combinaciones de teclas
		KeyCodeCombination memoryClear = new KeyCodeCombination(KeyCode.L, KeyCodeCombination.CONTROL_DOWN);
		if (memoryClear.match(event)) BorrarMemoria();

		KeyCodeCombination memoryRecall = new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN);
		if (memoryRecall.match(event)) TraerDeMemoria();

		KeyCodeCombination memoryStorage = new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN);
		if (memoryStorage.match(event)) almacenarMemoria();

		KeyCodeCombination memorySum = new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN);
		if (memorySum.match(event)) SumarMemoria();

		KeyCodeCombination memorySubtract = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);
		if (memorySubtract.match(event)) RestarMemoria();
	}


	// Métodos
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		this.enModoClaro = true;

		fecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

		// Internacionalización (I18N) (NO FUNCIONA)

		// // Menu Ver
		// menu_Ver.textProperty().bind(I18N.createStringBinding("form.menu_Ver"));
		// submenu_Historial.textProperty().bind(I18N.createStringBinding("form.submenu_Historial"));
		// submenu_ModoClaroOscuro.textProperty().bind(I18N.createStringBinding("form.submenu_ModoClaroOscuro"));
		// submenu_Debug.textProperty().bind(I18N.createStringBinding("form.submenu_Debug"));
		// submenu_Salir.textProperty().bind(I18N.createStringBinding("form.submenu_Salir"));

		// // Menu Ayuda
		// menu_Ayuda.textProperty().bind(I18N.createStringBinding("form.menu_Ayuda"));
		// submenu_Ayuda.textProperty().bind(I18N.createStringBinding("form.submenu_Ayuda"));
		// submenu_AcercaDe.textProperty().bind(I18N.createStringBinding("form.submenu_AcercaDe"));

		// Asignar los Eventos de las opciones de menu
		submenu_Salir.setOnAction((event) -> salir());
		submenu_Debug.setOnAction((event) -> activarDebug());
		submenu_AcercaDe.setOnAction((event) -> mostrarVentana("../vista/VentanaAcercaDe.fxml", "Acerca de Calculadora 3.0", true));
		submenu_Ayuda.setOnAction((event) -> mostrarVentana("../vista/VentanaAyuda.fxml", "Ayuda para el uso", false));
		submenu_Historial.setOnAction((event) -> mostrarVentana("../vista/VentanaHistorial.fxml", "Historial de operaciones", false));

		// Evento botones
		// Numeros y coma
		boton_0.setOnMouseClicked((event) -> insertarNumero("0"));
		boton_1.setOnMouseClicked((event) -> insertarNumero("1"));
		boton_2.setOnMouseClicked((event) -> insertarNumero("2"));
		boton_3.setOnMouseClicked((event) -> insertarNumero("3"));
		boton_4.setOnMouseClicked((event) -> insertarNumero("4"));
		boton_5.setOnMouseClicked((event) -> insertarNumero("5"));
		boton_6.setOnMouseClicked((event) -> insertarNumero("6"));
		boton_7.setOnMouseClicked((event) -> insertarNumero("7"));
		boton_8.setOnMouseClicked((event) -> insertarNumero("8"));
		boton_9.setOnMouseClicked((event) -> insertarNumero("9"));
		boton_coma.setOnMouseClicked((event) -> insertarNumero("."));

		// Operaciones
		boton_sumar.setOnMouseClicked((event) -> asignarOperacion("+"));
		boton_restar.setOnMouseClicked((event) -> asignarOperacion("-"));
		boton_multiplicar.setOnMouseClicked((event) -> asignarOperacion("*"));
		boton_dividir.setOnMouseClicked((event) -> asignarOperacion("/"));
		boton_porcentaje.setOnMouseClicked((event) -> asignarOperacion("%"));
		boton_raizQ.setOnMouseClicked((event) -> asignarOperacion("√"));
		boton_operar.setOnMouseClicked((event) -> calcular());

		// Funciones de la calculadora
		boton_retroceso.setOnMouseClicked((event) -> retroceder());
		boton_limpiar.setOnMouseClicked((event) -> clear());
		boton_limpiarEr.setOnMouseClicked((event) -> clearError());
		boton_cambioSigno.setOnMouseClicked((event) -> cambiarSigno());
		boton_invertirFrac.setOnMouseClicked((event) -> inversa());

		// Memoria
		boton_MC.setOnMouseClicked((event) -> BorrarMemoria());
		boton_MR.setOnMouseClicked((event) -> TraerDeMemoria());
		boton_MS.setOnMouseClicked((event) -> almacenarMemoria());
		boton_Mmas.setOnMouseClicked((event) -> SumarMemoria());
		boton_Mmenos.setOnMouseClicked((event) -> RestarMemoria());

		// Cambiar modo Claro a Oscuro y viceversa
		submenu_ModoClaroOscuro.setOnAction((event) -> cambioModo());
	}

	// Menu
	private void salir() {

		Stage stage = (Stage)submenu_Salir.getParentPopup().getOwnerWindow();
		stage.close();
	}

	// Debug
	private void activarDebug() {

		calc.debugEnable();
	}

	//Mostrar otra ventana
	private void mostrarVentana(String rutaFXML, String titulo, boolean modal) {

		String icon = "../vista/images/calculadora.png";
		try{
			//Léeme el source del archivo que te digo fxml y te pongo el path
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();

			//Creame un nuevo Stage (una nueva ventana vacía)
			Stage stage = new Stage();

			//Asignar al Stage la escena que anteriormente hemos leído y guardado en root
			stage.setTitle(titulo);
			stage.setResizable(false);

			if (modal) {

				stage.initModality(Modality.APPLICATION_MODAL);
			}

			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image(getClass().getResource(icon).toExternalForm()));

			//Mostrar el Stage (ventana)
			stage.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	// Modo Claro/Oscuro

	private void cambioModo() {

		String claro  = "vista/css/CalculadoraLight.css";
		String oscuro = "vista/css/CalculadoraDark.css";

		enModoClaro = !enModoClaro;

		if (enModoClaro) {

			fondo.getStylesheets().remove(oscuro);
			fondo.getStylesheets().add(claro);
		} else {

			fondo.getStylesheets().remove(claro);
			fondo.getStylesheets().add(oscuro);
		}
	}

	// Operaciones con numeros
	private void insertarNumero(String numero) {

		calc.insertarNumero(numero);
		refrescarTextoResultado();
	}

	private void asignarOperacion(String operacion) {

		try {

			if (operacion.equals("√")) {

				raiz();
				pantalla_operacion.setText(operacion + " " + calc.getNum1());
			}
			else {

				pantalla_operacion.setText(calc.getNum1() + " " + operacion);
			}

			calc.asignarOperacion(operacion);

			refrescarTextoResultado();
			pantalla_operacion.setText(calc.getNum1() + " " + operacion);

		} catch (DivisionPorCeroException e) {

			pantalla_resultado.setText(e.error());
		}		
	}

	private void retroceder() {

		calc.retroceder();
		refrescarTextoResultado();
	}

	private void cambiarSigno() {

		calc.cambiarSigno();
		refrescarTextoResultado();
	}

	private void calcular() {

		try {

			try {

				calc.calcular(false);

			} catch (NumberFormatException e) {

				pantalla_resultado.setText("");
			}

			refrescarTextoResultado();

			if (!(calc.getNum2() + "").contains(calc.getNum2() + "")) {

				pantalla_operacion.setText(pantalla_operacion.getText() + " " + calc.getNum2());
			}


		} catch (DivisionPorCeroException e) {

			pantalla_resultado.setText(e.error());
		}
	}

	private void raiz() {

		try {

			calc.raiz();
			pantalla_resultado.setText(calc.getNumActual());

		} catch (RaizNegativaException e) {

			pantalla_resultado.setText(e.error());
		}
	}

	private void inversa() {

		try {
			pantalla_resultado.setText(calc.inversa() + "");

		} catch (DivisionPorCeroException e) {

			pantalla_resultado.setText(e.error());
		}
	}


	private void almacenarMemoria() {

		calc.memoryStorage();

		pantalla_memoria.setText("M");
	}

	private void TraerDeMemoria() {

		calc.memoryRecall();

		refrescarTextoResultado();
	}

	private void BorrarMemoria() {

		calc.memoryClear();
		pantalla_memoria.setText("");
	}

	private void SumarMemoria() {

		calc.memorySumar();
	}

	private void RestarMemoria() {

		calc.memoryRestar();
	}


	private void clearError() {

		calc.clearError();
		refrescarTextoResultado();
	}

	private void clear() {

		calc.clear();
		refrescarTextoResultado();
		pantalla_operacion.setText("");
		pantalla_memoria.setText("");
	}

	private void refrescarTextoResultado() {

		pantalla_resultado.setText(utiles.Utiles.darFormatoNumero(calc.getNumActual()));
	}

}
