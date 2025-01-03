package org.example.hibernateUt3.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.hibernateUt3.config.HibernateUtil;
import org.example.hibernateUt3.model.client.Client;
import org.example.hibernateUt3.model.client.CorporateClient;
import org.example.hibernateUt3.model.client.PrivateClient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {

    public Client create(Client client) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.persist(client);
            tx.commit();
            return client;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void update(Client client){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.merge(client);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Client client){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            session.remove(client);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Client getById(Long clientId){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Client client=session.find(Client.class,clientId);

            tx.commit();
            return client;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public PrivateClient getByNationalId(String nationalId){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<PrivateClient> privateClientTypedQuery = session.createQuery(
                    "FROM PrivateClient p where p.nationalId = :nationalId", PrivateClient.class
            );
            privateClientTypedQuery.setParameter("nationalId", nationalId);

            try {
                return privateClientTypedQuery.getSingleResult();
            }catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CorporateClient getByVAT(String VAT){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<CorporateClient> corporateClientTypedQuery = session.createQuery(
                    "FROM CorporateClient e where e.VAT = :VAT", CorporateClient.class
            );
            corporateClientTypedQuery.setParameter("VAT", VAT);

            try {
                return corporateClientTypedQuery.getSingleResult();
            }catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAll(){
        try (Session session = HibernateUtil.getSession()) {

            TypedQuery<Client> clientTypedQuery = session.createQuery(
                    "FROM Client ", Client.class
            );

            try {
                return clientTypedQuery.getResultList();
            }catch (NoResultException e) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
