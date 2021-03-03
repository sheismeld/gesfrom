/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.projet.lo54.repository.HibernateUtilisateurDao;

import fr.utbm.projet.lo54.entity.Utilisateur;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Zome
 */
@Named(value = "usesList")
@Dependent
public class usesList implements Serializable{

    /**
     * Creates a new instance of usesList
     */
    
     private    List<Utilisateur> myList =null;
     private List<Utilisateur> uniqueUser = null;
   
     public   List<Utilisateur> getutilisateurs(){
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
         return myList;
    }
     
      public   List<Utilisateur> getuniqueUsers(){
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
         return myList;
    }
     
     
     
    public usesList() {
       this.myList= getutilisateurs();
    }
    
    
}
