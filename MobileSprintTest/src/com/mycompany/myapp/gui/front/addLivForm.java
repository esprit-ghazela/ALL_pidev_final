/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entitie.Livraison;
import com.mycompany.myapp.entitie.Paiement;

import com.mycompany.myapp.services.ServiceLivraison;
import com.mycompany.myapp.services.ServicePaiement;

import java.util.ArrayList;

/**
 *
 * @author aziz khbou
 */
public class addLivForm extends BaseForm {

    static String etat = "En Attente";

    public addLivForm() {
        super("Livraison", BoxLayout.y());
        Resources res = UIManager.initNamedTheme("/theme", "Theme");
        getToolbar().addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_BACKUP, ev -> {
                    new addComForm().show();
                });
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        super.addSideMenu(res);

        TextField taddr = new TextField("", "Adresse de livraison", 4, TextArea.ANY);
        TextField tetat = new TextField("", "Etat", 20, TextArea.ANY);

        Picker date = new Picker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date.setFormatter(sdf);

        Label lDate = new Label("Date de Livraison:" + date.getText());
        Button btnlivList = new Button("Liste commandes");

        ServicePaiement paiementService = new ServicePaiement();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Paiement> listp = paiementService.getAllPaiements();
        String[] selectPaiement = new String[listp.size()];
        for (int i = 0; i < listp.size(); i++) {
            selectPaiement[i] = listp.get(i).getEmail();
        }

        ComboBox<String> comboPaiement = new ComboBox<>(selectPaiement);
        Label Paiement = new Label("Paiement");
        comboPaiement.getAllStyles().setMargin(Component.BOTTOM, 3);

       Button btn = new Button("Valider livraison");
            Style butStylebpac = btn.getAllStyles();
            Stroke borderStrokepac = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            butStylebpac.setBorder(RoundRectBorder.create().
                    strokeColor(0x008000).
                    strokeOpacity(120).
                    stroke(borderStrokepac));
            butStylebpac.setBgColor(0xFF0000);
            butStylebpac.setFgColor(0x000000);
            butStylebpac.setBgTransparency(255);
            butStylebpac.setMarginUnit(Style.UNIT_TYPE_DIPS);
            butStylebpac.setMargin(Component.BOTTOM, 1);
            butStylebpac.setMargin(Component.TOP, 1);
            butStylebpac.setMargin(Component.LEFT, 10);
            butStylebpac.setMargin(Component.RIGHT, 10);

        btn.addActionListener((evt) -> {
            int pt = 0;
            for (int i = 0; i < listp.size(); i++) {
                if (listp.get(i).getEmail().toString().equals(comboPaiement.getSelectedItem())) {
                    pt = listp.get(i).getId();
                }
            }
            if ((taddr.getText().length() == 0) || (tetat.getText().length() == 0)) {
                Dialog.show("Attention", "Remplir tout les champs", "OK", null);
            } else {
                try {
                    Livraison l = new Livraison(taddr.getText(), etat, date.getDate(),
                            pt);

                    if (new ServiceLivraison().ajouterLivraison(l)) {
                        Dialog.show("SUCCES", "Livraison envoyé", "OK", null);
                    } else {
                        Dialog.show("ERREUR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERREUR", "Livraison non valide", "OK", null);
                }

            }

        });

        this.addAll(taddr, tetat, date, comboPaiement, btn);

    }
}
