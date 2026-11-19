package es.cesur.dam.service;

import es.cesur.dam.data.DAO;
import es.cesur.dam.model.Producto;
import java.util.List;
import java.util.Optional;

public class ProductoService {

    private final DAO<Producto, Integer> productoDAO;

    public ProductoService(DAO<Producto, Integer> productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> obtenerTodos() {
        return productoDAO.findAll();
    }

    public Optional<Producto> buscarPorId(Integer id) {
        return productoDAO.findById(id);
    }
}