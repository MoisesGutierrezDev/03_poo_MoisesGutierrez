package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Producto;
import vallegrande.edu.pe.model.ProductoDAO;
import vallegrande.edu.pe.view.MainView;

public class MainController {
    private MainView view;
    private ProductoDAO productoDAO;

    public MainController(MainView view) {
        this.view = view;
        this.productoDAO = new ProductoDAO();

        // Eventos del menú principal
        this.view.getBtnInicio().setOnAction(e -> this.view.mostrarInicio());
        this.view.getBtnProductos().setOnAction(e -> {
            this.view.mostrarProductos();
            cargarProductos();
        });

        // Evento para el botón Registrar
        this.view.getBtnRegistrar().setOnAction(e -> registrarProducto());
    }

    private void cargarProductos() {
        this.view.mostrarDatosProductos(productoDAO.listar());
    }

    private void registrarProducto() {
        // 1. Obtener los textos de los campos
        String nombre = view.getTxtNombre().getText();
        String categoria = view.getTxtCategoria().getText();
        String precioText = view.getTxtPrecio().getText();
        String stockText = view.getTxtStock().getText();

        // 2. Validar que no estén vacíos
        if (nombre.isEmpty() || categoria.isEmpty() || precioText.isEmpty() || stockText.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        try {
            // 3. Convertir precio y stock a tipos numéricos
            double precio = Double.parseDouble(precioText);
            int stock = Integer.parseInt(stockText);

            // 4. Crear objeto Producto con los datos ingresados
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setCategoria(categoria);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setStock(stock);

            // 5. Insertar en la base de datos a través del DAO
            boolean insertado = productoDAO.registrar(nuevoProducto);

            if (insertado) {
                System.out.println("Producto registrado con éxito.");
                view.limpiarCampos(); // Limpia los inputs
                cargarProductos();   // Actualiza la TableView en vivo
            } else {
                System.out.println("Error al insertar el producto en la base de datos.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio y el stock deben ser valores numéricos válidos.");
        }
    }
}