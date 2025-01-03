package org.example.hibernateUt3.config;

import org.example.hibernateUt3.model.*;
import org.example.hibernateUt3.model.client.Client;
import org.example.hibernateUt3.model.client.CorporateClient;
import org.example.hibernateUt3.model.client.PrivateClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Session session;

    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/practica_ad_ut3");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(Event.class);
        configuration.addAnnotatedClass(Host.class);
        configuration.addAnnotatedClass(Entertainer.class);
        configuration.addAnnotatedClass(Costume.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(CorporateClient.class);
        configuration.addAnnotatedClass(PrivateClient.class);
        configuration.addAnnotatedClass(Hiring.class);

        return configuration.buildSessionFactory();
    }

    public static Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static void close() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
