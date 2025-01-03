package org.example.hibernateUt3.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.hibernateUt3.config.HibernateUtil;
import org.example.hibernateUt3.model.Costume;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CostumeDao {

    public Costume create(Costume costume) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.persist(costume);

            tx.commit();
            return costume;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void update(Costume costume){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(costume);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Costume costume){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(costume);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Costume getById(Long costumeId){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Costume costume=session.get(Costume.class,costumeId);

            tx.commit();
            return costume;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Costume getByCharacter(String character) {
        try (Session session = HibernateUtil.getSession()) {
            TypedQuery<Costume> query = session.createQuery(
                    "FROM Costume c WHERE c.character = :character", Costume.class
            );
            query.setParameter("character", character);

            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Costume> getTwoMostExpesiveCostumes() {
        try (Session session = HibernateUtil.getSession()) {
            TypedQuery<Costume> query = session.createQuery(
                    "FROM Costume c ORDER BY c.price DESC", Costume.class
            );

            try {
                return query.getResultList();
            } catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
