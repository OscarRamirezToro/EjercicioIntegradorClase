package es.cesur.dam.data;

import es.cesur.dam.model.Producto;
import es.cesur.dam.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDaoHibernateImpl implements DAO<Producto, Integer> {

    private static final Logger logger = Logger.getLogger(ProductoDaoHibernateImpl.class.getName());

    @Override
    public Optional<Producto> findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Producto p = session.get(Producto.class, id);
            return Optional.ofNullable(p);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar por id", e);
            throw e;
        }
    }

    @Override
    public List<Producto> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            logger.info("Consultando registros desde MySQL con Hibernate");
            return session.createQuery("from Producto", Producto.class).list();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al listar registros con Hibernate", e);
            throw e;
        }
    }

    @Override
    public Producto save(Producto entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Error al guardar registro", e);
            throw e;
        }
    }

    @Override
    public void update(Producto entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Error al actualizar registro", e);
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Producto p = session.get(Producto.class, id);
            if (p != null) {
                session.remove(p);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Error al eliminar registro", e);
            throw e;
        }
    }
}