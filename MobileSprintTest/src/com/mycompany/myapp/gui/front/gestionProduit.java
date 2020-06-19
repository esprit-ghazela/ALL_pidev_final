        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entitie.Categorie;
import com.mycompany.myapp.entitie.Produit;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yousra
 */
public class gestionProduit extends Form {

    Form current;
    String l = "";
    private String imgPath = "";
    private String imgName = "";
    boolean imageselected = false;
    User user = SignInForm.recupUser;
ArrayList<Produit> listenew;
    ProduitService produitService = new ProduitService();
    public gestionProduit(Form previous) {
        current = this;

        Style eventStyle = getAllStyles();
        eventStyle.setBgColor(0x9ACCFF);
        ProduitService s = new ProduitService();

        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("",
                FontImage.MATERIAL_ARROW_BACK, ev -> {
                    new ecommerceMenu(current).show();
                });
        setTitle("Produit");
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
        getToolbar().addMaterialCommandToLeftBar("List",
                FontImage.MATERIAL_BACKUP, ev -> {
                   
                    listenew = produitService.getStockPartenaire(user.getId());

                    new listeProduitB(listenew).show();
                });

        /**
         * ********************************************************************************
         */
        Label a = new Label("Ajouter produit");
        a.setUIID("Title");
        Container c = new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP, 70);
        add(c);
        Stroke borderStroke = new Stroke(3, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        /**
         * ********************************************************************************
         */

        TextField nom1 = new TextField("", "Nom", 20, TextArea.ANY);

        nom1.getAllStyles().setMargin(Component.BOTTOM, 2);

        Style butStylee = nom1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        nom1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        nom1.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */

        Button bt = new Button("importer image");
        Label imn = new Label("");
        MultipartRequest cr = new MultipartRequest();

        bt.addActionListener((evt) -> {
             
             String filePath = Capture.capturePhoto();
             String name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.') - 1);
             String ext = filePath.substring(filePath.lastIndexOf('.') - 1, filePath.length());
             name = name + ext;
             System.out.println("rdtfhuol //// :" + name);
             imgName = name;

            
            imgName = "VELO+16+POUCES+4+5+6+ANS+900+ALU+RACING+ROUGE.jpg";

        });

        /**
         * ********************************************************************************
         */
        TextArea desc1 = new TextField("", "Description", 20, TextArea.ANY);

        desc1.getAllStyles().setMargin(Component.BOTTOM, 3);

        Style butStyle = desc1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        desc1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        desc1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
        desc1.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */

        TextField ref = new TextField("", "Référence", 20, TextArea.ANY);

        ref.getAllStyles().setMargin(Component.BOTTOM, 3);

        Style butStyled = ref.getAllStyles();
        butStyled.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        ref.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        ref.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
        ref.getAllStyles().setFgColor(0x000000);
        /**
         * ********************************************************************************
         */

        String[] selectMarque = {"Atala", "Atom Bicycles", "BH Bikes", "Bianchi",
            "Bike by Me", "BMC", "BTwin", "Cannondale Bicycles", "Canyon", "Giant", "Kestrel"};

        ComboBox<String> comboMarque = new ComboBox<>(selectMarque);
        Label marque = new Label("Marque ");
        comboMarque.getAllStyles().setMargin(Component.BOTTOM, 3);

        /**
         * ********************************************************************************
         */
        CategorieService categorieService = new CategorieService();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Categorie> listc = categorieService.getList();
        String[] selectCategorie = new String[listc.size()];
        for (int i = 0; i < listc.size(); i++) {
            selectCategorie[i] = listc.get(i).getNom();
        }

        ComboBox<String> comboCategorie = new ComboBox<>(selectCategorie);
        Label categorie = new Label("Catégorie");
        comboCategorie.getAllStyles().setMargin(Component.BOTTOM, 3);

        /**
         * ********************************************************************************
         */
        TextField qt1 = new TextField("", "Quantiter", 20, TextArea.NUMERIC);
        qt1.getAllStyles().setMargin(Component.BOTTOM, 3);
        Style butStylenb = qt1.getAllStyles();
        butStylenb.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        qt1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        qt1.getAllStyles().setFgColor(0x000000);
        /**
         * ***********************************************************************************************
         */

        TextField prix = new TextField("", "Prix", 20, TextArea.NUMERIC);
        prix.getAllStyles().setMargin(Component.BOTTOM, 3);
        Style butStylenb1 = prix.getAllStyles();
        butStylenb1.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        prix.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        prix.getAllStyles().setFgColor(0x000000);
           /**
         * ***********************************************************************************************
         */

        TextField num = new TextField("", "Numéro de téléphone", 20, TextArea.PHONENUMBER);
        num.getAllStyles().setMargin(Component.BOTTOM, 3);
        Style butStynum = num.getAllStyles();
        butStylenb1.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        num.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        num.getAllStyles().setFgColor(0x000000);
        /**
         * ***********************************************************************************************
         */
        Button valider = new Button("valider");
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

        Container c1 = BoxLayout.encloseY(ref, nom1, bt, marque, comboMarque, categorie, comboCategorie, qt1, prix, desc1,num);

        // Container c3 = BoxLayout.encloseY(nom1, bt, desc1,marque,categorie, qt1);
        setScrollableY(true);
        cnt3.add(valider);

        Container c4 = BoxLayout.encloseX(c1);

        c.getAllStyles().setMargin(Component.BOTTOM, 50);
        c4.getAllStyles().setMargin(Component.TOP, 3);
        c4.getAllStyles().setMargin(Component.LEFT, 50);
        c4.getAllStyles().setMargin(Component.RIGHT, 50);
        c4.getStyle().setBgColor(0xE5F2FE);
        c4.getStyle().setBgTransparency(255);

        addAll(c4, cnt3);
        /**
         * ********************************************************************************
         */
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
            validatorNom.addConstraint(prix, nomConstraint);

            Validator validatorQuantite = new Validator();
            RegexConstraint QuantiteConstraint = new RegexConstraint("[(0-9)]", "Quantite Invalide");
            validatorNom.addConstraint(qt1, nomConstraint);

            if (nom1.getText() == "" || imgName == "" || ref.getText() == "" || desc1.getText() == "" || qt1.getText() == "" || prix.getText() == "") {
                Dialog.show("Attention", "Veuiller remplir tous les champ", "OK", null);
            } else if (nom1.getText().length() < 2) {
                Dialog.show("Attention", "Le nom doit contenir au moins 2 caractére ", "OK", null);
            } else if (ref.getText().length() < 2) {
                Dialog.show("Attention", "La référence doit contenir au moins 2 caractére ", "OK", null);
            } else if (Integer.parseInt(qt1.getText()) == 0) {
                Dialog.show("Attention", "La quantiter doit etre supérieure a 0 ", "OK", null);
            } else if (Float.parseFloat(prix.getText()) == 0) {
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

                Produit p = new Produit();

                p.setNom(nom1.getText());
                p.setImage(imgName);
                p.setDescription(desc1.getText());
                p.setMarque(comboMarque.getSelectedItem());
                p.setId_categorie(ct);
                p.setQuantite(Integer.parseInt(qt1.getText()));
                p.setPartenaire(user.getId());
                p.setPrix(Float.parseFloat(prix.getText()));
                p.setReference(ref.getText());

                ProduitService sp = new ProduitService();
                sp.AjouterProduit(p);
                System.out.println(p);

               listenew = produitService.getStockPartenaire(user.getId());

                    new listeProduitB(listenew).show();
                    
                    sp.SendSms(num.getText(),user.getUsername()) ;
            }

        });
    }

}
