/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import org.mindrot.jbcrypt.BCrypt;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author yousra
 */
public class UserService {

    // /getUser/{username}/{password}
    public ArrayList<User> getListUser(String json) throws ParseException, IOException {
        ArrayList<User> Entity = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> uj = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> list2 = (java.util.List<Map<String, Object>>) uj.get("root");
            for (Map<String, Object> obj : list2) {
                User u = new User();
                u.setUsername(obj.get("username").toString());
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                u.setRole(obj.get("roles").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                String enabled = obj.get("enabled").toString();
                if (enabled == "true") {
                    u.setEnabled(1);
                } else {
                    u.setEnabled(0);
                }
                //System.out.println("enabled :" + u.getEnabled());
                //u.setEnabled((int)enabled);
                /*1
                 String en = obj.get("enabled").toString();
                 int e =Integer.valueOf(en) ;
                 u.setEnabled(e);*/
                Entity.add(u);
            }
        } catch (IOException ex) {
        }
        return Entity;

    }

    ArrayList<User> listUser = new ArrayList<>();

    public ArrayList<User> getList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/getAllUser");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    UserService ser = new UserService();
                    listUser = ser.getListUser(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser;
    }
    
    
      public ArrayList<User> getListPartenaire() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/getAllPartenaire");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    UserService ser = new UserService();
                    listUser = ser.getListUser(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser;
    }       
            

    public int totalePartenaire() {
        ArrayList<User> listUser = getList();
        int totaleP = 0;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getRole().equals("[ROLE_PART, ROLE_USER]")) {
                totaleP++;
            }
        }
        return totaleP;
    }

    public int totalePartenereDesactiver() {
        ArrayList<User> listUser = getList();
        int totaleP = 0;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getRole().equals("[ROLE_PART, ROLE_USER]") && listUser.get(i).getEnabled() == 0) {
                totaleP++;
            }
        }
        return totaleP;
    }

    User user = new User();

    public User getUser(String username) {
        ArrayList<User> listu = getList();

        for (int i = 0; i < listu.size(); i++) {
            if (username.equals(listu.get(i).getUsername().toString())) {
                user.setId(listu.get(i).getId());
                user.setUsername(listu.get(i).getUsername().toString());
                user.setNom(listu.get(i).getNom().toString());
                user.setPrenom(listu.get(i).getPrenom().toString());
                user.setEmail(listu.get(i).getEmail().toString());
                user.setRole(listu.get(i).getRole());
                user.setPassword(listu.get(i).getPassword().toString());
                user.setEnabled(listu.get(i).getEnabled());

                System.out.println("User");
                System.out.println("Identifiant : " + user.getId());
                System.out.println("Username : " + user.getUsername());
                System.out.println("Nom : " + user.getNom());
                System.out.println("Prenom  : " + user.getPrenom());
                System.out.println("Email : " + user.getEmail());
                System.out.println("Role : " + user.getRole());
                System.out.println("Password : " + user.getPassword());
                System.out.println("Enabled : " + user.getEnabled());
            }
        }
        return user;
    }

    public String cryptPassword(String password) {
        String mdp = BCrypt.hashpw(password, BCrypt.gensalt(13));
        //mdp = mdp.replaceFirst("2a", "2y");
        char[] tab = mdp.toCharArray();
        tab[2] = 'y';
        mdp = String.valueOf(tab);
        return mdp;
    }

    public void inscriptionUser(User u) {
       /* System.out.println("User ");
        System.out.println("Nom : "+u.getNom());
        System.out.println("Prénom: "+u.getPrenom());
        System.out.println("Username : "+u.getUsername());
        System.out.println("Email: "+u.getEmail());
        System.out.println("Role: "+u.getRole());
        System.out.println("Enabled: "+u.getEnabled());
        System.out.println("Password: "+u.getPassword());*/

        ConnectionRequest con = new ConnectionRequest();
         String Url = "http://127.0.0.1/v/VBack/web/inscriptionUserMobile.php?"
                + "&nom=" + u.getNom()
                + "&prenom=" + u.getPrenom()
                + "&username=" + u.getUsername()
                + "&email=" + u.getEmail()
                + "&enabled=" + u.getEnabled()
                + "&password=" + u.getPassword()
                + "&role=" + u.getRole();
         con.setUrl(Url);
          con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
      public void UpdateStatuPartenaire(int statu ,int id) {

      ConnectionRequest con = new ConnectionRequest();
         String Url = "http://127.0.0.1/v/VBack/web/updateStatuUserMobile.php?"
                + "&statu=" + statu
                + "&id=" + id;
         con.setUrl(Url);
          con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
      
       public void SupprimrePartenaire(int id) {

      ConnectionRequest con = new ConnectionRequest();
         String Url = "http://127.0.0.1/v/VBack/web/supprimerUserMobile.php?"
                + "&id=" + id;
         con.setUrl(Url);
          con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
    
    
    
    
    
    
    
    
    public void AjouterProduit(User u) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1/v/VBack/web/app_dev.php/ajouterProduitMobile?"
                + "&nom=" + u.getNom()
                + "&prenom=" + u.getPrenom()
                + "&username=" + u.getUsername()
                + "&email=" + u.getEmail()
                + "&enabled=" + u.getEnabled()
                + "&password=" + u.getPassword()
                + "&role=" + u.getRole();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
      public List<Integer> StatistiqueP() {
        ArrayList<User> a = getListPartenaire() ;
        List<Integer> li = new ArrayList<Integer>();
        for (int i=0;i<a.size();i++){
          // li.add(Integer.parseInt(a.get(i).getMarque())) ;
            li.add(a.get(i).getEnabled()) ;
        }
        return li ;
    }

  

    
    
}

