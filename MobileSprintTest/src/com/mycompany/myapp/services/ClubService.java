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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.Club;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed laifi
 */
public class ClubService {
      private ConnectionRequest req;
  public static ClubService  instance=null;
  public ArrayList<String> id;
  public ArrayList<String> nom;
    public ArrayList<String> fondateur;
  public ArrayList<String> date_creation;
  public ArrayList<String> solde;
   public ArrayList<String> nomImage;
   public ArrayList<Club> listClub;
    public boolean resultOK;
    
    
    public ClubService() {
         req = new ConnectionRequest();
    }
    
    public static ClubService getInstance() {
        if (instance == null) {
            instance = new ClubService();
        }
        return instance;
    }
    

         /*******************************************************************************************/               
    
             public boolean addclub(Club e) {//baseurl : adresse ip de serveur 
        String url = "http://127.0.0.1/v/VBack/web/app_dev.php/examan/ajouterclub" + "?nom=" + e.getNom()+"&fondateur="+e.getFondateur()+"&date_creation="+e.getDate_creation()+"&solde="+e.getSolde();//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
      /************************************************************************************************/      
       public ArrayList<Club> parseclub(String jsonText) throws ParseException{
        try {
            listClub=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                

                Club t = new Club();
               

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                t.setNom(obj.get("nom").toString());
                t.setFondateur(obj.get("fondateur").toString());

               t.setSolde((int)Float.parseFloat(obj.get("solde").toString()));
                 Map<String, Object> mapDateDebut=(Map<String, Object>) obj.get("dateCreation");
                            float date_creation = Float.parseFloat(mapDateDebut.get("timestamp").toString());
                            String date =new com.codename1.l10n.SimpleDateFormat("dd/MM/yy").format(new Date((long) (date_creation * 1000L)));
                t.setDate_creation(date);
               
        
                listClub.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return listClub;
    }    
public ArrayList<Club> getAllclub(){
       
                String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/alls";
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    listClub = parseclub(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                  
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listClub;
    }
  /************************************************************************************************/    
                               public ArrayList<Club> supprimerevent(int id){
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/supprimerclub" + "?id=" + id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listClub;
    }
         public boolean modifierClub(Club e) {
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/modifierclub" + "?id=" + e.getId()+ "&nom=" + e.getNom()+"&fondateur="+e.getFondateur()+"&dateCreation=" +e.getDate_creation();//création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
             public ArrayList<Club> rembourser(int id ) {
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/rembourserclub"  + "?id=" + id;
       System.out.println(url);//création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
          return listClub;
    }
         
    public ArrayList<Club> getAllclubtrier(){
       
                String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/getAllclubtrier";
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    listClub = parseclub(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                  
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listClub;
    }
}
  