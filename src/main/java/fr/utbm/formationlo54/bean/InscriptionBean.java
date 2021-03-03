/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.formation.lo54.UtilisateurInscrit;
import static fr.utbm.formationlo54.bean.FormationBean.formaList;
import fr.utbm.projet.lo54.entity.Formation;
import fr.utbm.projet.lo54.entity.Utilisateur;
import static fr.utbm.projet.lo54.repository.AdministrateurDao.cfg;
import fr.utbm.projet.lo54.repository.HibernateFormationDao;
import fr.utbm.projet.lo54.repository.HibernateUtilisateurDao;
import fr.utbm.projetlo54.util.HibernatUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.faces.view.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Zome
 */
@Named(value = "inscriptionBean")
@ViewScoped
public class InscriptionBean implements Serializable {

    /**
     * Creates a new instance of InscriptionBean
     */
    private int frtNumero;
    private int utiNumero;
    private int montant;

    private String libelle, nom;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getFrtNumero() {
        return this.frtNumero;
    }

    public void setFrtNumero(int frtNumero) {
        this.frtNumero = frtNumero;
    }

    public int getUtiNumero() {
        return this.utiNumero;
    }

    public void setUtiNumero(int utiNumero) {
        this.utiNumero = utiNumero;
    }

    public  String returnAction() {
     
        this.frtNumero = Integer.parseInt(frtNum(libelle) + "");
        this.utiNumero = Integer.parseInt(utilNum(nom) + "");
        if (this.frtNumero == 0 || this.utiNumero == 0) {
            return utiNumero == 0 ? "success" : "failure";
        } else {
            InscrireUtilisateur(this.frtNumero, this.utiNumero, this.montant);
             
            return utiNumero != 0 ? "success" : "failure";
        }
        
    }
    
    

    public static Long frtNum(String libFormation) {
        String listeLocation;
        Long idfrt = null;
        //listeLocation = new ArrayList<>();
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
        //listeLocation = new ArrayList<>();
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

    private List<Utilisateur> userList = null;

    public List<Utilisateur> getUserList() {
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
        userList = userDAO.userList();
        return userList;
    }

    public List<Formation> getFormationList() {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaList = formaDAO.listeFormation();
        return formaList;
    }

    public void InscrireUtilisateur(int a, int b, int c) {

        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "INSERT INTO inscription(FRT_NUMERO,UTI_NUMERO,NBR_FORMA)"
                        + " VALUES (?,?,?)";

                session.createSQLQuery(sqlInsert)
                        .setParameter(1, a)
                        .setParameter(2, b)
                        .setParameter(3, c)
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Object> inscriptionList() {

        List<Object> listeUser;
        listeUser = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String hql = "SELECT u.utiNom,u.utiPrenom,u.utiAdresseEmail,f.frtLibelle "
                        + "FROM Utilisateur u "
                        + "INNER JOIN Inscription i ON u.utiNumero=i.id.utiNumero "
                        + "INNER JOIN Formation f on i.id.frtNumero = f.frtNumero";
                Query query = session.createQuery(hql);
                listeUser = query.list();
                t.commit();
                session.close();

                // System.out.println("Enregistrement effectuer avec succes...");
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return listeUser;
    }

    public static List<Utilisateur> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return (List<Utilisateur>) list;
    }

    public String action(){
        getUniqueListInscrit();
        return "";
    }
    List<UtilisateurInscrit> listInscrit;
    public  List<UtilisateurInscrit> getListInscrit() {
        //UtilisateurInscrit u = new UtilisateurInscrit();
        listInscrit = new ArrayList<>();
        int i = 0;
        for (Object l : inscriptionList()) {

            List<Utilisateur> f = convertObjectToList(l);
            //ff.add(f.get(i) + " "+f.get(i+1)+ " "+f.get(i+2)+" "+f.get(i+3));
            listInscrit.add(new UtilisateurInscrit(f.get(i)+"", f.get(i+1)+"",f.get(i+2)+"",f.get(i+3)+""));

            // System.err.println(ff.get(0).getFrtLibelle() + " ");
        }
        return listInscrit ;
    }

    private List<UtilisateurInscrit> uniqueListInscrit;
   public  List<UtilisateurInscrit> getUniqueListInscrit() {
        //UtilisateurInscrit u = new UtilisateurInscrit()
        uniqueListInscrit=new ArrayList<>();
        List<UtilisateurInscrit> f = getListInscrit();
        for(int j=0 ; j<f.size();j++){
            if(f.get(j).getFormation().equals(this.libelle)){
               uniqueListInscrit.add(f.get(j));
            }
        }
        return uniqueListInscrit ;
    }

   
  
    
   
}
