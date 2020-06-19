/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

import com.mycompany.myapp.entitie.Panier;

/**
 *
 * @author aziz khbou
 */
public class Commande extends Panier {

    private int id;
    private int user_id;
    private int prixprod;
    private int amount;
    private int prixlivr;
    private String produit;

    public Commande(int qtPanier, int id, String nom, double prix, int quantite, String reference, String image) {
        super(qtPanier, id, nom, prix, quantite, reference, image);
    }

    
    public Commande(int id, int user_id, int prixprod, int prixlivr, int amount, String produit) {
        this.id = id;
        this.user_id = user_id;
        this.prixprod = prixprod;
        this.amount = amount;

        this.prixlivr = prixlivr;
        this.produit = produit;
    }

    public Commande(int prixprod, int prixlivr, int amount, String produit) {
        this.prixprod = prixprod;
        this.amount = amount;
        this.prixlivr = prixlivr;
        this.produit = produit;

    }

    public Commande(int id, int prixprod, int prixlivr, int amount, String produit) {

        this.id = id;

        this.prixprod = prixprod;
        this.amount = amount;
        this.prixlivr = prixlivr;
        this.produit = produit;

    }

    public Commande() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrixprod() {
        return prixprod;
    }

    public void setPrixprod(int prixprod) {
        this.prixprod = prixprod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrixlivr() {
        return prixlivr;
    }

    public void setPrixlivr(int prixlivr) {
        this.prixlivr = prixlivr;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user_id=" + user_id + ", prixprod=" + prixprod + ", amount=" + amount + ", prixlivr=" + prixlivr + ", produit=" + produit + '}';
    }

}
