/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationlo54.bean;

import fr.utbm.projet.lo54.entity.Localisation;
import fr.utbm.projet.lo54.repository.HibernateLocalisationDao;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author daris
 */
@Named(value = "localisationBean")
@RequestScoped
public class LocalisationBean {
    
    private List<Localisation> lisLocal = null;

    public List<Localisation> getlocalisation(){
        HibernateLocalisationDao locDao = new HibernateLocalisationDao();
        lisLocal = locDao.listeLocation();
        return lisLocal;
    }
}
