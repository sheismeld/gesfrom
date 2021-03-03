/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zome
 */
@Entity
@Table(name = "promotion", catalog = "lo54formation", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
    @NamedQuery(name = "Promotion.findByPrtCode", query = "SELECT p FROM Promotion p WHERE p.prtCode = :prtCode"),
    @NamedQuery(name = "Promotion.findByPrtProucentageReduction", query = "SELECT p FROM Promotion p WHERE p.prtProucentageReduction = :prtProucentageReduction")})
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRT_CODE", nullable = false)
    private Long prtCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRT_PROUCENTAGE_REDUCTION", nullable = false, precision = 3, scale = 2)
    private BigDecimal prtProucentageReduction;

    public Promotion() {
    }

    public Promotion(Long prtCode) {
        this.prtCode = prtCode;
    }

    public Promotion(Long prtCode, BigDecimal prtProucentageReduction) {
        this.prtCode = prtCode;
        this.prtProucentageReduction = prtProucentageReduction;
    }

    public Long getPrtCode() {
        return prtCode;
    }

    public void setPrtCode(Long prtCode) {
        this.prtCode = prtCode;
    }

    public BigDecimal getPrtProucentageReduction() {
        return prtProucentageReduction;
    }

    public void setPrtProucentageReduction(BigDecimal prtProucentageReduction) {
        this.prtProucentageReduction = prtProucentageReduction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prtCode != null ? prtCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.prtCode == null && other.prtCode != null) || (this.prtCode != null && !this.prtCode.equals(other.prtCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.utbm.projet.lo54.entity.Promotion[ prtCode=" + prtCode + " ]";
    }

}
