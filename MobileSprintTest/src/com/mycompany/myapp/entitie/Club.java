/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ahmed laifi
 */
public class Club {
    

   
    private int id;
    private String nom;
    private String fondateur;
    private String date_creation;
    private String nomImage;
    private int solde;

public Club() {
    

    }



    public Club(int id, String nom, String fondateur, String date_creation, int solde,String nomImage) {
        this.id = id;
        this.nom = nom;
        this.fondateur = fondateur;
         this.date_creation = date_creation;
        this.solde = solde;
        this.nomImage=nomImage;
    }


    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFondateur() {
        return fondateur;
    }

    

    public void setFondateur(String fondateur) {
        this.fondateur = fondateur;
    }
        public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.fondateur);
        hash = 53 * hash + Objects.hashCode(this.date_creation);
        hash = 53 * hash + Objects.hashCode(this.nomImage);
        hash = 53 * hash + this.solde;
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
        final Club other = (Club) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.fondateur, other.fondateur)) {
            return false;
        }
        if (!Objects.equals(this.date_creation, other.date_creation)) {
            return false;
        }
        if (!Objects.equals(this.nomImage, other.nomImage)) {
            return false;
        }
        if (this.solde != other.solde) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", nom=" + nom + ", fondateur=" + fondateur + ", date_creation=" + date_creation + ", nomImage=" + nomImage + ", solde=" + solde + '}';
    }

    

    
}




