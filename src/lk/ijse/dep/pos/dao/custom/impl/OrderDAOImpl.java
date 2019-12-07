package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.OrderDAO;
import lk.ijse.dep.pos.entity.Order;
import org.hibernate.Session;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public Session session;


    @Override
    public void setSession(Session session) {
        this.session= session;
    }

    @Override
    public int getLastOrderId() throws Exception {

        Integer i = (Integer) session.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1")
                .uniqueResult();
        return (i == null) ? 0 : i;
    }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        return  session.createNativeQuery("SELECT * FROM `Order` WHERE customer_id=?1").setParameter(1,customerId).uniqueResult() != null;
    }

    @Override
    public List<Order> findAll() throws Exception {

        return  session.createQuery("From Order",Order.class).list();

    }

    @Override
    public Order find(Integer orderId) throws Exception {
        return session.get(Order.class,orderId);

    }

    @Override
    public void save(Order order) throws Exception {
        session.persist(order);
    }

    @Override
    public void update(Order order) throws Exception {
        session.persist(order);
    }

    @Override
    public void delete(Integer orderId) throws Exception {
        session.delete(session.load(Order.class,orderId));
    }
}
