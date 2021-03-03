/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Zome
 */
public class Inscription implements Serializable {

    private InscriptionPK id;
    private Long nbrForma;
    private BigDecimal insPrix;

    public Inscription() {
    }

    public Inscription(InscriptionPK id) {
        this.id = id;
    }

    public Inscription(InscriptionPK id, Long nbrForma, BigDecimal insPrix) {
        this.id = id;
        this.nbrForma = nbrForma;
        this.insPrix = insPrix;
    }

    public InscriptionPK getId() {
        return this.id;
    }

    public void setId(InscriptionPK id) {
        this.id = id;
    }

    public Long getNbrForma() {
        return this.nbrForma;
    }

    public void setNbrForma(Long nbrForma) {
        this.nbrForma = nbrForma;
    }

    public BigDecimal getInsPrix() {
        return this.insPrix;
    }

    public void setInsPrix(BigDecimal insPrix) {
        this.insPrix = insPrix;
    }

}
