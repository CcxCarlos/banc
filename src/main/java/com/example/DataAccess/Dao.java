package com.example.DataAccess;

import com.example.Model.Clients;
import com.example.Model.Comptes;
import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class Dao {
    private static Session session;

    static {
        newSession();
    }
    public Query executeQuery(String query, Class<?> clase) {
        isSessionOpen();
        return session.createQuery(query, clase);
    }

    public Query executeQuery(String query, String parameter, String parameterValue, Class<?> clase) {
        isSessionOpen();
        Query q = session.createQuery(query, clase);
        q.setParameter(parameter, parameterValue);
        return q;
    }
    public List<Object> getValues(Query q) {
        return q.getResultList();
    }
    protected  void persistObject(Object o) {
        isSessionOpen();
        try {
            session.persist(o);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }

    }

    private  void isSessionOpen() {
        if(!session.isOpen()) newSession();
    }

    protected  void deleteObject(Object o){
        isSessionOpen();
        session.remove(o);
        session.getTransaction().commit();
    }
    public  void addObject(Object o) {
        persistObject(o);
    }
    public  void removeObject(Object o) {
        deleteObject(o);
    }
    public  Boolean objectExists(Object o){
        if (o instanceof Clients) return !getValues(executeQuery(Querys.CLIENTS_BY_ID_FISCAL,
                Querys.getParameter(Querys.CLIENTS_BY_ID_FISCAL), String.valueOf(((Clients) o).getIdFiscal()), Clients.class)).isEmpty();
        else return !getValues(executeQuery(Querys.CUENTA, Querys.getParameter(Querys.CUENTA), ((Comptes)o).getnCuenta(), Comptes.class)).isEmpty();
    }
    protected  void refreshSession(Class<?> clase){
        isSessionOpen();
        session.refresh(clase);
    }
    public  void closeSession(){
        session.close();
    }


    private static void newSession(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }
    public List<Clients> getClientsbyByMultipleFilters(String[][] data){
        isSessionOpen();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Clients> cq = cb.createQuery(Clients.class);
        Root<Clients> root = cq.from(Clients.class);
        int size = data[1].length;
        Predicate[] predicates = new Predicate[size];
        for (int i = 0; i < size; i++) {
            if (data[0][i].equals("nCuenta")) {
                Join<Clients, Comptes> comptesJoin = root.join("comptes");
                predicates[i] = cb.equal(comptesJoin.get(data[0][i]), data[1][i]);
            } else {
                predicates[i] = cb.like(root.get(data[0][i]), data[1][i]);
            }
        }
        cq.select(root).where(cb.and(predicates));
        return session.createQuery(cq).getResultList();
    }

}
