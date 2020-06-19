/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.Switch;
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
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author yousra
 */
public class detailPartenaire extends Form {

    Form current;
    String l = "";
    Form previous = Display.getInstance().getCurrent();

    public detailPartenaire(Form previous) {

        current = this;

        Produit p = new Produit();
        Style prodStyle = getAllStyles();
        prodStyle.setBgColor(0x9ACCFF);
        setTitle("Partenaire");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_ARROW_BACK, ev -> {
                    previous.showBack();
                });
        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
        /**
         * ****************************************************************************************
         */
        Label a = new Label("Détail Partenaire");
        a.setUIID("Title");
        Container c = new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP, 70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        /**
         * ********************************************************************************
         */

        Label nom = new Label("Nom :");
        Label nom1 = new Label(listePartenaire.us.getNom());

        Label prenom = new Label("Prénom :");
        Label prenom1 = new Label(listePartenaire.us.getPrenom());

        Label username = new Label("Nom d'utilisateur:");
        Label username1 = new Label(listePartenaire.us.getUsername());

        Label email = new Label("Email :");
        Label email1 = new Label(listePartenaire.us.getEmail());

        Label status = new Label("Status :");
        Switch switchb = new Switch();
        Label status1 = new Label("");

        int enabled = listePartenaire.us.getEnabled();
        if (enabled == 1) {
            status1.setText("Activer");
            status1.getAllStyles().setFgColor(0x008000);
            switchb.setComponentState(true);
        } else {
            status1.setText("Désactiver");
            status1.getAllStyles().setFgColor(0xFF0000);
            switchb.setComponentState(false);
        }

        UserService us = new UserService();
        switchb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                boolean cc = (boolean) switchb.getComponentState();
                if (cc) {
                    if (Dialog.show("Activation", "Voulez-vous vraiment activer ce compte ?", "Ok", "Annuler")) {
                        status1.setText("Activer");
                        status1.getAllStyles().setFgColor(0x008000);
                        us.UpdateStatuPartenaire(1, listePartenaire.us.getId());
                        c.revalidate();
                    }

                } else {
                    if (Dialog.show("Désactivation", "Voulez-vous vraiment désactiver ce compte ?", "Ok", "Annuler")) {
                        status1.setText("Désactiver");
                        status1.getAllStyles().setFgColor(0xFF0000);
                        us.UpdateStatuPartenaire(0, listePartenaire.us.getId());
                        c.revalidate();
                        //new listePartenaire(current).show();
                    }
                }
            }
        }
        );

        status.getAllStyles()
                .setMargin(Component.BOTTOM, 7);

        /**
         * ********************************************************************************
         */
        Container cnt3 = new Container(new FlowLayout(Container.CENTER));

        /**
         * ********************************************************************************
         */
        //Container c1 = BoxLayout.encloseY(ref, nom,marque,categorie, quantiter, prix, desc);
        nom1.setUIID(
                "Label2");
        prenom1.setUIID(
                "Label2");
        username1.setUIID(
                "Label2");
        email1.setUIID(
                "Label2");

        Container c3 = BoxLayout.encloseY(
                nom, nom1,
                prenom, prenom1,
                username, username1,
                status, switchb, status1
        );

        setScrollableY(
                true);

        Container c4 = BoxLayout.encloseX(c3);

        c.getAllStyles()
                .setMargin(Component.BOTTOM, 150);
        c4.getAllStyles()
                .setMargin(Component.TOP, 10);
        c4.getAllStyles()
                .setMargin(Component.LEFT, 50);
        c4.getAllStyles()
                .setMargin(Component.RIGHT, 50);
        c4.getStyle()
                .setBgColor(0xE5F2FE);
        c4.getStyle()
                .setBgTransparency(255);

        addAll(c4);

    }
}
