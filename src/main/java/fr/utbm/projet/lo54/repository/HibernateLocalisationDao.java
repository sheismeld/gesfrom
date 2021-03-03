/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.projetlo54.util.HibernatUtil;
import fr.utbm.projet.lo54.entity.Localisation;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

/**
 *
 * @author Zome
 */
public class HibernateLocalisationDao {

    private static final Configuration cfg = new Configuration();

    public static void InsertLocation(String locCity) {
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "insert into localisation(LOC_CITY)  values (?) ";
                Localisation f = new Localisation();

                f.setLocCity(locCity);

                session.createSQLQuery(sqlInsert)
                        .setParameter(1, f.getLocCity())
                        .executeUpdate();

                t.commit();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

    public boolean testUpdate;

    public static void updateLocation(int id, String locCity) {
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "UPDATE  localisation SET LOC_CITY=:city WHERE LOC_IENTIFIANT=" + id;
                Localisation f = new Localisation();
                f.setLocCity(locCity);

                session.createSQLQuery(sqlInsert)
                        .setParameter("city", f.getLocCity())
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

    }

    public static List<Localisation> listeLocation() {
        List<Localisation> listeLocations;
        listeLocations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                String hql = "FROM fr.utbm.projet.lo54.entity.Localisation ";
                Query query = session.createQuery(hql);
                listeLocations = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeLocations;
    }

    public static List<Localisation> uniqueLocation(int id) {
        List<Localisation> listeLocations;
        listeLocations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM fr.utbm.projet.lo54.entity.Localisation where LOC_IENTIFIANT=" + id);
                listeLocations = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeLocations;
    }

    public static void deleteLocation(int id) {

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("DELETE FROM fr.utbm.projet.lo54.entity.Localisation where LOC_IENTIFIANT=" + id);

                session.close();
                System.out.println("surpression reussie...");

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

    public static Long villeLocalisation(String ville) {
        String listeLocation;
        Long idLocalisation = null;
        //listeLocation = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("SELECT locIentifiant FROM Localisation where locCity=:city");
                query.setParameter("city", ville);
                listeLocation = query.getSingleResult().toString();
                idLocalisation = Long.parseLong(listeLocation);
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return idLocalisation;
    }

    public List<Localisation> getVilleLocalisation() {
        List<Localisation> villeloc;
        villeloc = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("SELECT locCity FROM Localisation");
                villeloc = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return villeloc;
    }

}
