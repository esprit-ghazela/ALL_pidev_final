/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.myapp.entitie.Categorie;
import com.mycompany.myapp.entitie.Produit;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yousra
 */
public class listeProduitB extends Form {

    Form current;
    User user = SignInForm.recupUser;
    static Produit b = new Produit();
    ProduitService produitService = new ProduitService();

    ArrayList<Produit> list2 = new ArrayList<>();
    
    ArrayList<Produit> listenew ;

    public listeProduitB(ArrayList<Produit> liste) {
        current = this;
        Style prodStyle = getAllStyles();
        prodStyle.setBgColor(0x9ACCFF);
        setTitle("Produit");

        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_BACKUP, ev -> {
                    new gestionProduit(current).show();
                });
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });

        /**
         * ***********
         */
        /**
         * ****************************************************************************************
         */
        Label a = new Label("Vos Produit");
        a.setUIID("Title");
        Container c = new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP, 70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        /**
         * ********************************************************************************
         */

        Style s3 = getAllStyles();

        /**
         * **********************************
         */
        Container listss = new Container(BoxLayout.y());
        /*
         getToolbar().addSearchCommand(ev -> {
         String text = (String) ev.getSource();
         // System.out.println(text);
         //String text = (String) ev.getSource();
         if (text == null || text.length() == 0) {
         ArrayList<Produit> liste = produitService.getStockPartenaire(user.getId());
         listss.revalidate();
         } else {
         text = text.toLowerCase();
         ArrayList<Produit> liste = produitService.getStockPartenaireSearch(user.getId(),text);
         listss.revalidate();
                
         }
         });*/

        TextField searchField = new TextField("", "Chercher..."); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);

        Style s = UIManager.getInstance().getComponentStyle("Title");
        //getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        Button schB = new Button(searchIcon);

        //Container chCnt = BoxLayout.encloseX(searchField);
        Container cnt = BorderLayout.centerEastWest(searchField, schB, null);
        listss.add(cnt);
        int ty = 1;
        schB.addActionListener(ev -> {
            String text = searchField.getText();
           // System.out.println("Brainwash ...");
           //ArrayList<Produit> liste = produitService.getStockPartenaire(user.getId());
            listenew = produitService.getStockPartenaireSearch(user.getId(), text);
            listss.revalidate();
            new listeProduitB(listenew).show();

        });
        Container lists = new Container(BoxLayout.y());
        for (Produit c1 : liste) {
            {
                listss.add(createContainer(c1));
            }

        }

        /**
         * **********************
         */
        listss.setScrollableY(true);
        Style st = lists.getAllStyles();
        st.setMargin(Component.BOTTOM, 900);

        Tabs t = new Tabs();
        Style s2 = UIManager.getInstance().getComponentStyle("Tab");
        t.setUIID("Tab");

        t.addTab("Produit en stock", listss);
        t.setScrollableY(true);
        add(t);

        // lists.setScrollableY(true);
    }

    private Container createContainer(Produit p) {

        FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "TitleCommand", 4);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, "TitleCommand", 4);
        FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, "TitleCommand", 4);

        Button bt = new Button(icon1);
        Style butStylebn = bt.getAllStyles();

        butStylebn.setFgColor(0x000000);
        Button bt1 = new Button(icon2);
        Style butStylebbn = bt1.getAllStyles();

        Button bt2 = new Button(icon3);

        butStylebbn.setFgColor(0x000000);

        // Label name = new Label(imgserv);
        Label nomp = new Label("Nom");
        Label refp = new Label("Référence");
        Label descriptionp = new Label("description");
        Label quntiterp = new Label("Quantiter");
        Label prixp = new Label("Prix");
        Label marquep = new Label("Marque");
        Label categoriep = new Label("Catégorie");

        Label nom = new Label("");
        Label ref = new Label("");
        Label description = new Label("");
        Label quntiter = new Label("");
        Label prix = new Label("");
        Label marque = new Label("");
        Label categorie = new Label("");

        // name.getAllStyles().setBgTransparency(0);
        //name.getAllStyles().setFgColor(0xB1D7E8);
        // name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        //email.getAllStyles().setBgTransparency(0);
        nom.getAllStyles().setFgColor(0x000000);
        description.getAllStyles().setFgColor(0x000000);
        quntiter.getAllStyles().setFgColor(0x000000);
        prix.getAllStyles().setFgColor(0x000000);

        // b=plat;
        Container ccc1 = BoxLayout.encloseY(refp, nomp, marquep, categoriep, quntiterp, prixp, descriptionp);
        Container ccc2 = BoxLayout.encloseY(ref, nom, marque, categorie, quntiter, prix, description);

        Container cnt = BoxLayout.encloseX(ccc1, ccc2);
        cnt.getStyle().setBgColor(0xE5F2FE);
        cnt.getStyle().setBgTransparency(255);

        //cnt.setScrollVisible(true);
        Style st = cnt.getAllStyles();
        st.setMargin(Component.BOTTOM, 2);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_MITER, 1);

        ref.setText(p.getReference());
        nom.setText(p.getNom());
        marque.setText(p.getMarque());
        CategorieService categorieService = new CategorieService();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Categorie> listc = categorieService.getList();
        String c = null;
        //System.out.println("CATZGORIE ID"+p.getId_categorie());
        for (int i = 0; i < listc.size(); i++) {
            if (listc.get(i).getId() == p.getId_categorie()) {
                c = listc.get(i).getNom().toString();
            }

        }
        //System.out.println("CATZGORIE NOM "+c);

        categorie.setText(c);
        quntiter.setText(p.getQuantite() + " Unité");
        prix.setText(p.getPrix() + " DT");
        description.setText(p.getDescription());

        Container c4 = BoxLayout.encloseX(bt, bt2, bt1);
        bt1.addActionListener(e -> {

            if (Dialog.show("suppression", "Voulez-vous supprimer cette article?", "Ok", "Annuler")) {
                ProduitService sp = new ProduitService();
                sp.SupprimerProduit(p.getId());
                c4.revalidate();
                listenew = produitService.getStockPartenaire(user.getId());
                new listeProduitB(listenew).show();
            }

        });

        bt.addActionListener(e -> {
            b = p;
            System.out.println(p.getId());
            b.setId(p.getId());
            b.setPartenaire(user.getId());

            new modifierProduit(current).show();
        });

        bt2.addActionListener(e -> {
            b = p;
            b = p;
            System.out.println(p.getId());
            b.setId(p.getId());
            b.setPartenaire(user.getId());
            new detailProduitB(current).show();

        });

        /**
         * ************************
         */
        return BorderLayout.center(cnt).
                add(BorderLayout.EAST, c4);
    }

}
