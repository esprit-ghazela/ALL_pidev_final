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
import com.codename1.messaging.Message;
import com.codename1.sendgrid.SendGrid;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.UserService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends Form {

    Form current;
    Resources t;
    public static User recupUser;

    public SignInForm() {

        super(new BorderLayout());
        current = this;
        Resources res = UIManager.initNamedTheme("/theme", "Theme");
  
        
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

        add(BorderLayout.NORTH, new Label(res.getImage("log.png"), "LogoLabel"));

        TextField username = new TextField("", "Nom d'utilisateur", 20, TextField.ANY);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Se connecter");

        signIn.addActionListener(new ActionListener() {
            User user = new User();

            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService userService = new UserService();
                System.out.println(username.getText() + "/n" + password.getText());
                //userService.check(username.getText(), password.getText());
                // = userService.check(usern,pass) ;

                signIn.addActionListener(e -> {

                    String passswordText = password.getText();
                    String usernameText = username.getText();
                    //userService.rechercher(usernameText);
                    // signIn.requestFocus();
                    String mdp = BCrypt.hashpw(passswordText, BCrypt.gensalt(13));
                    //mdp = mdp.replaceFirst("2a", "2y");
                    // System.out.print("CHECK : "+test);
                    //String mdp = userService.cryptPassword(password.getText());

                    user = userService.getUser(username.getText().toString());
                    recupUser = user;

                    String motDePasseBD = user.getPassword();
                    char[] tab = motDePasseBD.toCharArray();
                    tab[2] = 'a';
                    motDePasseBD = String.valueOf(tab);
                    boolean c = BCrypt.checkpw(passswordText, motDePasseBD);
                    System.out.println("CH 1 /:" + c);

                    System.out.println(usernameText.equals(user.getUsername()));
                    System.out.println("Username : " + user.getUsername());
                    System.out.println("Username : " + usernameText);
                    System.out.println("Passsword: " + user.getPassword());
                    System.out.println("Passsword: " + mdp);

                    if (usernameText.equals(user.getUsername())) {

                        if (BCrypt.checkpw(passswordText, motDePasseBD)) {
                            if (user.getEnabled() == 0) {
                                Dialog.show("Attention", "Ce compte a été désactiver", "OK", null);
                            } else {
                                if (user.getRole().equals("[ROLE_CLIENT, ROLE_USER]")) {
                                    System.out.println("Vous ete connecter en tant que client");
                                    new acceuilF().show();
                                } else if (user.getRole().equals("[ROLE_ADMIN, ROLE_USER]")) {
                                    System.out.println("Vous ete connecter en tant que client");
                                    Dialog.show("Erreur", "Impossible de ce conecter a ce compte", "Ok", null);
                                } else {
                                    t = UIManager.initNamedTheme("/theme", "Theme1");
                                    Toolbar.setGlobalToolbar(false);
                                    new acceuil(current).show();
                                    Toolbar.setGlobalToolbar(true);
                                    System.out.println("Vous ete connecter en tant que " + user.getRole());
                                    sendMail() ;
                                }
                            }
                        }
                    } else {
                        t = UIManager.initNamedTheme("/theme", "Theme1");
                        Toolbar.setGlobalToolbar(false);
                        //new WalkthruForm(t).show();
                        Toolbar.setGlobalToolbar(true);
                        //Dialog.show("Attention", "Verifier vos coordonnées", "OK", null);
                    }

                }
                );

                //userService.sendEmail() ;
            }
        });

        Button signUp = new Button("Inscription");
        signUp.addActionListener(e -> new SignUpForm().show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);

        getToolbar().addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });

    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public void sendMail() {
 
        /*SendGrid s = SendGrid.create("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");
        s.sendSync("yousra.abid@esprit.tn", "velotunisie5@gmail.com", "test", "test");*/
        
        Message m = new Message(" Je vous informe que je peux pas assister à l'évenement"+" "+"donc je vais annuler ma participation,cordialement");
                String textAttachmentUri = null;
                m.getAttachments().put(textAttachmentUri, "text/plain");
                String imageAttachmentUri = null;
                m.getAttachments().put(imageAttachmentUri, "image/png");
                Display.getInstance().sendMessage(new String[] {"velotunisie@outlook.com"}, "Annulation particpation", m);



    }

}
