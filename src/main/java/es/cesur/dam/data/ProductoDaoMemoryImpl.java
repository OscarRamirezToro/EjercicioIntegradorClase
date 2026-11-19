package es.cesur.dam.data;

import es.cesur.dam.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductoDaoMemoryImpl implements DAO<Producto, Integer> {

    private static final Logger logger = Logger.getLogger(ProductoDaoMemoryImpl.class.getName());
    private final List<Producto> datos = new ArrayList<>();

    public ProductoDaoMemoryImpl() {
        datos.add(new Producto(1, "Portátil", 899.99, 10));
        datos.add(new Producto(2, "Ratón inalámbrico", 25.50, 35));
        datos.add(new Producto(3, "Teclado mecánico", 79.90, 15));
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return datos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public List<Producto> findAll() {
        logger.info("Recuperando todos los registros desde memoria");
        return new ArrayList<>(datos);
    }

    @Override
    public Producto save(Producto entity) {
        datos.add(entity);
        return entity;
    }

    @Override
    public void update(Producto entity) {
        findById(entity.getId()).ifPresent(existente -> {
            existente.setNombre(entity.getNombre());
            existente.setPrecio(entity.getPrecio());
            existente.setStock(entity.getStock());
        });
    }

    @Override
    public void delete(Integer id) {
        datos.removeIf(p -> p.getId().equals(id));
    }
}