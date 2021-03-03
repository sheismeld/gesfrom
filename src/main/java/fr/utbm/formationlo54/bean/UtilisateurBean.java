/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.projet.lo54.repository.HibernateUtilisateurDao;
import fr.utbm.projet.lo54.entity.Utilisateur;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Zome
 */
@Named(value = "utilisateurBean")
@ViewScoped
public class UtilisateurBean implements Serializable {

    private Long utiNumero;
    private String utiNom;
    private String utiPrenom;
    private Date utiDateNaissance;
    private BigInteger utiNumeroRue;
    private String utiNomRue;
    private String utiVille;
    private String utiCodePostale;
    private String utiAdresseEmail;
    private String utiPassword;
    private String date;

    public String getDate() {
        if (this.date != null) {
            return date.replaceAll("-", "/");
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private List<Utilisateur> userList = null;
    private List<Utilisateur> uniqueUser = null;
    private Utilisateur user = null;

    public List<Utilisateur> getUserList() {
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
        userList = userDAO.userList();
        return userList;
    }

    public List<Utilisateur> getUniqueUser(int id) {
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
        uniqueUser = userDAO.uniqueUser(id);
        return uniqueUser;
    }

    public String insertUsser() {
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
        String num = utiNumeroRue + "";
        int numero = Integer.parseInt(num);
        userDAO.insertUser(utiNom, utiPrenom, this.date, numero, utiNomRue, utiVille, utiCodePostale, utiAdresseEmail, utiPassword);
        this.msg = "Insertion réussie";
        return "1".equals("1") ? "success" : "error";
    }

    public void deleteUser(int id) {
        HibernateUtilisateurDao userDAO = new HibernateUtilisateurDao();
        userDAO.deleteUser(id);
    }

    public Long getUtiNumero() {
        return this.utiNumero;
    }

    public void setUtiNumero(Long utiNumero) {
        this.utiNumero = utiNumero;
    }

    public String getUtiNom() {
        return this.utiNom;
    }

    public void setUtiNom(String utiNom) {
        this.utiNom = utiNom;
    }

    public String getUtiPrenom() {
        return this.utiPrenom;
    }

    public void setUtiPrenom(String utiPrenom) {
        this.utiPrenom = utiPrenom;
    }

    public Date getUtiDateNaissance() {
        if (this.utiDateNaissance != null) {
            String date = this.utiDateNaissance + "";
            return this.utiDateNaissance = new Date(date.replace("-", "/").trim());
        }

        return this.utiDateNaissance;
    }

    public void setUtiDateNaissance(Date utiDateNaissance) {
        this.utiDateNaissance = utiDateNaissance;
    }

    public BigInteger getUtiNumeroRue() {
        return this.utiNumeroRue;
    }

    public void setUtiNumeroRue(BigInteger utiNumeroRue) {
        this.utiNumeroRue = utiNumeroRue;
    }

    public String getUtiNomRue() {
        return this.utiNomRue;
    }

    public void setUtiNomRue(String utiNomRue) {
        this.utiNomRue = utiNomRue;
    }

    public String getUtiVille() {
        return this.utiVille;
    }

    public void setUtiVille(String utiVille) {
        this.utiVille = utiVille;
    }

    public String getUtiCodePostale() {
        return this.utiCodePostale;
    }

    public void setUtiCodePostale(String utiCodePostale) {
        this.utiCodePostale = utiCodePostale;
    }

    public String getUtiAdresseEmail() {
        return this.utiAdresseEmail;
    }

    public void setUtiAdresseEmail(String utiAdresseEmail) {
        this.utiAdresseEmail = utiAdresseEmail;
    }

    public String getUtiPassword() {
        return this.utiPassword;
    }

    public void setUtiPassword(String utiPassword) {
        this.utiPassword = utiPassword;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
