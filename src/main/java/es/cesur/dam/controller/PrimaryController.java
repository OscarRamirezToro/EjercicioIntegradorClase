package es.cesur.dam.controller;

import es.cesur.dam.data.ProductoDaoHibernateImpl;
import es.cesur.dam.model.Producto;
import es.cesur.dam.service.ProductoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimaryController implements Initializable {

    private static final Logger logger = Logger.getLogger(PrimaryController.class.getName());
    private final ProductoService productoService = new ProductoService(new ProductoDaoHibernateImpl());

    @FXML
    private TableView<Producto> tablaDatos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    private ObservableList<Producto> listado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listado = FXCollections.observableArrayList();
        tablaDatos.setItems(listado);

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        try {
            listado.setAll(productoService.obtenerTodos());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los datos", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se pudieron cargar los datos de la base de datos");
            alert.showAndWait();
        }
    }
}