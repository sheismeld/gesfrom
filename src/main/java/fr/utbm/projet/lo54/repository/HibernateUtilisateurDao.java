/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.projetlo54.util.HibernatUtil;
import fr.utbm.projet.lo54.entity.Utilisateur;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author Zome
 */
public class HibernateUtilisateurDao {

    private static final Configuration cfg = new Configuration();

    public  void insertUser(String nom,String Prenom,String dateN,int numRue,String nomRue,String ville,String codeP,String email,String pwd) {
             String pattern = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String d = simpleDateFormat.format(new Date(dateN));
                String a = d.replaceAll("-", "/");
  
                Date dateNaissance = new Date(a);
                String  numeRue = numRue+"";
                BigInteger numeroRue = new BigInteger(numeRue.trim());
                
        
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "INSERT INTO utilisateur( UTI_NOM,UTI_PRENOM,UTI_DATE_NAISSANCE,UTI_NUMERO_RUE,UTI_NOM_RUE,UTI_VILLE,UTI_CODE_POSTALE, UTI_ADRESSE_EMAIL, UTI_PASSWORD)"
                        + "                         VALUES (?,?,?,?,?,?,?,?,?) ";
                Utilisateur f = new Utilisateur();
                f.setUtiNom(nom);
                f.setUtiPrenom(Prenom);
                f.setUtiDateNaissance(dateNaissance);
                f.setUtiNumeroRue(numeroRue);
                f.setUtiNomRue(nomRue);
                f.setUtiVille(ville);
                f.setUtiCodePostale(codeP);
                f.setUtiAdresseEmail(email);
                f.setUtiPassword(pwd);
                
                session.createSQLQuery(sqlInsert)
                        .setParameter(1, f.getUtiNom())
                        .setParameter(2, f.getUtiPrenom())
                        .setParameter(3, f.getUtiDateNaissance())
                        .setParameter(4, f.getUtiNumeroRue())
                        .setParameter(5, f.getUtiNomRue())
                        .setParameter(6, f.getUtiVille())
                        .setParameter(7, f.getUtiCodePostale())
                        .setParameter(8, f.getUtiAdresseEmail())
                        .setParameter(9, f.getUtiPassword())   
                .executeUpdate();
                t.commit();
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

    public boolean testUpdateUser;

    public  void updateUser(int id,String nom,String Prenom,String dateNaiss,int numRue,String nomRue,String ville,String codeP,String email,String pwd) {
        String pattern = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date(dateNaiss));
  
                Date dateNaissance = new Date(date);
                String  numeRue = numRue+"";
                BigInteger numeroRue = new BigInteger(numeRue.trim());
                long idp=id;
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "UPDATE  utilisateur SET UTI_NOM=:nom ,UTI_PRENOM=:prenom,"
                                               + "UTI_DATE_NAISSANCE=:dateNaiss,UTI_NUMERO_RUE=:numeroRue,"
                                               + "UTI_NOM_RUE=:nomRue,UTI_VILLE=:ville,"
                                               + "UTI_CODE_POSTALE=:codeP, UTI_ADRESSE_EMAIL=:email,"
                                               + " UTI_PASSWORD =:pwd WHERE UTI_NUMERO=" + idp;
                Utilisateur f = new Utilisateur();
                f.setUtiNumero(idp);
                f.setUtiNom(nom);
                f.setUtiPrenom(Prenom);
                f.setUtiDateNaissance(dateNaissance);
                f.setUtiNumeroRue(numeroRue);
                f.setUtiNomRue(nomRue);
                f.setUtiVille(ville);
                f.setUtiCodePostale(codeP);
                f.setUtiAdresseEmail(email);
                f.setUtiPassword(pwd);
                
                session.createSQLQuery(sqlInsert)
                        .setParameter("nom", f.getUtiNom())
                        .setParameter("prenom", f.getUtiPrenom())
                        .setParameter("dateNaiss", f.getUtiDateNaissance())
                        .setParameter("numeroRue", f.getUtiNumeroRue())
                        .setParameter("nomRue", f.getUtiNomRue())
                        .setParameter("ville", f.getUtiVille())
                        .setParameter("codeP", f.getUtiCodePostale())
                        .setParameter("email", f.getUtiAdresseEmail())
                        .setParameter("pwd", f.getUtiPassword())   
                .executeUpdate();
                t.commit();
                
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());

        }

    }

    public  List<Utilisateur> userList() {
        List<Utilisateur> listeUser;
        listeUser = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                String hql = "FROM fr.utbm.projet.lo54.entity.Utilisateur";
                Query query = session.createQuery(hql);
                listeUser = query.list();
                session.close();
                }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeUser;
    }

    public  List<Utilisateur> uniqueUser(int id) {
        List<Utilisateur> user;
        user = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
               // Transaction t = session.beginTransaction();
                Query query = session.createQuery("FROM fr.utbm.projet.lo54.entity.Utilisateur WHERE UTI_NUMERO=" + id);
                user = query.list();
                 session.close();
                  }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return user;
    }

    public  void deleteUser(int id) {

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                Query query = session.createQuery("DELETE FROM fr.utbm.projet.lo54.entity.Utilisateur WHERE UTI_NUMERO=" + id);
                        query.executeUpdate();
                t.commit();
                session.close();
                System.out.println("surpression reussie...");

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

    }

}
