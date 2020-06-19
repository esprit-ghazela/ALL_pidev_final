/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.TOP;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.myapp.entitie.Categorie;
import com.mycompany.myapp.entitie.Produit;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author yousra
 */
public class detailProduitB extends Form {

    Form current;
    String l = "";
    String imgName = "";
    Image imgs;
 User user = SignInForm.recupUser;
ArrayList<Produit> listenew;
    ProduitService produitService = new ProduitService();
    public detailProduitB(Form previous) {

        current = this;

        Produit p = new Produit();
        Style prodStyle = getAllStyles();
        prodStyle.setBgColor(0x9ACCFF);
        setTitle("produit");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_BACKUP, ev -> {
                     listenew = produitService.getStockPartenaire(user.getId());

                    new listeProduitB(listenew).show();
                });
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
        /**
         * ****************************************************************************************
         */
        Label a = new Label("Détail Produit");
        a.setUIID("Title");
        Container c = new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP, 70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        /**
         * ********************************************************************************
         */

        Label ref = new Label("Référence :");
        Label ref1 = new Label(listeProduitB.b.getReference());
      

        /**
         * ********************************************************************************
         */
        Label image = new Label("Image :");
        Label imn = new Label("");
        
        int deviceWidth = 600;
        String urlImage = "http://localhost/pi-dev-master/web/front_template/img/category/"+listeProduitB.b.getImage();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
        imgs = URLImage.createToStorage(enc, urlImage,urlImage, URLImage.RESIZE_SCALE);
        ImageViewer imageV = new ImageViewer(imgs);
        
        imn.setText(listeProduitB.b.getImage());

        /**
         * ********************************************************************************
         */
        Label nom = new Label("Nom :");
        Label nom1 = new Label(listeProduitB.b.getNom());

        /**
         * ********************************************************************************
         */
        Label categorie = new Label("Catégorie :");
        CategorieService categorieService = new CategorieService();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Categorie> listc = categorieService.getList();
        String s = "";
        for (int i = 0; i < listc.size(); i++) {
            if (listc.get(i).getId() == listeProduitB.b.getId_categorie()) {
                s = listc.get(i).getNom();
            }
        }
        Label categorie1 = new Label(s);

        /**
         * ********************************************************************************
         */
        Label marque = new Label("Marque :");
        Label marque1 = new Label(listeProduitB.b.getMarque());

        /**
         * ********************************************************************************
         */
        Label quantiter = new Label("Quantiter :");
        String q = String.valueOf(listeProduitB.b.getQuantite());
        Label quantiter1 = new Label(q);

        /**
         * ********************************************************************************
         */
        Label prix = new Label("Prix :");
        String prixP = String.valueOf(listeProduitB.b.getPrix());
        Label prix1 = new Label(prixP);

        /**
         * ********************************************************************************
         */
        Label desc = new Label("description :");
        Label desc1 = new Label(listeProduitB.b.getDescription());
        desc1.getAllStyles().setMargin(Component.BOTTOM, 7);

        /**
         * ********************************************************************************
         */
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        /**
         * ********************************************************************************
         */

        //Container c1 = BoxLayout.encloseY(ref, nom,marque,categorie, quantiter, prix, desc);
        ref1.setUIID("Label2");
        nom1.setUIID("Label2");
        marque1.setUIID("Label2");      
        categorie1.setUIID("Label2");        
        quantiter1.setUIID("Label2");    
        prix1.setUIID("Label2");
        desc1.setUIID("Label2");  
        
        Container c3 = BoxLayout.encloseY(ref, ref1,
                nom, nom1,
                image, imageV, imn,
                marque, marque1,
                categorie, categorie1,
                quantiter, quantiter1,
                prix, prix1,
                desc, desc1);

        setScrollableY(true);

        Container c4 = BoxLayout.encloseX(c3);

        c.getAllStyles().setMargin(Component.BOTTOM, 150);
        c4.getAllStyles().setMargin(Component.TOP, 10);
        c4.getAllStyles().setMargin(Component.LEFT, 50);
        c4.getAllStyles().setMargin(Component.RIGHT, 50);
        c4.getStyle().setBgColor(0xE5F2FE);
        c4.getStyle().setBgTransparency(255);

        addAll(c4);

    }
}
