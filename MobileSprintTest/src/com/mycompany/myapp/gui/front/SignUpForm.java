/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends Form {

    public static User userInsc;

    public SignUpForm() {
        super(new BorderLayout());
        Resources res = UIManager.initNamedTheme("/theme", "Theme");

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "Nom d'utilisateur", 20, TextField.ANY);
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prénom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm mot de passe", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Inscription"); //SUIVANT
        Button signIn = new Button("Connecter vous");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déja un compte ?");

        Container content = BoxLayout.encloseY(
                new Label("Inscription", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {
            String role = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
            String nomu = nom.getText();
            String prenomu = prenom.getText();
            String usern = username.getText();
            String passw = password.getText();
            String confpw = confirmPassword.getText();
            String mail = email.getText();
            int enabled = 1;
            
            Validator validatorEmail = new Validator();
            RegexConstraint emailConstraint = new RegexConstraint("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$", "Address Email Invalide");
            validatorEmail.addConstraint(email, emailConstraint);
            
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
            }
            else if (passw.length() < 3) {
                Dialog.show("Attention", "Le mot de passe doit contenir au moins 4 caractére ", "OK", null);
            } 
            
           else if (!validatorNom.isValid()) {
                 ToastBar.showErrorMessage("Vérifier nom");
                 Dialog.show("Attention", "Nom invalide  ", "OK", null);
            } else if (!validatorPrenom.isValid()) {
                 ToastBar.showErrorMessage("Vérifier prénom");
                 Dialog.show("Attention", "Nom invalide  ", "OK", null);
            } 
            
            else {
                String mdp = BCrypt.hashpw(passw, BCrypt.gensalt(13));
                mdp = mdp.substring(4, mdp.length());
                mdp = "$2y$"+mdp ;
                User u = new User();
                u.setNom(nom.getText());
                u.setPrenom(prenomu);
                u.setUsername(usern);
                u.setEmail(mail);
                u.setEnabled(1);
                u.setPassword(mdp);
                u.setRole(role);
                userInsc = u;
                UserService userService = new UserService();
                userService.inscriptionUser(u);
                new SignInForm().show();
                //new ActivateForm().show();
            }
        });

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

}
