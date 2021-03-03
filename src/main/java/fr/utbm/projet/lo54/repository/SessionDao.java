/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.formationlo54.model.Mysession;
import fr.utbm.projet.lo54.entity.Administrateur;
import fr.utbm.projet.lo54.entity.Localisation;

import fr.utbm.projetlo54.util.HibernatUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author daris
 */
public class SessionDao {

    public static final Configuration cfg = new Configuration();

   
    public List<String> g() {
        List<String> listville = new ArrayList<>();
        int i = 0;
        List<Localisation> f = null;
        for (Object a : getSessionVille()) {

            f = convertObjectToList(a);

            listville.add(f.get(i) + " " + f.get(i + 1) + " " + f.get(i + 2));

        }
        return listville;
    }

    public static List<Localisation> convertObjectToList(Object obj) {

        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return (List<Localisation>) list;
    }

    public static List<Localisation> getSessionVille() {
        List<Localisation> listSession;
        listSession = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("SELECT "
                        + "    l.locCity,"
                        + "    m.sesDateDebut,"
                        + "    m.sesDateFin "
                        + "    From Localisation l INNER JOIN Mysession m"
                        + " 	ON l.locIentifiant = m.locIentifiant");
                listSession = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return listSession;
    }

    public void InsertSession(String sesDateDebut, String sesDateFin, Integer locIdentifiant) {
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        long locId = locIdentifiant;
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateDebut = simpleDateFormat.format(new Date(sesDateDebut));
        String dateFin = simpleDateFormat.format(new Date(sesDateFin));
        Date dateDebutSession = new Date(dateDebut);
        Date dateFinSession = new Date(dateFin);
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                String hql = "INSERT INTO mysession(SES_DATE_DEBUT, SES_DATE_FIN, LOC_IENTIFIANT) VALUES (?,?,?)";
                Mysession ses = new Mysession();
                ses.setSesDateDebut(dateDebutSession);
                ses.setSesDateFin(dateFinSession);
                ses.setLocIentifiant(locId);
                session.createSQLQuery(hql)
                        .setParameter(1, ses.getSesDateDebut())
                        .setParameter(2, ses.getSesDateFin())
                        .setParameter(3, ses.getLocIentifiant())
                        .executeUpdate();
                tx.commit();
            }
        } catch (HibernateException e) {

        }
    }

    public List<Mysession> ListSession() {
        List<Mysession> listSession;
        listSession = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("FROM fr.utbm.formationlo54.model.Mysession");
                listSession = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return listSession;
    }

    public List<Mysession> ListUniqueSession(int id) {
        List<Mysession> listSession;
        listSession = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from fr.utbm.projet.lo54.entity.Mysession where SES_NUMERO=:id")
                        .setParameter("ud", id);
                listSession = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return listSession;
    }

    public void UpdateSession(Integer sesNumero, Integer locIdentifiant, String sesDateDebut, String sesDateFin) {
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        long id = sesNumero;
        long locId = locIdentifiant;
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateDebut = simpleDateFormat.format(new Date(sesDateDebut));
        String dateFin = simpleDateFormat.format(new Date(sesDateFin));
        Date dateDebutSession = new Date(dateDebut);
        Date dateFinSession = new Date(dateFin);
        Mysession ses = new Mysession();
        ses.setSesDateDebut(dateDebutSession);
        ses.setSesDateFin(dateFinSession);
        ses.setLocIentifiant(locId);
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                String query = "UPDATE fr.utbm.formationlo54.model.Mysession  set SES_DATE_DEBUT = :dateDebut, SES_DATE_FIN = :dateFin, LOC_IENTIFIANT= :locId WHERE SES_NUMERO = :id";
                session.createQuery(query)
                        .setParameter("dateDebut", ses.getSesDateDebut())
                        .setParameter("dateFin", ses.getSesDateFin())
                        .setParameter("locId", ses.getLocIentifiant())
                        .setParameter("id", id)
                        .executeUpdate();
                tx.commit();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    public void DeleteSession(Integer sesNumero) {
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        long id = sesNumero;
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                String query = "DELETE FROM fr.utbm.formationlo54.model.Mysession s WHERE s.sesNumero= :id";
                session.createQuery(query)
                        .setParameter("id", id)
                        .executeUpdate();
                tx.commit();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }
}
