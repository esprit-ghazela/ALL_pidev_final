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
import com.mycompany.myapp.entitie.Commande;
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
public class ServiceCommande {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Commande> commandes;

    public ServiceCommande() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean ajouterCommande(Commande c) {
        String url = Statics.BASE_URL + "aziz/commandes/new?" + "prixprod=" + c.getPrixprod()
                + "&" + "prixlivr=" + c.getPrixlivr() + "&" + "amount=" + c.getAmount() + "&" + "produit=" + c.getProduit();

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

    public ArrayList<Commande> getAllCommandes() {
        String url = Statics.BASE_URL + "aziz/commandes/all";

        if (url.equals("http://127.0.0.1/v/VBack/web/app_dev.php/aziz/commandes/all")) {
            System.out.println("lol");
        }

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return commandes;
    }

    public ArrayList<Commande> findById(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/aziz/commandes/find/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCommande ser = new ServiceCommande();
                commandes = ser.parseCommandes(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }

    public ArrayList<Commande> parseCommandes(String jsonText) {
        System.out.println(jsonText);
        try {
            commandes = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
             for (Map<String, Object> obj : list) {
               int id = (int) Float.parseFloat(obj.get("id").toString());

                int prixprod = (int) Float.parseFloat(obj.get("prixprod").toString());
                int prixlivr = (int) Float.parseFloat(obj.get("prixlivr").toString());
                int amount = (int) Float.parseFloat(obj.get("prixprod").toString()) +(int) Float.parseFloat(obj.get("prixlivr").toString());

                String produit = obj.get("produit").toString();
                commandes.add(new Commande(id,prixprod, prixlivr, amount, produit));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return commandes;
    }
    String[] c1  ;
    public String[] getNomCategorie() {
        ArrayList<Commande> listc = getAllCommandes();
        
        
            for (int i=0 ;i<listc.size();i++){
               //System.out.println("qdrgfvervg ????? "+listc.get(i).getNom().toString());
               //c1[i] = listc.get(i).getNom().toString() ;
            } 
        
        return c1;
    }

}
