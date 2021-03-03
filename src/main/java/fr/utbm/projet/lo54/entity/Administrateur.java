/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.entity;

import java.io.Serializable;


/**
 *
 * @author Zome
 */
public class Administrateur implements Serializable {

    private static final long serialVersionUID = 1L;
     private Long admNumero;
     private String admNom;
     private String admPrenom;
     private String admSexe;

    public Administrateur() {
    }

	
    public Administrateur(String admNom, String admPrenom) {
        this.admNom = admNom;
        this.admPrenom = admPrenom;
    }
    public Administrateur(String admNom, String admPrenom, String admSexe) {
       this.admNom = admNom;
       this.admPrenom = admPrenom;
       this.admSexe = admSexe;
    }
   
    public Long getAdmNumero() {
        return this.admNumero;
    }
    
    public void setAdmNumero(Long admNumero) {
        this.admNumero = admNumero;
    }
    public String getAdmNom() {
        return this.admNom;
    }
    
    public void setAdmNom(String admNom) {
        this.admNom = admNom;
    }
    public String getAdmPrenom() {
        return this.admPrenom;
    }
    
    public void setAdmPrenom(String admPrenom) {
        this.admPrenom = admPrenom;
    }
    public String getAdmSexe() {
        return this.admSexe;
    }
    
    public void setAdmSexe(String admSexe) {
        this.admSexe = admSexe;
    }

    @Override
    public String toString() {
        return "fr.utbm.projet.lo54.entity.Administrateur[ admNumero=" + admNumero + " ]";
    }
    
}
