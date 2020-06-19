/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entitie.Annonce;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class AnnonceService {
    private ConnectionRequest req;
  public static AnnonceService  instance=null;
  public ArrayList<String> id_annonce;
  public ArrayList<String> description;
  
  public ArrayList<String> nom;
  public ArrayList<String> image;
   public ArrayList<String> date;
 
   public ArrayList<Annonce> listAnnonce;
    public boolean resultOK;
    
    
    public AnnonceService() {
         req = new ConnectionRequest();
    }
    
    public static AnnonceService getInstance() {
        if (instance == null) {
            instance = new AnnonceService();
        }
        return instance;
    }
    

         /*******************************************************************************************/               
    
     
         
      /************************************************************************************************/      
       public ArrayList<Annonce> parseAnnonce(String jsonText) throws ParseException{
        try {
            listAnnonce=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                

               Annonce t = new Annonce();
               

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_annonce((int)id);
               
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                t.setImage(obj.get("image").toString());
                
                
                 Map<String, Object> mapDateDebut=(Map<String, Object>) obj.get("date");
                            float date_creation = Float.parseFloat(mapDateDebut.get("timestamp").toString());
                            String dates =new com.codename1.l10n.SimpleDateFormat("dd/MM/yy").format(new Date((long) (date_creation * 1000L)));
                t.setDate(dates);
                
               
        
                listAnnonce.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return listAnnonce;
    }    
public ArrayList<Annonce> getAllAnnonce(){
       
                String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnonce";
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    listAnnonce = parseAnnonce(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                  
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listAnnonce;
    }
public ArrayList<String> parseid_Annonce(String jsonText){
        try {
            id_annonce=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("id").toString());
              
               
                id_annonce.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return id_annonce;
    }
        public ArrayList<String> parsenom(String jsonText){
        try {
            nom=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("nom").toString());
               
                
                nom.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return nom;
    }
        
            
     public ArrayList<String> parsedescription(String jsonText){
        try {
            description=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("description").toString());
               
                
                description.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return description;
    }
     public ArrayList<String> parseimage(String jsonText){
        try {
            image=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("image").toString());
               
                
                image.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return image;
    }
     
     
             public ArrayList<String> parsedate(String jsonText){
        try {
            date=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                  // String name=(obj.get("dateFinEvent").toString());
               
               Map<String, Object> mapDateDebut=(Map<String, Object>) obj.get("date");
                            float datedebutt = Float.parseFloat(mapDateDebut.get("timestamp").toString());
                            String dates =new com.codename1.l10n.SimpleDateFormat("dd/MM/yyyy").format(new Date((long) (datedebutt * 1000L)));
                
                date.add(dates);
            }
            
            
        } catch (IOException ex) {
            
        }
        return date;
    }
         public ArrayList<String> getid_annonce(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnonce";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                id_annonce = parseid_Annonce(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return id_annonce;
    }
    public ArrayList<String> getnom(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnoce";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                nom = parsenom(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nom;
    }
    public ArrayList<String> getdescription(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnonce";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                description= parsedescription(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return description;
    }
    public ArrayList<String> getimage(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnonce";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                image= parsedescription(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return image;
    }
  
    
    public ArrayList<String> getdate(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allAnnonce";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                date = parsedate(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return date;
    }
             public boolean addAnnonce(Annonce r,int id) {
        String url = "http://127.0.0.1/v/VBack/web/app_dev.php/ajouterAnnonce/"+id+"?description=" + r.getDescription()+"&nom="+r.getNom()+"&image="+r.getImage()+"&date="+r.getDate();//création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
              public boolean modifierAnnonce(Annonce r,int id) {////ne9sa matiere
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/updateann" +"?id="+ r.getId_annonce() + "&description=" + r.getDescription()+"&nom="+r.getNom()+"&image="+r.getImage()+"&date="+r.getDate();
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
                                  public ArrayList<Annonce> supprimerAnnonce(int id){
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/deleteann/"+ id;
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                 
                
                    //System.out.println("chnia mochkol "+tasks);
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listAnnonce;
    }
    
}
