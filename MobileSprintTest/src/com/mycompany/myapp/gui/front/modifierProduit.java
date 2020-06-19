/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
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
public class modifierProduit extends Form {

    Form current;
    String l = "";
    String imgName = "";
    ArrayList<Produit> listenew;
    ProduitService produitService = new ProduitService();

    public modifierProduit(Form previous) {

        current = this;
        User user = SignInForm.recupUser;
        Produit p = new Produit();
        Style prodStyle = getAllStyles();
        prodStyle.setBgColor(0x9ACCFF);
        setTitle("produit");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_ARROW_BACK, ev -> {

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
        Label a = new Label("Modifier Produit");
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
        TextField ref1 = new TextField(listeProduitB.b.getReference());
        ref1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStylee = ref1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        ref1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        ref1.getAllStyles().setFgColor(0x000000);

        /**
         * ********************************************************************************
         */
        Button bt = new Button("importer image");
        Label imn = new Label("");
        Label image = new Label("Image :");
        MultipartRequest cr = new MultipartRequest();

        bt.addActionListener((evt) -> {

            String filePath = Capture.capturePhoto();
            String name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.') - 1);
            String ext = filePath.substring(filePath.lastIndexOf('.') - 1, filePath.length());
            name = name + ext;
            System.out.println("rdtfhuol //// :" + name);
            imgName = name;
            imgName = "VELO+16+POUCES+4+5+6+ANS+900+ALU+RACING+ROUGE.jpg";
            imn.setText(listeProduitB.b.getImage());

        });

        /**
         * ********************************************************************************
         */
        Label nom = new Label("Nom :");
        TextField nom1 = new TextField(listeProduitB.b.getNom());
        nom1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyle = nom1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        nom1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        nom1.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */
        Label categorie = new Label("Catégorie :");
        CategorieService categorieService = new CategorieService();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Categorie> listc = categorieService.getList();
        String[] selectCategorie = new String[listc.size()];
        int s = 0;
        for (int i = 0; i < listc.size(); i++) {
            selectCategorie[i] = listc.get(i).getNom();
            if (listc.get(i).getId() == listeProduitB.b.getId_categorie()) {
                s = i;
            }
        }
        ComboBox<String> comboCategorie = new ComboBox<>(selectCategorie);
        comboCategorie.getAllStyles().setMargin(Component.BOTTOM, 2);
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        comboCategorie.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        comboCategorie.getAllStyles().setFgColor(0x000000);
        comboCategorie.setSelectedIndex(s);

        /**
         * ********************************************************************************
         */
        Label marque = new Label("Marque :");
        String[] selectMarque = {"Atala", "Atom Bicycles", "BH Bikes", "Bianchi",
            "Bike by Me", "BMC", "BTwin", "Cannondale Bicycles", "Canyon", "Giant", "Kestrel"};
        ComboBox<String> comboMarque = new ComboBox<>(selectMarque);
        comboMarque.getAllStyles().setMargin(Component.BOTTOM, 2);
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        comboMarque.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        comboMarque.getAllStyles().setFgColor(0x000000);
        comboMarque.setSelectedItem(listeProduitB.b.getMarque());

        /**
         * ********************************************************************************
         */
        Label quantiter = new Label("Quantiter :");
        String q = String.valueOf(listeProduitB.b.getQuantite());
        TextField quantiter1 = new TextField(q);
        quantiter1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleq = quantiter1.getAllStyles();
        butStyleq.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        quantiter1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        quantiter1.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */

        Label prix = new Label("Prix :");
        String prixP = String.valueOf(listeProduitB.b.getPrix());
        TextField prix1 = new TextField(prixP);
        prix1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStylep = prix1.getAllStyles();
        butStylep.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        prix1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        prix1.getAllStyles().setFgColor(0x000000);

        /**
         * ********************************************************************************
         */
        Label desc = new Label("description :");
        TextArea desc1 = new TextArea(listeProduitB.b.getDescription());
        desc1.getAllStyles().setMargin(Component.BOTTOM, 7);
        Style butStyled = desc1.getAllStyles();
        butStyled.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        desc1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        desc1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
        desc1.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */

        Button valider = new Button("modifier");
        valider.setUIID("Button");
        Style butStyleb = valider.getAllStyles();
        butStyleb.setBorder(RoundRectBorder.create().
                strokeColor(0x3399ff).
                strokeOpacity(120).
                stroke(borderStroke));
        butStyleb.setBgColor(0x3399ff);
        butStyleb.setFgColor(0xffffff);
        butStyleb.setBgTransparency(255);
        butStyleb.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyleb.setMargin(Component.BOTTOM, 3);

        butStyleb.setMargin(Component.TOP, 3);
        //cnt5.add(btnval);

        /**
         * ***********************************************************************************************
         */
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));
        /**
         * ********************************************************************************
         */

        //Container c1 = BoxLayout.encloseY(ref, nom,marque,categorie, quantiter, prix, desc);
        Container c3 = BoxLayout.encloseY(ref, ref1,
                nom, nom1,
                image, bt, imn,
                marque, comboMarque,
                categorie, comboCategorie,
                quantiter, quantiter1,
                prix, prix1,
                desc, desc1);

        setScrollableY(true);

        cnt3.add(valider);
        Container c4 = BoxLayout.encloseX(c3);
        
        c.getAllStyles().setMargin(Component.BOTTOM, 150);
        c4.getAllStyles().setMargin(Component.TOP, 10);
        c4.getAllStyles().setMargin(Component.LEFT, 50);
        c4.getAllStyles().setMargin(Component.RIGHT, 50);
        c4.getStyle().setBgColor(0xE5F2FE);
        c4.getStyle().setBgTransparency(255);

        valider.addActionListener(e -> {
            int ct = 0;
            for (int i = 0; i < listc.size(); i++) {
                if (listc.get(i).getNom().toString().equals(comboCategorie.getSelectedItem())) {
                    ct = listc.get(i).getId();
                }
            }

            Validator validatorNom = new Validator();
            RegexConstraint nomConstraint = new RegexConstraint("[(a-zA-Z-0-9)]", "Nom Invalide");
            validatorNom.addConstraint(nom1, nomConstraint);

            Validator validatorPrix = new Validator();
            RegexConstraint PrixConstraint = new RegexConstraint("[(0-9)]", "Prix Invalide");
            validatorNom.addConstraint(prix1, PrixConstraint);

            Validator validatorQuantite = new Validator();
            RegexConstraint QuantiteConstraint = new RegexConstraint("[(0-9)]", "Quantite Invalide");
            validatorNom.addConstraint(quantiter1, QuantiteConstraint);

            if (nom1.getText() == "" || ref1.getText() == "" || desc1.getText() == "" || quantiter1.getText() == "" || prix1.getText() == "") {
                Dialog.show("Attention", "Veuiller remplir tous les champ", "OK", null);
            } else if (nom1.getText().length() < 2) {
                Dialog.show("Attention", "Le nom doit contenir au moins 2 caractére ", "OK", null);
            } else if (ref1.getText().length() < 2) {
                Dialog.show("Attention", "La référence doit contenir au moins 2 caractére ", "OK", null);
            } else if (Integer.parseInt(quantiter1.getText()) == 0) {
                Dialog.show("Attention", "La quantiter doit etre supérieure a 0 ", "OK", null);
            } else if (Float.parseFloat(prix1.getText()) == 0) {
                Dialog.show("Attention", "Le prix doit etre supérieure a 0 ", "OK", null);
            } else if (!validatorNom.isValid()) {
                ToastBar.showErrorMessage("Vérifier nom");
                Dialog.show("Attention", "Nom invalide  ", "OK", null);
            } else if (!validatorPrix.isValid()) {
                ToastBar.showErrorMessage("Vérifier prix");
                Dialog.show("Attention", "Prix invalide  ", "OK", null);
            } else if (!validatorQuantite.isValid()) {
                ToastBar.showErrorMessage("Vérifier quantite");
                Dialog.show("Attention", "Quantite invalide  ", "OK", null);
            } else {

                p.setNom(nom1.getText());
                p.setImage(listeProduitB.b.getImage());
                System.out.println("LOGOFOD /// * " + listeProduitB.b.getImage());
                p.setDescription(desc1.getText());
                p.setMarque(comboMarque.getSelectedItem());
                p.setId_categorie(ct);
                p.setQuantite(Integer.parseInt(quantiter1.getText()));
                p.setPartenaire(listeProduitB.b.getPartenaire());
                p.setPrix(Float.parseFloat(prix1.getText()));
                p.setReference(ref1.getText());
                p.setId(listeProduitB.b.getId());

                if (Dialog.show("modification", "Voulez-vous modifier cette article?", "Ok", "Annuler")) {
                    ProduitService sp = new ProduitService();
                    sp.ModifierProduit(p);
                    listenew = produitService.getStockPartenaire(user.getId());
                    new listeProduitB(listenew).show();
                }
            }
        });
        addAll(c4, cnt3);

        setScrollableY(true);

    }
}
