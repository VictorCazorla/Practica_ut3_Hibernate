package org.example.hibernateUt3.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.hibernateUt3.config.HibernateUtil;
import org.example.hibernateUt3.model.Event;
import org.example.hibernateUt3.model.Host;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class HostDao {

    public Host create(Host host) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.persist(host);

            tx.commit();
            return host;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void update(Host host){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(host);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Host host){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(host);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Host getById(Long hostId){
        try (Session session = HibernateUtil.getSession()) {
            try {
                return session.find(Host.class, hostId);
            } catch (NoResultException e){
                return null;
            }
        }
    }

    public Host getByNationalId(String nationalId){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Host> hostTypedQuery = session.createQuery(
                    "FROM Host p where p.nationalId = :nationalId", Host.class
            );

            hostTypedQuery.setParameter("nationalId", nationalId);
            try{
                return hostTypedQuery.getSingleResult();
            }catch (NoResultException e){
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        public List<Host> getAll(){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Host> hostTypedQuery = session.createQuery(
                    "FROM Host ", Host.class
            );

            try{
                return hostTypedQuery.getResultList();
            }catch (NoResultException e){
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Event> getHostEventsById(String enterNationalId){
        try {
            Host host=getByNationalId(enterNationalId);
            return host.getHostEvents();
        }catch (NoResultException e) {
            return null;
        }
    }
}
