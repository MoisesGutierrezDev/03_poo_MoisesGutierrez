package vallegrande.edu.pe.view;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import vallegrande.edu.pe.model.Producto;

public class MainView extends BorderPane {

    private Button btnInicio;
    private Button btnProductos;
    private TableView<Producto> tablaProductos;

    // --- NUEVOS CONTROLES PARA EL REGISTRO ---
    private TextField txtNombre;
    private TextField txtCategoria;
    private TextField txtPrecio;
    private TextField txtStock;
    private Button btnRegistrar;

    public MainView() {
        crearMenu();
        crearFormulario(); // Inicializa los campos de texto
        crearTabla();
        mostrarInicio();
    }

    private void crearMenu() {
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setPrefWidth(220);

        Label titulo = new Label("MI SISTEMA");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        btnInicio = crearBoton("Inicio");
        btnProductos = crearBoton("Productos");

        menu.getChildren().addAll(titulo, btnInicio, btnProductos);
        menu.setStyle("-fx-background-color: #2563EB;");

        setLeft(menu);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(40);
        return boton;
    }

    // --- MÉTODOS Y CONTROLES DEL FORMULARIO DE REGISTRO ---
    private void crearFormulario() {
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del producto");

        txtCategoria = new TextField();
        txtCategoria.setPromptText("Categoría");

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");

        txtStock = new TextField();
        txtStock.setPromptText("Stock");

        btnRegistrar = new Button("Registrar");
    }

    public void mostrarInicio() {
        VBox contenido = new VBox(10);
        contenido.setAlignment(Pos.CENTER);

        Label titulo = new Label("BIENVENIDO");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label texto = new Label("Sistema de gestión de productos");
        contenido.getChildren().addAll(titulo, texto);

        setCenter(contenido);
    }

    public void mostrarProductos() {
        VBox contenido = new VBox(15);
        contenido.setPadding(new Insets(30));

        Label titulo = new Label("PRODUCTOS");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        // Agregamos el título, los 4 campos, el botón y la tabla en orden vertical
        contenido.getChildren().addAll(
                titulo,
                txtNombre,
                txtCategoria,
                txtPrecio,
                txtStock,
                btnRegistrar,
                tablaProductos
        );

        setCenter(contenido);
    }

    private void crearTabla() {
        tablaProductos = new TableView<>();

        TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Producto, String> colCategoria = new TableColumn<>("Categoría");
        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tablaProductos.getColumns().addAll(colId, colNombre, colCategoria, colPrecio, colStock);
    }

    public void mostrarDatosProductos(List<Producto> productos) {
        tablaProductos.setItems(FXCollections.observableArrayList(productos));
    }

    // --- MÉTODO PARA LIMPIAR LOS CAMPOS TRAS REGISTRAR ---
    public void limpiarCampos() {
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtStock.clear();
    }

    // --- GETTERS DE LOS CONTROLES Y BOTONES ---
    public Button getBtnInicio() { return btnInicio; }
    public Button getBtnProductos() { return btnProductos; }
    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtCategoria() { return txtCategoria; }
    public TextField getTxtPrecio() { return txtPrecio; }
    public TextField getTxtStock() { return txtStock; }
    public Button getBtnRegistrar() { return btnRegistrar; }
}