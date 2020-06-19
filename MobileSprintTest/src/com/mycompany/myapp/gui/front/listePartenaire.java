/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yousra
 */
public class listePartenaire extends Form {

    Form current;
    User user = SignInForm.recupUser;
    static User us = new User();
    Form previous = Display.getInstance().getCurrent();
    UserService userService = new UserService();

    public listePartenaire(Form previous) {
        current = this;
        Style pStyle = getAllStyles();
        pStyle.setBgColor(0x9ACCFF);

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
        Label a = new Label("Vos Partenaire");
        a.setUIID("Title");
        Container c = new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP, 70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        /**
         * ********************************************************************************
         */
         System.out.println("UsedsfZEFZEfr ");
        
        UserService uss = new UserService();
        ArrayList<User> liste = uss.getListPartenaire();
        Style s3 = getAllStyles();
          for (int i=0;i<liste.size();i++){
        System.out.println("User ");
        System.out.println("Nom : "+liste.get(i).getNom());
        System.out.println("Prénom: "+liste.get(i).getPrenom());
        System.out.println("Username : "+liste.get(i).getUsername());
        System.out.println("Email: "+liste.get(i).getEmail());
        System.out.println("Role: "+liste.get(i).getRole());
        System.out.println("Enabled: "+liste.get(i).getEnabled());
        System.out.println("Password: "+liste.get(i).getPassword());
          }
        /**
         * **********************************
         */
        Container listss = new Container(BoxLayout.y());

        Container lists = new Container(BoxLayout.y());
        for (User uu : liste) {
            {
                System.out.println(uu.getNom());
                listss.add(createContainer(uu));
            }

        }
        /**
         * **********************
         */
          setScrollableY(true);

        listss.setScrollableY(true);
        Style st = lists.getAllStyles();
        st.setMargin(Component.BOTTOM, 900);

        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        t.setUIID("Tab");

        t.addTab("Liste de vos partenaire", listss);
        t.setScrollableY(true);
        add(t);

        // lists.setScrollableY(true);
    }

    private Container createContainer(User u) {

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
  setScrollableY(true);
        // Label name = new Label(imgserv);
        Label nom = new Label("Nom");
        Label prenom = new Label("Prénom");
        Label username = new Label("Nom d'utilisateur");
        Label email = new Label("E-Mail");
        Label status = new Label("Status");

        Label nom1 = new Label("");
        Label prenom1 = new Label("");
        Label username1 = new Label("");
        Label email1 = new Label("");
        Label status1 = new Label("");

        nom1.getAllStyles().setFgColor(0x000000);
        prenom1.getAllStyles().setFgColor(0x000000);
        username1.getAllStyles().setFgColor(0x000000);
        email1.getAllStyles().setFgColor(0x000000);
        status1.getAllStyles().setFgColor(0x000000);

        Container ccc1 = BoxLayout.encloseY(username,username1,email,email1,status,status1);
      
        Container cnt = BoxLayout.encloseX(ccc1);
        cnt.getStyle().setBgColor(0xE5F2FE);
        cnt.getStyle().setBgTransparency(255);

        //cnt.setScrollVisible(true);
        Style st = cnt.getAllStyles();
        st.setMargin(Component.BOTTOM, 4);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_MITER, 2);

        nom1.setText(u.getNom());
        prenom1.setText(u.getPrenom());
        username1.setText(u.getUsername());
        email1.setText(u.getEmail()) ;
        int enabled = u.getEnabled();
        if (enabled == 1) {
            status1.setText("Activer");
        } else {
            status1.setText("Désactiver");
        }

        Container c4 = BoxLayout.encloseX(bt2, bt1);
        bt1.addActionListener(e -> {

            if (Dialog.show("suppression", "Voulez-vous supprimer ce partenaire?", "Ok", "Annuler")) {
                userService.SupprimrePartenaire(u.getId());
                c4.revalidate();
                new listePartenaire(current).show();
            }

        });

        bt.addActionListener(e -> {
            us = u;
             // new modifierPartenaire(current).show();
        });

        bt2.addActionListener(e -> {
            us = u;
            new detailPartenaire(current).show();

        });
        /**
         * ************************
         */
        return BorderLayout.center(cnt).
                add(BorderLayout.EAST, c4);
    }

}
