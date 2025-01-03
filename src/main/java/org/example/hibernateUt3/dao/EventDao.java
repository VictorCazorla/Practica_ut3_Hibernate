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

public class EventDao {

    public Event create(Event event) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.persist(event);

            tx.commit();
            return event;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void update(Event event){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(event);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Event event){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(event);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Event getById(Long eventId){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Event event=session.find(Event.class,eventId);

            tx.commit();
            return event;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Event getByName(String name){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Event> eventTypedQuery = session.createQuery(
                    "FROM Event e where e.name = :name", Event.class
            );
            eventTypedQuery.setParameter("name", name);

            try {
                return eventTypedQuery.getSingleResult();
            }catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> getAll(){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Event> eventTypedQuery = session.createQuery(
                    "FROM Event e", Event.class
            );

            try {
                return eventTypedQuery.getResultList();
            }catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Entertainer> getEventEntertainers(String eventName){
        try {
            Event event=getByName(eventName);
            return event.getEventEntertainers();
        }catch (NoResultException e) {
            return null;
        }
    }
}
