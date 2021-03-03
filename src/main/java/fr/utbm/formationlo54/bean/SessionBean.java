/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.projet.lo54.entity.Localisation;
import fr.utbm.projet.lo54.repository.HibernateLocalisationDao;
import fr.utbm.projet.lo54.repository.SessionDao;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daris
 */
@RequestScoped
@Named("sessionBean")
public class SessionBean {

    private List<String> sessionVille = null;
    private List<Localisation> listVille = null;
    private String villesession;
    private Date Datedebutsession;
    private Date Datefinsession;
    private String datedebut;
    private String datefin;
    private String locCity;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getSessionVille() {
        SessionDao sessionDAO = new SessionDao();
        sessionVille = sessionDAO.g();
        String f = "";
        //f.length()
        return sessionVille;
    }

    public List<Localisation> getVille() {
        HibernateLocalisationDao locDao = new HibernateLocalisationDao();
        listVille = locDao.getVilleLocalisation();

        return listVille;
    }

    public void insertSession() {
        SessionDao sessionDao = new SessionDao();
        Long locList = HibernateLocalisationDao.villeLocalisation(this.villesession);
        int locIdentifiant = locList.intValue();
        sessionDao.InsertSession(this.datedebut, this.datefin, locIdentifiant);
        this.msg = "Insertion Réussie";
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity;
    }

    public String getLocCity() {
        return locCity;
    }

    public void setVillesession(String villesession) {
        this.villesession = villesession;
    }

    public void setDatedebutsession(Date Datedebutsession) {
        this.Datedebutsession = Datedebutsession;
    }

    public void setDatefinsession(Date Datefinsession) {
        this.Datefinsession = Datefinsession;
    }

    public String getVillesession() {
        return villesession;
    }

    public Date getDatedebutsession() {
        if (this.Datedebutsession != null) {
            String date = this.Datedebutsession + "";
            return this.Datedebutsession = new Date(date.replace("-", "/").trim());
        }
        return Datedebutsession;
    }

    public Date getDatefinsession() {
        if (this.Datefinsession != null) {
            String date = this.Datefinsession + "";
            return this.Datefinsession = new Date(date.replace("-", "/").trim());
        }
        return Datefinsession;
    }

    public String getDatedebut() {
        if (this.datedebut != null) {
            return datedebut.replaceAll("-", "/");
        }
        return datedebut;
    }

    public String getDatefin() {
        if (this.datefin != null) {
            return datefin.replaceAll("-", "/");
        }
        return datefin;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

}
