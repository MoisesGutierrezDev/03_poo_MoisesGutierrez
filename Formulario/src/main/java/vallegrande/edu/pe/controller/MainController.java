package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Producto;
import vallegrande.edu.pe.model.ProductoDAO;
import vallegrande.edu.pe.view.MainView;

public class MainController {
    private MainView view;
    private ProductoDAO productoDAO;
    private Producto productoSeleccionado; // Guarda la referencia del producto seleccionado en la tabla

    public MainController(MainView view) {
        this.view = view;
        this.productoDAO = new ProductoDAO();

        // Eventos del menú principal
        this.view.getBtnInicio().setOnAction(e -> this.view.mostrarInicio());
        this.view.getBtnProductos().setOnAction(e -> {
            this.view.mostrarProductos();
            cargarProductos();
        });

        // Eventos del CRUD
        this.view.getBtnRegistrar().setOnAction(e -> registrarProducto());
        this.view.getBtnEditar().setOnAction(e -> editarProducto());
        this.view.getBtnEliminar().setOnAction(e -> eliminarProducto());
        this.view.getBtnLimpiar().setOnAction(e -> limpiarFormulario());

        // Escuchador para detectar cuando el usuario hace clic en una fila de la tabla
        this.view.getTablaProductos().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> seleccionarProducto(newValue)
        );
    }

    private void cargarProductos() {
        this.view.mostrarDatosProductos(productoDAO.listar());
    }

    // Al seleccionar una fila de la tabla, llena automáticamente las cajas de texto
    private void seleccionarProducto(Producto p) {
        if (p != null) {
            this.productoSeleccionado = p;
            this.view.getTxtNombre().setText(p.getNombre());
            this.view.getTxtCategoria().setText(p.getCategoria());
            this.view.getTxtPrecio().setText(String.valueOf(p.getPrecio()));
            this.view.getTxtStock().setText(String.valueOf(p.getStock()));
        }
    }

    private void registrarProducto() {
        String nombre = view.getTxtNombre().getText();
        String categoria = view.getTxtCategoria().getText();
        String precioText = view.getTxtPrecio().getText();
        String stockText = view.getTxtStock().getText();

        if (nombre.isEmpty() || categoria.isEmpty() || precioText.isEmpty() || stockText.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioText);
            int stock = Integer.parseInt(stockText);

            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setCategoria(categoria);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setStock(stock);

            boolean insertado = productoDAO.registrar(nuevoProducto);

            if (insertado) {
                System.out.println("Producto registrado con éxito.");
                limpiarFormulario();
                cargarProductos();
            } else {
                System.out.println("Error al insertar el producto en la base de datos.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio y el stock deben ser valores numéricos válidos.");
        }
    }

    private void editarProducto() {
        if (productoSeleccionado == null) {
            System.out.println("Por favor, selecciona un producto de la tabla para editar.");
            return;
        }

        String nombre = view.getTxtNombre().getText();
        String categoria = view.getTxtCategoria().getText();
        String precioText = view.getTxtPrecio().getText();
        String stockText = view.getTxtStock().getText();

        if (nombre.isEmpty() || categoria.isEmpty() || precioText.isEmpty() || stockText.isEmpty()) {
            System.out.println("Por favor, llena todos los campos para actualizar.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioText);
            int stock = Integer.parseInt(stockText);

            // Asignamos los datos modificados manteniendo el ID original
            productoSeleccionado.setNombre(nombre);
            productoSeleccionado.setCategoria(categoria);
            productoSeleccionado.setPrecio(precio);
            productoSeleccionado.setStock(stock);

            boolean actualizado = productoDAO.actualizar(productoSeleccionado);

            if (actualizado) {
                System.out.println("Producto actualizado con éxito.");
                limpiarFormulario();
                cargarProductos();
            } else {
                System.out.println("Error al actualizar el producto en la base de datos.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio y el stock deben ser valores numéricos válidos.");
        }
    }

    private void eliminarProducto() {
        if (productoSeleccionado == null) {
            System.out.println("Por favor, selecciona un producto de la tabla para eliminar.");
            return;
        }

        boolean eliminado = productoDAO.eliminar(productoSeleccionado.getId());

        if (eliminado) {
            System.out.println("Producto eliminado con éxito.");
            limpiarFormulario();
            cargarProductos();
        } else {
            System.out.println("Error al eliminar el producto de la base de datos.");
        }
    }

    private void limpiarFormulario() {
        this.productoSeleccionado = null;
        this.view.limpiarCampos();
    }
}