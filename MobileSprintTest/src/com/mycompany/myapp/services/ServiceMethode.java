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
import com.mycompany.myapp.entitie.Methodedepaiement;

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
public class ServiceMethode {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Methodedepaiement> methodes;

    public ServiceMethode() {
        request = DataSource.getInstance().getRequest();
    }
    public  ArrayList<Methodedepaiement> getAllMethodes() {
        String url = Statics.BASE_URL + "methodes/all";

        if (url.equals("http://127.0.0.1/v/VBack/web/app_dev.php/aziz/commandes/all")) {
            System.out.println("lol");
        }

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                methodes = parseMethodes(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return methodes;
    }
     public ArrayList<Methodedepaiement> parseMethodes(String jsonText) {
        try {
            methodes= new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());               
                String name = obj.get("name").toString();
                methodes.add(new Methodedepaiement(id, name));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return methodes;
    }
}
