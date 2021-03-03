/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formation.lo54;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yves
 */
public class UtilisateurInscrit implements Serializable{
    private String nom,prenom,email,formation;
    public  List<UtilisateurInscrit> utilisateurInscrit=new ArrayList<>();

    public UtilisateurInscrit() {
    }

    @Override
    public String toString() {
        return "UtilisateurInscrit{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", formation=" + formation + '}';
    }
    

    public UtilisateurInscrit(String nom, String prenom, String email, String formation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.formation = formation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    
    
}
