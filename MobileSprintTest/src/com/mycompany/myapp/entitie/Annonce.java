/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

import java.util.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author ACER
 */
public class Annonce {
      private int id_annonce;
    private String nom;
    private String image;
    private ImageView photo;
    private String date;
    private String description;

    public Annonce(int id_annonce, String nom, String image, ImageView photo, String date, String description) {
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.image = image;
        this.photo = photo;
        this.date = date;
        this.description = description;
    }

    public Annonce(String nom, String image, String date, String description) {
        this.nom = nom;
        this.image = image;
        this.date = date;
        this.description = description;
    }

    public Annonce() {
    }

    public Annonce(int id_annonce, String nom, String image, String date, String description) {
        this.id_annonce = id_annonce;
        this.nom = nom;
        this.image = image;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", nom=" + nom + ", image=" + image + ", photo=" + photo + ", date=" + date + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id_annonce;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.image);
        hash = 29 * hash + Objects.hashCode(this.photo);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (this.id_annonce != other.id_annonce) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
}
