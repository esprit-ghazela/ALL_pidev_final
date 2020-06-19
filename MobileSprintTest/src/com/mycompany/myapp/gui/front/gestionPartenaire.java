        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.Switch;
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
import static com.mycompany.myapp.gui.front.SignUpForm.userInsc;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yousra
 */
public class gestionPartenaire extends Form {

    Form current;
    String l = "";
    Form previous = Display.getInstance().getCurrent();
    User user = SignInForm.recupUser;
    int enabled = 1;

    public gestionPartenaire(Form previous) {
        current = this;

        Style pStyle = getAllStyles();
        pStyle.setBgColor(0x9ACCFF);
        setLayout(BoxLayout.y());

        getToolbar().addMaterialCommandToLeftBar("",
                FontImage.MATERIAL_ARROW_BACK, ev -> {
                    previous.showBack();
                });

        setTitle("Partenaire");

        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });

        getToolbar().addMaterialCommandToLeftBar("List",
                FontImage.MATERIAL_BACKUP, ev -> {
                    new listePartenaire(current).show();
                });

        /**
         * ********************************************************************************
         */
        Label a = new Label("Ajouter Partenaire");
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
        Label nom = new Label("Nom");
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
        TextField prenom1 = new TextField("", "Prénom", 20, TextArea.ANY);
        Label prenom = new Label("Prénom");
        prenom1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleep = prenom1.getAllStyles();
        butStyleep.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        prenom1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        prenom1.getAllStyles().setFgColor(0x000000);

        /**
         * ***********************************************************************************************
         */
        TextField username1 = new TextField("", "Nom d'utilsateur", 20, TextArea.ANY);
        Label username = new Label("Nom d'utilsateur");
        username1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleeu = username1.getAllStyles();
        butStyleeu.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        username1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        username1.getAllStyles().setFgColor(0x000000);

        /**
         * ***********************************************************************************************
         */
        TextField email1 = new TextField("", "E-Mail", 20, TextArea.ANY);
        Label email = new Label("E-Mail");
        email1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleee = email1.getAllStyles();
        butStyleee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        email1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        email1.getAllStyles().setFgColor(0x000000);

        /**
         * ***********************************************************************************************
         */
        TextField password1 = new TextField("", "Mot de passe", 20, TextArea.PASSWORD);
        Label password = new Label("Mot de passe");
        password1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleepw = password1.getAllStyles();
        butStyleepw.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        password1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        password1.getAllStyles().setFgColor(0x000000);

        /**
         * ***********************************************************************************************
         */
        TextField cpassword1 = new TextField("", "Confiremer mot de passe", 20, TextArea.PASSWORD);
        Label cpassword = new Label("Confiremer mot de passe");
        cpassword1.getAllStyles().setMargin(Component.BOTTOM, 2);
        Style butStyleepwc = cpassword1.getAllStyles();
        butStyleepwc.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        cpassword1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        cpassword1.getAllStyles().setFgColor(0x000000);

        /**
         * ***********************************************************************************************
         */
        Label status = new Label("Satus");

        Switch switchb = new Switch();
        Label state = new Label("Compte partenaire activer");
        state.getAllStyles().setFgColor(0x008000);
        switchb.setComponentState(true);
        switchb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                boolean c = (boolean) switchb.getComponentState();
                if (c) {
                    state.setText("Compte partenaire activer");
                    enabled = 1;
                    state.getAllStyles().setFgColor(0x008000);
                } else {
                    state.setText("Compte partenaire désactiver");
                    enabled = 0;
                    state.getAllStyles().setFgColor(0xFF0000);
                }

            }
        });

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

        Container c1 = BoxLayout.encloseY(nom, nom1,
                prenom, prenom1,
                username, username1,
                email, email1,
                password, password1,
                cpassword, cpassword1,
                status, switchb, state
        );

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
            String role = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
            String nomu = nom1.getText();
            String prenomu = prenom1.getText();
            String usern = username1.getText();
            String passw = password1.getText();
            String confpw = cpassword1.getText();
            String mail = email1.getText();

            Validator validatorNom = new Validator();
            RegexConstraint nomConstraint = new RegexConstraint("^[(a-z-A-z)]", "Nom Invalide");
            validatorNom.addConstraint(nom, nomConstraint);

            Validator validatorPrenom = new Validator();
            RegexConstraint PrenomConstraint = new RegexConstraint("^[(a-z-A-z)]", "Prénom Invalide");
            validatorNom.addConstraint(prenom, PrenomConstraint);

            if (usern == "" || passw == "" || confpw == "" || nomu == "" || prenomu == "" || mail == "") {
                Dialog.show("Attention", "Veuiller remplir tous les champ", "OK", null);
            } else if (!passw.equals(confpw)) {
                Dialog.show("Attention", "Les mot de passe ne corresponde pas", "OK", null);
            } else if (usern.length() < 2) {
                Dialog.show("Attention", "Le nom d'utilisateur doit contenir au moins 2 caractére ", "OK", null);
            } else if (nomu.length() < 3) {
                Dialog.show("Attention", "Le nom doit contenir au moins 3 caractére ", "OK", null);
            } else if (prenomu.length() < 3) {
                Dialog.show("Attention", "Le prénom doit contenir au moins 3 caractére ", "OK", null);
            } else if (passw.length() < 3) {
                Dialog.show("Attention", "Le mot de passe doit contenir au moins 4 caractére ", "OK", null);
            } else if (!validatorNom.isValid()) {
                ToastBar.showErrorMessage("Vérifier nom");
                Dialog.show("Attention", "Nom invalide  ", "OK", null);
            } else if (!validatorPrenom.isValid()) {
                ToastBar.showErrorMessage("Vérifier prénom");
                Dialog.show("Attention", "Nom invalide  ", "OK", null);
            } else {
                User u = new User();
                u.setNom(nom.getText());
                u.setPrenom(prenomu);
                u.setUsername(usern);
                u.setEmail(mail);
                u.setEnabled(1);
                u.setPassword(passw);
                u.setRole(role);
                userInsc = u;
                UserService userService = new UserService();
                userService.inscriptionUser(u);
                new listePartenaire(current).show();
            
            }

        });
        
    }

}
