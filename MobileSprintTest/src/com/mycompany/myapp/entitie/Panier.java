/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

import java.util.Objects;

/**
 *
 * @author yousra
 */
public class Panier extends Produit {
    
    private int qtPanier ;

    public Panier() {
    }

    public Panier(int qtPanier, int id, String nom, double prix, int quantite, String reference, String image) {
        super(id, nom, prix, quantite, reference, image);
        this.qtPanier = qtPanier;
    }

   

    public int getQtPanier() {
        return qtPanier;
    }

    public void setQtPanier(int qtPanier) {
        this.qtPanier = qtPanier;
    }

   
    
    
    
    
     
    
}
