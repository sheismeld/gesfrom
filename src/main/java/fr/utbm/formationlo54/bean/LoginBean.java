/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

/**
 *
 * @author Zome
 */
import fr.utbm.projet.lo54.entity.Utilisateur;
import static fr.utbm.projet.lo54.repository.AdministrateurDao.cfg;
import fr.utbm.projetlo54.util.HibernatUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@Named ("loginBean")
@SessionScoped
public class LoginBean implements Serializable {


    private static final long serialVersionUID = -5433850275008415405L;

    private String utiAdresseEmail ;
    private String utiPassword ;
    
    public String getUtiAdresseEmail() {
        return utiAdresseEmail;
    }

    public void setUtiAdresseEmail(String login) {
        this.utiAdresseEmail = login;
    }
    
    public String getUtiPassword() {
        return utiPassword;
    }
    
    public void setUtiPassword(String password) {
      //  System.out.println( "in setPassword with " + password );
        this.utiPassword = password;
    }
    
    private boolean isUser=false;
    public String returnAction() {
       for(Utilisateur u:userList()){
           if(this.utiAdresseEmail.equals(u.getUtiAdresseEmail()) && this.utiPassword.equals(u.getUtiPassword())) {
               return utiPassword.equals(u.getUtiPassword()) ? "success" : "failure";
           }
       }
       
       return null;
    }
    
    
    public  List<Utilisateur> userList() {
        List<Utilisateur> listeUser;
        listeUser = new ArrayList<>();

        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                String hql = "FROM fr.utbm.projet.lo54.entity.Utilisateur where utiAdresseEmail='"+utiAdresseEmail+"' and utiPassword='"+utiPassword+"'";
                Query query = session.createQuery(hql);
                listeUser = query.list();
                session.close();
                }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }

        return listeUser;
    }
    
}