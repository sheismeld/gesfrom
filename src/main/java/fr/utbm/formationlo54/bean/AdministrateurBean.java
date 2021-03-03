/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.projet.lo54.entity.Administrateur;
import fr.utbm.projet.lo54.repository.AdministrateurDao;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author daris
 */
@Named(value = "administrateurBean")
@RequestScoped
public class AdministrateurBean {
    
    private String replyMessage="";

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    private List<Administrateur> adminList = null;
    private String admsexe;
    private String admnom;
    private String admprenom;

    public void setAdmnom(String admnom) {
        this.admnom = admnom;
    }

    public void setAdmprenom(String admprenom) {
        this.admprenom = admprenom;
    }

    public String getAdmnom() {
        return admnom;
    }

    public String getAdmprenom() {
        return admprenom;
    }

    public String getAdmsexe() {
        return admsexe;
    }

    public void setAdmsexe(String admsexe) {
        this.admsexe = admsexe;
    }
    
    
    public List<Administrateur> getAdminList(){
        AdministrateurDao admDao = new AdministrateurDao();
        adminList = admDao.ListAdmin();
        return adminList;
    }
    
    public void insertAdmin(){   
        AdministrateurDao admDao = new AdministrateurDao();
        admDao.InsertAdmin(admnom,admprenom,admsexe);
        this.replyMessage="Insertion Réussie";
    }
}
