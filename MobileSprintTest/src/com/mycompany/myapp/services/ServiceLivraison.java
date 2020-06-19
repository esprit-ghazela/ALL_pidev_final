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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.Livraison;

import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author aziz khbou
 */
public class ServiceLivraison {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Livraison> livraisons;

    public ServiceLivraison() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean ajouterLivraison(Livraison l) {
        String url = Statics.BASE_URL + "aziz/livraisons/new?" + "paiement=" + l.getPaiement_id()
                + "&" + "adresseLivr=" + l.getAdresseLivr() + "&" + "etat=" + l.getEtat() + "&" 
                + "datelivr="+ format(l.getDatelivr());
        

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
    
   
    public static String format(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(date);
        
        return format;
    }

    public ArrayList<Livraison> getAllLivraisons() {
        String url = Statics.BASE_URL + "aziz/livraisons/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    livraisons = parseLivraisons(new String(request.getResponseData()));
                    request.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return livraisons;
    }

    public ArrayList<Livraison> parseLivraisons(String jsonText)  {
        try {
            livraisons = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> livraisonsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) livraisonsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int paiement_id = (int) Float.parseFloat(obj.get("paiement_id").toString());
                String adresseLivr = obj.get("adresseLivr").toString();
                String etat = obj.get("etat").toString();
                String datestr = obj.get("datelivr").toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date datelivr = sdf.parse(datestr);
                livraisons.add(new Livraison(paiement_id,adresseLivr,etat,datelivr));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) { 
            System.out.println(ex.getMessage());
        }

        return livraisons;
    }

}
