package org.example.hibernateUt3.dao;

import jakarta.persistence.TypedQuery;
import org.example.hibernateUt3.config.HibernateUtil;
import org.example.hibernateUt3.model.Hiring;
import org.example.hibernateUt3.model.client.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiringDao {

    public Hiring create(Hiring hiring) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.persist(hiring);

            tx.commit();
            return hiring;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Hiring update(Hiring hiring){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(hiring);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return hiring;
    }

    public void delete(Hiring hiring){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(hiring);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Hiring getById(Long hiringId){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Hiring hiring=session.find(Hiring.class,hiringId);

            tx.commit();
            return hiring;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public List<Client> getByProvince(String Province){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {

            ClientDao clientDao=new ClientDao();

            TypedQuery<Client> hiringTypedQuery = session.createQuery("SELECT h.client FROM Hiring h WHERE h.eventCity = :eventCity", Client.class);
            hiringTypedQuery.setParameter("eventCity", Province);
            return hiringTypedQuery.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
