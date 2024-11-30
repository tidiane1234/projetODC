package com.djessoretech.database;


import com.djessoretech.model.Personne;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connect {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .addAnnotatedClass(Personne.class)
                        .buildSessionFactory();

                System.out.println("Session crée avec success");
                return sessionFactory;
            }catch (Exception e){
                System.out.println("Impossible de creer une session");
                throw new RuntimeException(e.getMessage());
            }
        }

        return sessionFactory;
    }

    public void Close(){
        if (sessionFactory != null){
            sessionFactory.close();
        }
    }
}
