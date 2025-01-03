package org.example.hibernateUt3.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.hibernateUt3.config.HibernateUtil;
import org.example.hibernateUt3.model.Entertainer;
import org.example.hibernateUt3.model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class EntertainerDao {

    public Entertainer create(Entertainer entertainer) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession();
        try {
            tx = session.beginTransaction();

            session.persist(entertainer);
            tx.commit();
            return entertainer;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Entertainer entertainer){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(entertainer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Entertainer entertainer){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(entertainer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Entertainer getById(Long entertainerId){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Entertainer entertainer=session.find(Entertainer.class,entertainerId);

            tx.commit();
            return entertainer;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Entertainer getByNationalId(String nationalId){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Entertainer> entertainerTypedQuery = session.createQuery(
                    "FROM Entertainer a where a.nationalId = :nationalId", Entertainer.class
            );
            entertainerTypedQuery.setParameter("nationalId", nationalId);

            return entertainerTypedQuery.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Entertainer> getAll(){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Entertainer> entertainerTypedQuery = session.createQuery(
                    "FROM Entertainer", Entertainer.class
            );
            return entertainerTypedQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

