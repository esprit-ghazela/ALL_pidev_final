/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

/**
 *
 * @author aziz khbou
 */
public class Methodedepaiement {
    private int id;
    private String name;

    public Methodedepaiement(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Methodedepaiement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "methode{" + "id=" + id + ", name=" + name + '}';
    }
    
}

