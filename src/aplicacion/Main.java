package aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			String fxml = "/vista/Calculadora.fxml";
			String css	= "/vista/css/CalculadoraLight.css";
			String icon = "/vista/images/calculadora.png";

			// Indicar el idioma
			// Locale locale = new Locale("es");
			// ResourceBundle bundle = ResourceBundle.getBundle("utiles.idiomas.strings", locale);

			// Cargar la ventana
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(fxml));

			// Cargar la Scene, css y imagenes
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResource(icon).toExternalForm()));

			// Asignar propiedades al Stage
			primaryStage.setTitle("Calculadora 3.0");
			// primaryStage.titleProperty().bind(I18N.createStringBinding("form.titulo"));
			primaryStage.setResizable(false);
			// Asignar la scene y mostrar
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
