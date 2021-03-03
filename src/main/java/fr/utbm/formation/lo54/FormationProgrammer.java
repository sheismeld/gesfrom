/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formation.lo54;

import java.io.Serializable;

/**
 *
 * @author daris
 */
public class FormationProgrammer implements Serializable {
    private String frtNumero;
     private String prtCode;
     private String frtLibelle;
     private String frtDescription;
      private String sesNumero;
     private String sesDateDebut;
     private String sesDateFin;

    @Override
    public String toString() {
        return "FormationProgrammer{" + "frtNumero=" + frtNumero + ", prtCode=" + prtCode + ", frtLibelle=" + frtLibelle + ", frtDescription=" + frtDescription + ", sesNumero=" + sesNumero + ", sesDateDebut=" + sesDateDebut + ", sesDateFin=" + sesDateFin + '}';
    }

    public FormationProgrammer() {
    }

     
    public FormationProgrammer(String frtNumero, String prtCode, String frtLibelle, String frtDescription, String sesNumero, String sesDateDebut, String sesDateFin) {
        this.frtNumero = frtNumero;
        this.prtCode = prtCode;
        this.frtLibelle = frtLibelle;
        this.frtDescription = frtDescription;
        this.sesNumero = sesNumero;
        this.sesDateDebut = sesDateDebut;
        this.sesDateFin = sesDateFin;
    }
     

    public String getFrtNumero() {
        return frtNumero;
    }

    public void setFrtNumero(String frtNumero) {
        this.frtNumero = frtNumero;
    }

    public String getPrtCode() {
        return prtCode;
    }

    public void setPrtCode(String prtCode) {
        this.prtCode = prtCode;
    }

    public String getFrtLibelle() {
        return frtLibelle;
    }

    public void setFrtLibelle(String frtLibelle) {
        this.frtLibelle = frtLibelle;
    }

    public String getFrtDescription() {
        return frtDescription;
    }

    public void setFrtDescription(String frtDescription) {
        this.frtDescription = frtDescription;
    }

    public String getSesNumero() {
        return sesNumero;
    }

    public void setSesNumero(String sesNumero) {
        this.sesNumero = sesNumero;
    }

    public String getSesDateDebut() {
        return sesDateDebut;
    }

    public void setSesDateDebut(String sesDateDebut) {
        this.sesDateDebut = sesDateDebut;
    }

    public String getSesDateFin() {
        return sesDateFin;
    }

    public void setSesDateFin(String sesDateFin) {
        this.sesDateFin = sesDateFin;
    }
     
}
