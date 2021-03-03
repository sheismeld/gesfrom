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
public class SubdiviserPK implements Serializable {

    private long sesNumero;
    private long frtNumero;

    public SubdiviserPK() {
    }

    public SubdiviserPK(long sesNumero, long frtNumero) {
        this.sesNumero = sesNumero;
        this.frtNumero = frtNumero;
    }

    public long getSesNumero() {
        return this.sesNumero;
    }

    public void setSesNumero(long sesNumero) {
        this.sesNumero = sesNumero;
    }

    public long getFrtNumero() {
        return this.frtNumero;
    }

    public void setFrtNumero(long frtNumero) {
        this.frtNumero = frtNumero;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof SubdiviserPK)) {
            return false;
        }
        SubdiviserPK castOther = (SubdiviserPK) other;

        return (this.getSesNumero() == castOther.getSesNumero())
                && (this.getFrtNumero() == castOther.getFrtNumero());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (int) this.getSesNumero();
        result = 37 * result + (int) this.getFrtNumero();
        return result;
    }

}
