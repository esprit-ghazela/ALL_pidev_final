/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entitie.Categorie;
import com.mycompany.myapp.entitie.Produit;
import static com.mycompany.myapp.utils.Statics.BASE_URL;
import com.twilio.Twilio;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author yousra
 */
public class ProduitService {

    public ArrayList<Produit> getListProduit(String json) throws ParseException, IOException {
        // retour entity 
        ArrayList<Produit> Entity = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> Ss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> list2 = (java.util.List<Map<String, Object>>) Ss.get("root");

            for (Map<String, Object> obj : list2) {
                Produit p = new Produit();

                p.setNom(obj.get("nom").toString());
                p.setImage(obj.get("image").toString());
                p.setMarque(obj.get("marque").toString());
                p.setReference(obj.get("reference").toString());
                p.setDescription(obj.get("description").toString());

                // float id_categorie = Float.parseFloat(obj.get("categorie").toString());
                // p.setId_categorie((int) id_categorie);
                // System.out.println(id_categorie);
                String str = obj.get("categorie").toString();
                int t = str.indexOf('.');

                //System.out.println(str.substring(4, t));
                int c = Integer.parseInt(str.substring(4, t));
                p.setId_categorie(c);

                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix(prix);
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int) quantite);

                float id = Float.parseFloat(obj.get("id").toString());

                p.setId((int) id);
                Entity.add(p);
                System.out.println("constr*************\n" + p);
            }
        } catch (IOException ex) {
            System.out.println("HGFD");
        }
        System.out.println(Entity);
        return Entity;

    }

    ArrayList<Produit> listProduit = new ArrayList<>();

    public ArrayList<Produit> getList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/afficherProduitMobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listProduit = ser.getListProduit(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }

    public void AjouterProduit(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("Produit :");
        System.out.println("Réference : " + p.getReference());
        System.out.println("Nom: " + p.getNom());
        System.out.println("Description : " + p.getDescription());
        System.out.println("Marqua: " + p.getMarque());
        System.out.println("Categorie: " + p.getId_categorie());
        System.out.println("Prix : " + p.getPrix());
        System.out.println("Quantite : " + p.getQuantite());
        System.out.println("Image : " + p.getImage());
        System.out.println("Partenaire : " + p.getPartenaire());

        String Url = "http://127.0.0.1/v/VBack/web/app_dev.php/ajouterProduitMobile?"
                + "&nom=" + p.getNom()
                + "&description=" + p.getDescription()
                + "&marque=" + p.getMarque()
                + "&prix=" + p.getPrix()
                + "&quantite=" + p.getQuantite()
                + "&image=" + p.getImage()
                + "&reference=" + p.getReference()
                + "&categorie=" + p.getId_categorie()
                + "&partenaire=" + p.getPartenaire();

        con.setUrl(Url);
        System.out.println("produit ajouter");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void SupprimerProduit(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/app_dev.php/SupprimerProduitMobile/" + id);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueue(con);
    }

    ArrayList<Produit> listProduitPartenaire = new ArrayList<>();

    public ArrayList<Produit> getStockPartenaire(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/afficherProduitMobile2/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listProduitPartenaire = ser.getListProduit(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduitPartenaire;
    }

    public int totaleQuantiterProduitEnStock(int id) {
        int totale = 0;
        ArrayList<Produit> liste = getStockPartenaire(id);
        for (int i = 0; i < liste.size(); i++) {
            totale = totale + liste.get(i).getQuantite();
        }
        return totale;
    }

    public int totaleProduitEnStock(int id) {
        ArrayList<Produit> liste = getStockPartenaire(id);
        return liste.size();
    }

    public void ModifierProduit(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("Produit :");
        System.out.println("Id : " + p.getId());
        System.out.println("Réference : " + p.getReference());
        System.out.println("Nom: " + p.getNom());
        System.out.println("Description : " + p.getDescription());
        System.out.println("Marqua: " + p.getMarque());
        System.out.println("Categorie: " + p.getId_categorie());
        System.out.println("Prix : " + p.getPrix());
        System.out.println("Quantite : " + p.getQuantite());
        System.out.println("Image : " + p.getImage());
        System.out.println("Partenaire : " + p.getPartenaire());

        String Url = "http://127.0.0.1/v/VBack/web/app_dev.php/app_dev.php/ModifierProduitMobile?"
                + "id=" + p.getId()
                + "&nom=" + p.getNom()
                + "&reference=" + p.getReference()
                + "&image=" + p.getImage()
                + "&prix=" + p.getPrix()
                + "&quantite=" + p.getQuantite()
                + "&description=" + p.getDescription()
                + "&marque=" + p.getMarque()
                + "&categorie=" + p.getId_categorie()
                + "&partenaire=" + p.getPartenaire();

        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("Modifier // :" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Produit> getStockPartenaireSearch(int id, String search) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/v/VBack/web/app_dev.php/app_dev.php/rechercheProduitMobile/" + id + "/" + search);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                try {
                    listProduitPartenaire = ser.getListProduit(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduitPartenaire;
    }

    public List<Integer> Stati() {
        ArrayList<Produit> a = getList();
        List<Integer> li = new ArrayList<Integer>();
        for (int i = 0; i < a.size(); i++) {
            // li.add(Integer.parseInt(a.get(i).getMarque())) ;
            li.add(a.get(i).getQuantite());
        }
        return li;
    }

    public List<Integer> Stati2() {
        List<Integer> li = new ArrayList<Integer>();
        try {
            ConnectionRequest r = new ConnectionRequest();

            r.setUrl(BASE_URL + "Stat");
            r.setPost(false);
            r.setHttpMethod("GET");

            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            r.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(r);

            Map<String, Object> response = (Map<String, Object>) new JSONParser().parseJSON(
                    new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            List<String> content = (List<String>) response.get("root");
            System.out.println("content ====> " + content);
            for (String obj : content) {
                li.add(Integer.parseInt(obj));
                System.out.println("li ====> " + li);
            }

        } catch (IOException err) {
            Log.e(err);
            return null;
        }
        return li;
    }

    public void SendSms(String numTel, String username) {
        String ACCOUNT_SID = "ACc91cf641904f4eb262d73f9517408e8f";
        String AUTH_TOKEN = "0b219b88b4ddd87441bd60dd14e60426";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + "ACc91cf641904f4eb262d73f9517408e8f" + "/Messages.json").
                queryParam("To", "+216"+numTel).
                queryParam("From", "+17812187273").
                queryParam("Body", "Bonjour " + username + " , Votre stock à bien été mise à jour").
                basicAuth("ACc91cf641904f4eb262d73f9517408e8f", "0b219b88b4ddd87441bd60dd14e60426").//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
                getAsJsonMap();
    }

}
