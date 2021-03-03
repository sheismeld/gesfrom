/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.projetlo54.util.HibernatUtil;
import fr.utbm.projet.lo54.entity.Formation;
import fr.utbm.projet.lo54.entity.Promotion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author Yves
 */
public class HibernateFormationDao {

    private static final Configuration cfg = new Configuration();

    public static List<Formation> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return (List<Formation>) list;
    }

    @Override
    public String toString() {
        return "HibernateFormationDao{" + "a=" + 1 + ", " + testUpdateUser + '}';
    }

    public void InsertFormation(int admNumero, String frtLibelle, String frtDescription, int ptrcode, String frtDateDebut, String frtDateFin) {
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String codeN = ptrcode + "";
        BigInteger code = new BigInteger(codeN.trim());
        long admNum = admNumero;

        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "INSERT INTO formation( ADM_NUMERO, PRT_CODE, FRT_LIBELLE, FRT_DESCRIPTION, FRT_DATE_DEBUT, FRT_DATE_FIN)"
                        + "                         VALUES (?,?,?,?,?,?) ";
                Formation f = new Formation();
                f.setAdmNumero(admNum);
                f.setPrtCode(code);
                f.setFrtLibelle(frtLibelle);
                f.setFrtDescription(frtDescription);
                f.setFrtDateDebut(new Date(frtDateDebut));
                f.setFrtDateFin(new Date(frtDateFin));

                session.createSQLQuery(sqlInsert)
                        .setParameter(1, f.getAdmNumero())
                        .setParameter(2, f.getPrtCode())
                        .setParameter(3, f.getFrtLibelle())
                        .setParameter(4, f.getFrtDescription())
                        .setParameter(5, f.getFrtDateDebut())
                        .setParameter(6, f.getFrtDateFin())
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

    public boolean testUpdateUser;

    public void updateFormation(int id, int admNumero, String frtLibelle, String frtDescription, int ptrcode, Date frtDateDebut, Date frtDateFin) {

        String codeN = ptrcode + "";
        BigInteger code = new BigInteger(codeN.trim());
        long admNum = admNumero;

        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();

                String sqlInsert = "UPDATE  formation SET ADM_NUMERO=:numAdm ,PRT_CODE=:code,"
                        + "FRT_LIBELLE=:lib,FRT_DESCRIPTION=:desc,"
                        + "FRT_DATE_DEBUT=:dateD, FRT_DATE_FIN=:dateF "
                        + " WHERE FRT_NUMERO=" + id;
                Formation f = new Formation();
                f.setAdmNumero(admNum);
                f.setPrtCode(code);
                f.setFrtLibelle(frtLibelle);
                f.setFrtDescription(frtDescription);
                f.setFrtDateDebut(frtDateDebut);
                f.setFrtDateFin(frtDateFin);

                session.createSQLQuery(sqlInsert)
                        .setParameter("numAdm", f.getAdmNumero())
                        .setParameter("code", f.getPrtCode())
                        .setParameter("lib", f.getFrtLibelle())
                        .setParameter("desc", f.getFrtDescription())
                        .setParameter("dateD", f.getFrtDateDebut())
                        .setParameter("dateF", f.getFrtDateFin())
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

    }

    public List<Formation> listeFormation() {
        List<Formation> listeFormations;
        listeFormations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                String hql = "FROM fr.utbm.projet.lo54.entity.Formation ";
                Query query = session.createQuery(hql);
                listeFormations = query.list();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeFormations;
    }

    public List<Formation> uniqueFormation(int id) {
        List<Formation> listeFormations;
        listeFormations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM fr.utbm.projet.lo54.entity.Formation where frtNumero=" + id);
                listeFormations = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeFormations;
    }

    public static void deleteFormation(int id) {

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery(" DELETE FROM fr.utbm.projet.lo54.entity.Formation where frtNumero=" + id);

                query.executeUpdate();
                session.close();
                System.out.println("surpression reussie...");

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

    public List<Promotion> listePromotion() {
        List<Promotion> listePromotions;
        listePromotions = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                String hql = "FROM fr.utbm.projet.lo54.entity.Promotion ";
                Query query = session.createQuery(hql);
                listePromotions = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listePromotions;
    }

    public static List<Formation> RechercheFormation(String mot) {
        List<Formation> listeFormations;

        listeFormations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM Formation f "
                        + " INNER JOIN Subdiviser s ON s.id.frtNumero = f.frtNumero"
                        + " INNER JOIN  Mysession m ON m.sesNumero = s.id.sesNumero"
                        + "  where f.frtLibelle LIKE'%" + mot + "%'"
                        + "  OR m.sesDateDebut LIKE'%" + mot + "%'"
                        + "ORDER BY f.frtLibelle  ");
                listeFormations = query.list();

                session.close();
            }

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeFormations;
    }

    public static List<Formation> formationProgramer() {
        List<Formation> listeFormations;

        listeFormations = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM Formation f "
                        + " INNER JOIN Subdiviser s ON s.id.frtNumero = f.frtNumero"
                        + " INNER JOIN  Mysession m ON m.sesNumero = s.id.sesNumero");
                listeFormations = query.list();

                session.close();
            }

        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeFormations;
    }

    public List<Formation> getformationProgramer() {
        List<Formation> ff = new ArrayList<>();
        for (Object l : formationProgramer()) {
            int i = 0;
            List<Formation> f = convertObjectToList(l);
            ff.add(f.get(i));
            i += 3;

        }
        return ff;
    }

    public List<Formation> getRechercheFormationstout(String mot) {
        List<Formation> ff = new ArrayList<>();
        for (Object l : RechercheFormation(mot)) {
            int i = 0;
            List<Formation> f = convertObjectToList(l);
            ff.add(f.get(i));
            i += 3;

        }
        return ff;
    }

    public void InscrireUtilisateur(int id_forma, int id_util, int prix) {
        String p = prix + "";
        BigDecimal Bp = new BigDecimal(p);

        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "INSERT INTO inscription(FRT_NUMERO,UTI_NUMERO,NBR_FORMA)"
                        + " VALUES (?,?,?)";
                session.createSQLQuery(sqlInsert)
                        .setParameter(1, id_forma)
                        .setParameter(2, id_util)
                        .setParameter(3, 1)
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Long frtNum(String libFormation) {
        String listeLocation;
        Long idfrt = null;
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                org.hibernate.Query query = session.createQuery("SELECT frtNumero FROM Formation where frtLibelle=:lib");
                query.setParameter("lib", libFormation);
                listeLocation = query.getSingleResult().toString();
                idfrt = Long.parseLong(listeLocation);
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return idfrt;
    }

    public static Long utilNum(String nom) {
        String listeLocation;
        Long idutil = null;
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                org.hibernate.Query query = session.createQuery("SELECT utiNumero FROM Utilisateur where utiNom=:nom");
                query.setParameter("nom", nom);
                listeLocation = query.getSingleResult().toString();
                idutil = Long.parseLong(listeLocation);
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return idutil;
    }

}
