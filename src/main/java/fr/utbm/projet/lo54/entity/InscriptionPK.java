/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projet.lo54.entity;

import java.io.Serializable;

public class InscriptionPK implements Serializable {

    private long frtNumero;
    private long utiNumero;

    public InscriptionPK() {
    }

    public InscriptionPK(long frtNumero, long utiNumero) {
        this.frtNumero = frtNumero;
        this.utiNumero = utiNumero;
    }

    public long getFrtNumero() {
        return this.frtNumero;
    }

    public void setFrtNumero(long frtNumero) {
        this.frtNumero = frtNumero;
    }

    public long getUtiNumero() {
        return this.utiNumero;
    }

    public void setUtiNumero(long utiNumero) {
        this.utiNumero = utiNumero;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof InscriptionPK)) {
            return false;
        }
        InscriptionPK castOther = (InscriptionPK) other;

        return (this.getFrtNumero() == castOther.getFrtNumero())
                && (this.getUtiNumero() == castOther.getUtiNumero());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (int) this.getFrtNumero();
        result = 37 * result + (int) this.getUtiNumero();
        return result;
    }

}
