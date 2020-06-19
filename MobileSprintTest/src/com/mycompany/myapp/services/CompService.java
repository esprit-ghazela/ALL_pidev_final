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

import com.mycompany.myapp.entitie.Competition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed laifi
 */
public class CompService {
     private ConnectionRequest req;
  public static CompService  instance=null;
  public ArrayList<String> id;
  public ArrayList<String> region;
   public ArrayList<String> debut;
     public ArrayList<String> dfinal;
    public ArrayList<String> nomnomcomp;

  public ArrayList<String> prime;
  
   public ArrayList<Competition> listComp;
    public boolean resultOK;
    
    
    
    public CompService() {
         req = new ConnectionRequest();
    }
    
    public static CompService getInstance() {
        if (instance == null) {
            instance = new CompService();
        }
        return instance;
    }
    
    
    
    
    
    
    /************************************************************************************************/      
       public ArrayList<Competition> parsecomp(String jsonText) throws ParseException{
        try {
            listComp=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                

                Competition t = new Competition();
               

                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
                t.setRegion(obj.get("region").toString());
                t.setNomcomp(obj.get("nomComp").toString());

               t.setPrime((int)Float.parseFloat(obj.get("prime").toString()));
                 Map<String, Object> mapDateDebut=(Map<String, Object>) obj.get("debut");
                            float debut = Float.parseFloat(mapDateDebut.get("timestamp").toString());
                            String date =new com.codename1.l10n.SimpleDateFormat("dd/MM/yy").format(new Date((long) (debut * 1000L)));
                t.setDebut(date);
                    Map<String, Object> mapDateFinal=(Map<String, Object>) obj.get("final");
                            float dfinal = Float.parseFloat(mapDateFinal.get("timestamp").toString());
                            String date2 =new com.codename1.l10n.SimpleDateFormat("dd/MM/yy").format(new Date((long) (dfinal * 1000L)));
                t.setDfinal(date2);
               
        
                listComp.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return listComp;
    }    
public ArrayList<Competition> allcomp(){
       
                String url ="http://127.0.0.1/v/VBack/web/app_dev.php/examan/competition/allcomp";
       System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                    listComp = parsecomp(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                  
                    req.removeResponseListener(this);
              
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listComp;
    }
    
    
    
}
 
    
