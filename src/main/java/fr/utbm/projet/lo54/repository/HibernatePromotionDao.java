/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.projetlo54.util.HibernatUtil;
import fr.utbm.projet.lo54.entity.Promotion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author Zome
 */
public class HibernatePromotionDao {

    private static final Configuration cfg = new Configuration();

    public boolean testUpdate;

    public static void updatePromotion(int id, Double prt) {
        cfg.configure("hibernate.cfg.xml");
        BigDecimal pourcentage = new BigDecimal(prt);
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "UPDATE  promotion SET PRT_PROUCENTAGE_REDUCTION =:pourcentage WHERE PRT_CODE=" + id;
                Promotion f = new Promotion();
                f.setPrtProucentageReduction(pourcentage);

                session.createSQLQuery(sqlInsert)
                        .setParameter("pourcentage", f.getPrtProucentageReduction())
                        .executeUpdate();

                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

    }

    public static List<Promotion> listePromotion() {
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

    public static List<Promotion> uniquePromotion(int id) {
        List<Promotion> listePromotions;
        listePromotions = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM fr.utbm.projet.lo54.entity.Promotion where PRT_CODE=" + id);
                listePromotions = query.list();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listePromotions;
    }

    public static void deletePromotion(int id) {

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("DELETE FROM fr.utbm.projet.lo54.entity.Promotion where PRT_CODE=" + id);

                session.close();
                System.out.println("surpression reussie...");

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

}
