/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.repository;

import fr.utbm.projet.lo54.entity.Administrateur;
import fr.utbm.projetlo54.util.HibernatUtil;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class AdministrateurDao {
    
    public static  final Configuration cfg = new Configuration();
    public void InsertAdmin(String admNom, String admPrenom, String admSexe){
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        
        try{
            try(Session session = HibernatUtil.getSessionFactory().openSession()){
                Transaction tx = session.beginTransaction();
                String hql = "INSERT INTO administrateur( ADM_NOM, ADM_PRENOM, ADM_SEXE)  VALUES (?,?,?)";
                Administrateur adm = new Administrateur();
                adm.setAdmNom(admNom);
                adm.setAdmPrenom(admPrenom);
                adm.setAdmSexe(admSexe);
                session.createSQLQuery(hql)
                        .setParameter(1, adm.getAdmNom())
                        .setParameter(2, adm.getAdmPrenom())
                        .setParameter(3, adm.getAdmSexe())
                        .executeUpdate();
                tx.commit();
            }
        }catch(HibernateException e){
            System.err.println(e.getMessage());
        }           
    }
    
    //Read all data Administrateur in database
    public List<Administrateur> ListAdmin(){
        List<Administrateur> listAdmin; 
        listAdmin = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try{
            try(Session session = HibernatUtil.getSessionFactory().openSession()){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from fr.utbm.projet.lo54.entity.Administrateur");
                listAdmin = query.list();
                session.close();
            }
        }catch(HibernateException e){
            System.err.println(e.getMessage());
        }
        return listAdmin;
    }
    
    
    //Read unique data Administrateur in database
    public List<Administrateur> ListUniqueAdmin(Integer id){
        List<Administrateur> listAdmin; 
        listAdmin = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try{
            try(Session session = HibernatUtil.getSessionFactory().openSession()){
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("from fr.utbm.projet.lo54.entity.Administrateur where ADM_NUMERO=:id")
                        .setParameter("id", id);
                listAdmin = query.list();
                session.close();
            }
        }catch(HibernateException e){
            System.err.println(e.getMessage());
        }
        return listAdmin;
    }
    
    
    //Update data Administrateur in database
    public void UpdateAdmin(Integer admNumero, String admNom, String admPrenom, String admSexe){
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        long id = admNumero;
        try{
            try(Session session = HibernatUtil.getSessionFactory().openSession()){
                Transaction tx = session.beginTransaction();
                String query = "UPDATE Administrateur a set a.admNom = '"+admNom+"', a.admPrenom = '"+admPrenom+"', a.admSexe='"+admSexe+"' WHERE a.admNumero = :id";
                session.createQuery(query)
                        .setParameter("id", id)
                        .executeUpdate();
                tx.commit();
                session.close();
            }        
        }catch(HibernateException e){
            System.err.println(e.getMessage());
        }
    }
    
    
    //Delete Administrateur in database
    public void DeleteAdmin(Integer admNumero){
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        long id = admNumero;
        try{
            try(Session session = HibernatUtil.getSessionFactory().openSession()){
                Transaction tx = session.beginTransaction();
                String query = "DELETE FROM Administrateur a WHERE a.admNumero= :id";
                session.createQuery(query)
                        .setParameter("id", id)
                        .executeUpdate();
                tx.commit();
                session.close();
            }
        }catch(HibernateException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        AdministrateurDao admao = new AdministrateurDao();
         Administrateur adm = new Administrateur("tttt"," hhh","m");
         admao.InsertAdmin(adm.getAdmNom(), adm.getAdmPrenom(), adm.getAdmSexe());
        System.out.println("fr.utbm.projet.lo54.repository.AdministrateurDao.main()");
    }
}