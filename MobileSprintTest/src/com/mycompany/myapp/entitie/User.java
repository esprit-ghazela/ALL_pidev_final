/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entitie;

/**
 *
 * @author yousra
 */
public class User {
    private int id ;
    private String username ;
    private String nom ;
    private String prenom ;
    private String role ;
    private int enabled ;
    private String password ;
    private String email ;

    public User() {
    }

    public User(int id, String username, String nom, String prenom, String role, int enabled, String password, String email) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.enabled = enabled;
        this.password = password;
        this.email = email;
    }

    public User(String username, String nom, String prenom, String role, int enabled, String password, String email) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.enabled = enabled;
        this.password = password;
        this.email = email;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
    
}
