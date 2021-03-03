/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.formation.lo54.FormationProgrammer;
import fr.utbm.formationlo54.model.Mysession;
import fr.utbm.projet.lo54.entity.Administrateur;
import fr.utbm.projet.lo54.entity.Formation;
import fr.utbm.projet.lo54.entity.Promotion;
import fr.utbm.projet.lo54.entity.Utilisateur;
import fr.utbm.projet.lo54.repository.AdministrateurDao;
import static fr.utbm.projet.lo54.repository.AdministrateurDao.cfg;
import fr.utbm.projet.lo54.repository.HibernateFormationDao;
import fr.utbm.projet.lo54.repository.SessionDao;
import fr.utbm.projetlo54.util.HibernatUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
@Named(value = "formationBean")
@ViewScoped
public class FormationBean implements Serializable {

    /**
     * Creates a new instance of FormationBean
     */
    private String date, date2;
    private int nbrForma;

    public int getNbrForma() {
        return nbrForma;
    }

    public void setNbrForma(int nbrForma) {
        this.nbrForma = nbrForma;
    }

    public String getDate2() {
        if (this.date2 != null) {
            return date2.replaceAll("-", "/");
        }
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDate() {
        if (this.date != null) {
            return date.replaceAll("-", "/");
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static List<Formation> formaList = null;
    public static List<Formation> allFormation = null;
    public static List<Promotion> promoList = null;
    public static List<Formation> uniqueForma = null;
    public static List<Administrateur> adminList = null;
    public static List<Formation> findFormaList = null;
    public static List<Formation> formaListPro = null;
    private Formation forma = null;

    public List<Formation> getFormationList() {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaList = formaDAO.listeFormation();
        return formaList;
    }

    public List<Formation> getAllFormation() {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        allFormation = formaDAO.listeFormation();
        return allFormation;
    }

    public List<Formation> getfindFormaList(String msg) {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaList = formaDAO.getRechercheFormationstout(msg);
        for (Formation f : formaList) {
            System.err.println(f.getFrtLibelle() + "  " + f.getFrtDescription());
        }
        return formaList;
    }

    public List<Formation> getformaListPro() {
        //getListInscrit();

        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaListPro = formaDAO.getformationProgramer();
        for (Formation f : formaListPro) {
            System.err.println(f.getFrtLibelle() + "  " + f.getFrtDescription());
        }
        return formaListPro;
    }

    public List<Formation> getUniqueFormation(int id) {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        uniqueForma = formaDAO.uniqueFormation(id);
        return uniqueForma;
    }

    public void deleteFormation(int id) {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaDAO.deleteFormation(id);
    }

    public FormationBean() {
    }

    private Long frtNumero;
    private Long utiNumero;
    private Long admNumero;
    private BigInteger prtCode;
    private String frtLibelle;
    private String frtDescription;
    private Date frtDateDebut;
    private Date frtDateFin;

    public Long getUtiNumero() {
        return utiNumero;
    }

    public void setUtiNumero(Long utiNumero) {
        this.utiNumero = utiNumero;
    }

    public Long getFrtNumero() {
        return this.frtNumero;
    }

    public void setFrtNumero(Long frtNumero) {
        this.frtNumero = frtNumero;
    }

    public Long getAdmNumero() {
        return this.admNumero;
    }

    public void setAdmNumero(Long admNumero) {
        this.admNumero = admNumero;
    }

    public BigInteger getPrtCode() {
        return prtCode;
    }

    public void setPrtCode(BigInteger prtCode) {
        this.prtCode = prtCode;
    }

    public String getFrtLibelle() {
        return this.frtLibelle;
    }

    public void setFrtLibelle(String frtLibelle) {
        this.frtLibelle = frtLibelle;
    }

    public String getFrtDescription() {
        return this.frtDescription;
    }

    public void setFrtDescription(String frtDescription) {
        this.frtDescription = frtDescription;
    }

    public Date getFrtDateDebut() {
        return this.frtDateDebut;
    }

    public void setFrtDateDebut(Date frtDateDebut) {
        this.frtDateDebut = frtDateDebut;
    }

    public Date getFrtDateFin() {
        return this.frtDateFin;
    }

    public void setFrtDateFin(Date frtDateFin) {
        this.frtDateFin = frtDateFin;
    }

    public void insertFormation() {
        HibernateFormationDao formaDAO = new HibernateFormationDao();

        int codenumero = Integer.parseInt(this.code);

        int adminnumero = Integer.parseInt(this.numAdm);

        formaDAO.InsertFormation(adminnumero, this.frtLibelle, this.frtDescription, codenumero, this.date, this.date2);
    }

    public void updateFormation(int id) {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaDAO.updateFormation(id, 1, frtLibelle, frtDescription, 1, frtDateDebut, frtDateFin);
        for (Formation l : formaDAO.uniqueFormation(id)) {
            System.err.println(l.getFrtLibelle() + "----------------   " + l.getFrtDescription());
        }
    }

    public List<Promotion> getPromoList() {

        promoList = new ArrayList<>();

        HibernateFormationDao formaDAO = new HibernateFormationDao();

        promoList = formaDAO.listePromotion();

        return promoList;

    }
    String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void inscrireUtilisateur() {
        Utilisateur u = new Utilisateur();
        HibernateFormationDao fDao = new HibernateFormationDao();
        this.frtNumero = HibernateFormationDao.frtNum(frtLibelle);
        this.utiNumero = HibernateFormationDao.frtNum(this.nom);
        // u.setUtiNumero(utiNumero);
        int frtnum = this.frtNumero.intValue();
        int utinum = this.utiNumero.intValue();
        fDao.InscrireUtilisateur(frtnum, utinum, 10);
    }

    public List<Administrateur> getAdminList() {

        adminList = new ArrayList<>();

        AdministrateurDao formaDAO = new AdministrateurDao();

        adminList = formaDAO.ListAdmin();

        return adminList;

    }

    private String msg;
    String numAdm, code;

    public String getNumAdm() {
        return numAdm;
    }

    public void setNumAdm(String numAdm) {
        this.numAdm = numAdm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int sesNumero;

    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getSesNumero() {
        return this.sesNumero;
    }

    public void setSesNumero(int sesNumero) {
        this.sesNumero = sesNumero;
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

    public static int sesNum(String nom) {
        String listeLocation;
        int idutil = 0;
        //listeLocation = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                org.hibernate.Query query = session.createQuery("SELECT sesNumero FROM Mysession where sesNumero=:nom");
                query.setParameter("nom", nom);
                listeLocation = query.getSingleResult().toString();
                idutil = Integer.parseInt(listeLocation);
                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return idutil;
    }

    private List<Formation> formList = null;
    private List<Mysession> sessionList = null;

    public List<Mysession> getUserList() {
        SessionDao userDAO = new SessionDao();
        sessionList = userDAO.ListSession();
        return sessionList;
    }

    public List<Formation> getFormationListprogramr() {
        HibernateFormationDao formaDAO = new HibernateFormationDao();
        formaList = formaDAO.listeFormation();
        return formaList;
    }

    public void InscrireFormation(int a, int b) {

        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String sqlInsert = "INSERT INTO subdiviser(SES_NUMERO,FRT_NUMERO)"
                        + " VALUES (?,?)";

                session.createSQLQuery(sqlInsert)
                        .setParameter(1, a)
                        .setParameter(2, b)
                        .executeUpdate();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Object> inscriptionformaList() {

        List<Object> listeforma;
        listeforma = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();

        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                String hql = "SELECT f.frtNumero,f.prtCode,f.frtLibelle,f.frtDescription,m.sesNumero,m.sesDateDebut,m.sesDateFin "
                        + "FROM Formation f "
                        + "INNER JOIN Subdiviser s ON f.frtNumero=s.id.frtNumero "
                        + "INNER JOIN Mysession m on m.id.sesNumero = s.id.sesNumero";
                Query query = session.createQuery(hql);
                listeforma = query.list();
                t.commit();
                session.close();

            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return listeforma;
    }

    public static List<Formation> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return (List<Formation>) list;
    }

    public String action() {

       // System.out.println("*************************************in returnAction " + libelle);

        //getUniqueListInscrit();
        return "";
    }
    List<FormationProgrammer> listInscrit;

    public List<FormationProgrammer> getListInscrit() {

        listInscrit = new ArrayList<>();
        int i = 0;
        for (Object l : inscriptionformaList()) {

            List<Formation> f = convertObjectToList(l);
            listInscrit.add(new FormationProgrammer(f.get(i) + "", f.get(i + 1) + "", f.get(i + 2) + "", f.get(i + 3) + "", f.get(i + 4) + "", f.get(i + 5) + "", f.get(i + 6) + ""));
            //System.err.println(f.get(0).getFrtLibelle() );
        }
        return listInscrit;
    }

    private List<FormationProgrammer> uniqueListInscrit;

    public List<FormationProgrammer> getUniqueListInscrit() {
        //UtilisateurInscrit u = new UtilisateurInscrit()
        //this.libelle="3";
        uniqueListInscrit = new ArrayList<>();
        List<FormationProgrammer> f = getListInscrit();
        for (int j = 0; j < f.size(); j++) {
            if (this.libelle != null) {
                if (f.get(j).getFrtLibelle().equalsIgnoreCase(this.libelle.trim())) {

                    this.frtcount = getNombreInscrit(f.get(j).getFrtLibelle());
                    uniqueListInscrit.add(f.get(j));
                } else if (f.get(j).getSesDateDebut().equalsIgnoreCase(this.libelle.trim()) || f.get(j).getSesDateFin().equalsIgnoreCase(this.libelle)) {
                    this.frtcount = getNombreInscrit(f.get(j).getFrtLibelle());
                    uniqueListInscrit.add(f.get(j));

                } else if (f.get(j).getSesNumero().equalsIgnoreCase(this.libelle.trim())) {
                    this.frtcount = getNombreInscrit(f.get(j).getFrtLibelle());
                    uniqueListInscrit.add(f.get(j));

                } else if (f.get(j).getFrtNumero().equalsIgnoreCase(this.libelle.trim())) {
                    this.frtcount = getNombreInscrit(f.get(j).getFrtLibelle());
                    uniqueListInscrit.add(f.get(j));
                }
            }

        }
        return uniqueListInscrit;
    }

    public String returnAction() {
        System.out.println("in returnAction " + nom + " " + frtLibelle);
        // this.montant=10;
        //this.utiNumero=2;
        // this.frtNumero=6;
        this.frtNume = Integer.parseInt(this.frtLibelle);
        this.sesNumero = Integer.parseInt(this.nom);

        if (this.frtNume == 0) {
            return frtNume == 0 ? "success" : "failure";
        } else {
            InscrireFormation(this.sesNumero, this.frtNume);
            return frtNume != 0 ? "success" : "failure";
        }

    }

    private int frtNume;

    public int getFrtNume() {
        return frtNume;
    }

    public void setFrtNume(int frtNume) {
        this.frtNume = frtNume;
    }

    private int frtcount;

    public int getFrtcount() {
        if (this.frtLibelle == null || this.frtLibelle.equalsIgnoreCase("")) {
            return getNombreInscrit("");
        } else {
            return getNombreInscrit(this.frtLibelle);
        }

    }

    public void setFrtcount(int frtcount) {
        this.frtcount = frtcount;
    }

    public int getNombreInscrit(String nom) {
        String listeLocation;
        int idutil = 0;
        //listeLocation = new ArrayList<>();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        try {
            try (Session session = HibernatUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                org.hibernate.Query query = session.createQuery("SELECT COUNT(frtNumero) FROM Formation where frtLibelle=:nom");
                org.hibernate.Query query2 = session.createQuery("SELECT COUNT(frtNumero) FROM Formation");

                query.setParameter("nom", nom);
                if (nom.equalsIgnoreCase("") || nom == null) {
                    listeLocation = query2.getSingleResult().toString();
                    this.frtcount = Integer.parseInt(listeLocation);
                } else {
                    listeLocation = query.getSingleResult().toString();
                    this.frtcount = Integer.parseInt(listeLocation);
                }

                session.close();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return this.frtcount;
    }

    @Override
    public String toString() {
        return "FormationBean{" + "date=" + date + ", date2=" + date2 + ", formaList=" + formaList + ", uniqueForma=" + uniqueForma + ", forma=" + forma + ", frtNumero=" + frtNumero + ", admNumero=" + admNumero + ", prtCode=" + prtCode + ", frtLibelle=" + frtLibelle + ", frtDescription=" + frtDescription + ", frtDateDebut=" + frtDateDebut + ", frtDateFin=" + frtDateFin + ", msg=" + msg + ", numAdm=" + numAdm + ", code=" + code + '}';
    }
}
