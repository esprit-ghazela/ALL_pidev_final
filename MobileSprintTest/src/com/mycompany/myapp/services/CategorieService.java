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
import com.codename1.l10n.ParseException;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author yousra
 */
public class CategorieService {

     public ArrayList<Categorie> getListCategorie(String json) throws ParseException, IOException {
        // retour entity 
        ArrayList<Categorie> Entity = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> Ss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> list2 = (java.util.List<Map<String, Object>>) Ss.get("root");
 
            for (Map<String, Object> obj : list2) {
                Categorie c = new Categorie();

                c.setNom(obj.get("nom").toString());
                c.setDescription(obj.get("description").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                
                Entity.add(c);
                System.out.println("constr*************\n" + c);
            }
        } catch (IOException ex) {
            System.out.println("HGFD");
        }
        System.out.println(Entity);
        return Entity;

    }

     
    ArrayList<Categorie> listCategorie = new ArrayList<>();
    public ArrayList<Categorie> getList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/afficherCategorieMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategorieService ser = new CategorieService();
                try {
                    try {
                        listCategorie = ser.getListCategorie(new String(con.getResponseData()));
                    } catch (IOException ex) {
                        //Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ParseException ex) {
                    System.out.println(""+ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategorie;
    }
    
    String[] c1  ;
    public String[] getNomCategorie() {
        ArrayList<Categorie> listc = getList();
        
        
            for (int i=0 ;i<listc.size();i++){
               //System.out.println("qdrgfvervg ????? "+listc.get(i).getNom().toString());
               //c1[i] = listc.get(i).getNom().toString() ;
            } 
        
        return c1;
    }

   
}
