/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entitie.Reclamation;
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
public class ReclamationService {
      private ConnectionRequest req;
  public static ReclamationService  instance=null;
  public ArrayList<String> id_reclamation;
  public ArrayList<String> reclamation;
    public ArrayList<String> categorie;
  public ArrayList<String> nom;
  public ArrayList<String> prenom;
   public ArrayList<String> date;
   public ArrayList<Reclamation> listReclamation;
    public boolean resultOK;
    
    
    public ReclamationService() {
         req = new ConnectionRequest();
    }
    
    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }
    

         /*******************************************************************************************/               
    
     
         
      /************************************************************************************************/      
       public ArrayList<Reclamation> parsereclamation(String jsonText) throws ParseException{
        try {
            listReclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                

                Reclamation t = new Reclamation();
               

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_reclamation((int)id);
               
                t.setNom(obj.get("nom").toString());
                t.setReclamation(obj.get("reclamation").toString());
                
                
                t.setPrenom(obj.get("prenom").toString());
                 Map<String, Object> mapDateDebut=(Map<String, Object>) obj.get("date");
                            float date_creation = Float.parseFloat(mapDateDebut.get("timestamp").toString());
                            String dates =new com.codename1.l10n.SimpleDateFormat("dd/MM/yy").format(new Date((long) (date_creation * 1000L)));
                t.setDate(dates);
               
        
                listReclamation.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return listReclamation;
    }    
public ArrayList<Reclamation> getAllReclamation(){
       
                String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    listReclamation = parsereclamation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                  
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listReclamation;
    }
public ArrayList<String> parseid_reclamation(String jsonText){
        try {
            id_reclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("id").toString());
              
               
                id_reclamation.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return id_reclamation;
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
        public ArrayList<String> parseprenom(String jsonText){
        try {
            prenom=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("prenom").toString());
               
                
                prenom.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return prenom;
    } public ArrayList<String> parsereclamations(String jsonText){
        try {
            reclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.print("size :"+list.size());
            for(Map<String,Object> obj : list){
                
                   String name=(obj.get("reclamation").toString());
               
                
                reclamation.add(name);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamation;
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
         public ArrayList<String> getid_reclamation(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                id_reclamation = parseid_reclamation(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return id_reclamation;
    }
    public ArrayList<String> getnom(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       
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
    public ArrayList<String> getreclamation(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                reclamation = parsereclamations(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamation;
    }
  
    public ArrayList<String> getprenom(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                prenom = parseprenom(new String(req.getResponseData()));
                 //System.out.println("chnia mochkol "+tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prenom;
    }
    public ArrayList<String> getdate(){
                   
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/allReclamation";
       
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
             public boolean addreclamation(Reclamation r,int id) {
        String url = "http://127.0.0.1/v/VBack/web/app_dev.php/ajouterReclamation/"+id+"?reclamation=" + r.getReclamation()+"&categorie="+r.getCategorie()+"&nom="+r.getNom()+"&prenom="+r.getPrenom()+"&date="+r.getDate();//création de l'URL
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
              public boolean modifierreclamation(Reclamation e,int id) {////ne9sa matiere
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/modifierReclamation"+ "?id=" + e.getId_reclamation() + "&nom=" + e.getNom()+"&prenom="+e.getPrenom()+"&date="+e.getDate()+"&reclamation="+e.getReclamation();
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
                                  public ArrayList<Reclamation> supprimerreclamation(int id){
        String url ="http://127.0.0.1/v/VBack/web/app_dev.php/supprimerReclamation" + "?id=" + id;
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
        return listReclamation;
    }
        
         
        
         
   
}
  