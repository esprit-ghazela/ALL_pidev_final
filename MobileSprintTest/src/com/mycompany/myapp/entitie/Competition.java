/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

import java.util.Objects;

/**
 *
 * @author Ahmed laifi
 */
public class Competition {
    
    private int id;
    private String region;
    private String debut;
    private String dfinal;
    private String nomcomp;
    private int prime;

    public Competition(int id, String region, String debut, String dfinal, String nomcomp, int prime) {
        this.id = id;
        this.region = region;
        this.debut = debut;
        this.dfinal = dfinal;
        this.nomcomp = nomcomp;
        this.prime = prime;
    }

    public Competition() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getDfinal() {
        return dfinal;
    }

    public void setDfinal(String dfinal) {
        this.dfinal = dfinal;
    }

    public String getNomcomp() {
        return nomcomp;
    }

    public void setNomcomp(String nomcomp) {
        this.nomcomp = nomcomp;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + ", region=" + region + ", debut=" + debut + ", dfinal=" + dfinal + ", nomcomp=" + nomcomp + ", prime=" + prime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.region);
        hash = 41 * hash + Objects.hashCode(this.debut);
        hash = 41 * hash + Objects.hashCode(this.dfinal);
        hash = 41 * hash + Objects.hashCode(this.nomcomp);
        hash = 41 * hash + this.prime;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Competition other = (Competition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.debut, other.debut)) {
            return false;
        }
        if (!Objects.equals(this.dfinal, other.dfinal)) {
            return false;
        }
        if (!Objects.equals(this.nomcomp, other.nomcomp)) {
            return false;
        }
        if (this.prime != other.prime) {
            return false;
        }
        return true;
    }
    
    
    
}
