/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.Paiement;

import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aziz khbou
 */
public class ServicePaiement {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Paiement> paiements;

    public ServicePaiement() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean ajouterPaiement(Paiement p) {
        String url = Statics.BASE_URL + "paiements/new?" + "email=" + p.getEmail()
                + "&" + "nom_companie=" + p.getNom_companie() + "&" + "pays=" + p.getPays()
                + "&" + "district=" + p.getDistrict()
                + "&" + "codePostal=" + p.getCodePostal() + "&" + "commande=" + p.getCommande_id()
                + "&" + "methodedepaiement=" + p.getMethodedepaiement_id();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public ArrayList<Paiement> getAllPaiements() {
        String url = Statics.BASE_URL + "paiements/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paiements = parsePaiements(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return paiements;
    }

    public ArrayList<Paiement> parsePaiements(String jsonText) {
        System.out.println(jsonText);
        try {
            paiements = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> paiementsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) paiementsListJson.get("root");
            for (Map<String, Object> obj : list) {
                System.out.println(obj);
                Object o_commande =obj.get("commande")  ;
                 int commande_id=0;
                if(o_commande!=null){
                    String commande_json  =o_commande.toString();
                //parse 
                    System.out.println(commande_json);
                Map<String, Object> commandeListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                Map<String, Object> com = ((List<Map<String, Object>>) commandeListJson.get("root")).get(1);
//                if(com.get("commande")!=null){
//                    commande_id= Integer.parseInt(com.get("commande").toString());
//                }
               
                }
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String email = obj.get("email").toString();
                String nom_companie = obj.get("nomCompanie").toString();
                String pays = obj.get("pays").toString();
                String district = obj.get("district").toString();
                int codePostal = (int) Float.parseFloat(obj.get("codePostal").toString());
                Object o_methode = obj.get("methodedepaiement");
                int  methodedepaiement_id =0;
                if(o_methode!=null){
                   
                }
                paiements.add(new Paiement(id,email, nom_companie, pays, district, codePostal, commande_id, 1));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return paiements;
    }
}

