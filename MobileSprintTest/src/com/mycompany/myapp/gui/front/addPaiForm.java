/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entitie.Commande;
import com.mycompany.myapp.entitie.Methodedepaiement;
import com.mycompany.myapp.entitie.Paiement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServiceMethode;
import com.mycompany.myapp.services.ServicePaiement;

/**
 *
 * @author aziz khbou
 */
public class addPaiForm extends BaseForm {

    public addPaiForm() {
        super("Paiement", BoxLayout.y());
        Resources res = UIManager.initNamedTheme("/theme", "Theme");
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new addComForm().show();});
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        super.addSideMenu(res);

 
       

        Message m = new Message("Paiement confirmé");

        TextField tmail = new TextField("", "Email", 20, TextArea.EMAILADDR);
        TextField tcomp = new TextField("", "Nom de companie", 20, TextArea.ANY);
        TextField tpays = new TextField("", "Pays", 20, TextArea.ANY);
        TextField tdist = new TextField("", "District", 20, TextArea.ANY);

        TextField tcode = new TextField("", "Code Postal", 4, TextArea.NUMERIC);

       ServiceCommande commandeService = new ServiceCommande();
        //selectCategorie = categorieService.getNomCategorie();
        ArrayList<Commande> listc = commandeService.getAllCommandes();
        String[] selectCommande = new String[listc.size()];
        for (int i = 0; i < listc.size(); i++) {
            selectCommande[i] = listc.get(i).getProduit();
        }

        ComboBox<String> comboCommande = new ComboBox<>(selectCommande);
        Label Commande = new Label("Catégorie");
        comboCommande.getAllStyles().setMargin(Component.BOTTOM, 3);
        
       
        
        

        ServiceMethode sm = new ServiceMethode();

        ArrayList<Methodedepaiement> listm = sm.getAllMethodes();
        String[] selectMethode = new String[listm.size()];
        for (int i = 0; i < listm.size(); i++) {
            selectMethode[i] = listm.get(i).getName();
        }

        ComboBox<String> comboMethode = new ComboBox<>(selectMethode);
        Label Methode = new Label("Methode de paiement");
        comboMethode.getAllStyles().setMargin(Component.BOTTOM, 3);
        

        

        Button btn = new Button("Valider Paiement");
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
        String messagep
                = "Paiement validé par le client"  + "\t";
        btn.addActionListener((evt) -> {
             int mt = 0;
            for (int i = 0; i < listm.size(); i++) {
                if (listm.get(i).getName().toString().equals(comboMethode.getSelectedItem())) {
                    mt = listc.get(i).getId();
                }
            }
            int ct = 0;
            for (int i = 0; i < listc.size(); i++) {
                if (listc.get(i).getProduit().toString().equals(comboCommande.getSelectedItem())) {
                    ct = listc.get(i).getId();
                }
            }
            if ((tmail.getText().length() == 0) || (tcode.getText().length() == 0)) {
                Dialog.show("Attention", "Remplir tout les champs", "OK", null);
            } else {
                try {
            

                    Paiement p = new Paiement(tmail.getText(), tcomp.getText(), tpays.getText(),
                            tdist.getText(), Integer.parseInt(tcode.getText()), ct
                            ,mt);

                    if (new ServicePaiement().ajouterPaiement(p)) {
                        Dialog.show("SUCCES", "Paiement validé", "OK", null);
                        new addLivForm().show();
                        String[] recipients = {p.getEmail()};
                        SendMail.sendMessage(recipients, "Paiement envoyé ", m);
                        try {
                            SendSms.sendSMS(messagep);
                            ToastBar.showInfoMessage("message Sent to:\t " + SendSms.to + " successfully");
                        } catch (Exception e) {
                            ToastBar.showErrorMessage(e.getMessage());
                        }
                        
                    } else {
                        Dialog.show("ERREUR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERREUR", "Paiement non valide", "OK", null);
                }

            }
        });
        this.addAll(tmail, tcomp, tpays, tdist, tcode,  comboCommande,comboMethode , btn);
    
    }

}
