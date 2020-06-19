/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.mycompany.myapp.entitie.Reclamation;
import com.codename1.capture.Capture;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.services.ReclamationService;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

/**
 *
 * @author ACER
 */
public class crudreclamation extends Form{
     Form current;
  String l="";
  
    public crudreclamation(Form previous) {
       current=this;
     
        ReclamationService s = new ReclamationService();
     
        
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new acceuilF().show();});
         setTitle("reclamation");
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("affichage", 
                FontImage.MATERIAL_BACKUP, ev->{new affichereclamation(previous).show();});
               getTitleArea().setUIID("Container");
        setUIID("SignIn");
/***********************************************************************************/
              Label a = new Label("Ajouter reclamation");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label reclamation  =new Label("reclamation :");
         TextField reclamation1  =new TextField();
        reclamation.getAllStyles().setMargin(Component.BOTTOM,120);

        reclamation1.getAllStyles().setMargin(Component.BOTTOM,2);

        Style butStylee=reclamation1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        reclamation.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   reclamation1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   reclamation.getAllStyles().setFgColor(0x000000);
reclamation1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

        Label categorie  =new Label("categorie :");
         TextArea categorie1  =new TextArea();
        categorie.getAllStyles().setMargin(Component.BOTTOM,120);

        categorie1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyle=categorie1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        categorie.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   categorie1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   categorie1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
   categorie.getAllStyles().setFgColor(0x000000);
categorie1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

        Label dated  =new Label("date");
         
         Picker date1 = new Picker();
      date1.setType(Display.PICKER_TYPE_DATE);
        dated.getAllStyles().setMargin(Component.BOTTOM,120);

        date1.getAllStyles().setMargin(Component.BOTTOM,7);

        
        dated.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   date1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   dated.getAllStyles().setFgColor(0x000000);
date1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

       Label nom  =new Label("nom");
         TextArea nom1  =new TextArea();
     
    

        nom1.getAllStyles().setMargin(Component.BOTTOM,7);

      
        nom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   nom1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   nom.getAllStyles().setFgColor(0x000000);
nom1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

        Label prenom  =new Label("prenom");
         TextArea prenom1  =new TextArea();
        prenom.getAllStyles().setMargin(Component.BOTTOM,80);

        prenom1.getAllStyles().setMargin(Component.BOTTOM,7);

      
        prenom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   prenom1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   prenom.getAllStyles().setFgColor(0x000000);
prenom1.getAllStyles().setFgColor(0x000000);
    /**************************************************************************************************/             
                        Button valider = new Button("valider");
Style butStyleb=valider.getAllStyles();
butStyleb.setBorder(RoundRectBorder.create().
        strokeColor(0x0000FF).
        strokeOpacity(120).
        stroke(borderStroke));
butStyleb.setBgColor(0x0000FF);
butStyleb.setFgColor(0x000000);
butStyleb.setBgTransparency(255);
butStyleb.setMarginUnit(Style.UNIT_TYPE_DIPS);
butStyleb.setMargin(Component.BOTTOM, 3);       

          butStyleb.setMargin(Component.TOP,2);  
              
          butStyleb.setMargin(Component.LEFT,10);  
           butStyleb.setMargin(Component.RIGHT,10); 
                //cnt5.add(btnval);
          

     
  /**************************************************************************************************/   

                Container cnt3=new Container(new FlowLayout(Container.CENTER));
/***********************************************************************************/
Container c1=BoxLayout.encloseY(reclamation,nom,prenom,categorie,dated);

Container c3=BoxLayout.encloseY(reclamation1,nom1,prenom1,categorie1,date1);


setScrollableY(false);

cnt3.add(valider);
Container c4=BoxLayout.encloseX(c1,c3);

c.getAllStyles().setMargin(Component.BOTTOM,150);
c4.getAllStyles().setMargin(Component.TOP,10);
c4.getAllStyles().setMargin(Component.LEFT,50);
c4.getAllStyles().setMargin(Component.RIGHT,50);



                
                        
addAll(c4,cnt3);
/***********************************************************************************/
valider.addActionListener(e->{
Reclamation r=new Reclamation();
r.setReclamation(reclamation1.getText());
r.setCategorie(Integer.parseInt(categorie1.getText()));
r.setNom(nom1.getText());
r.setPrenom(prenom1.getText());
String result = StringUtil.replaceAll(date1.getText(), "/", "-");

r.setDate(result);


NexmoClient client = NexmoClient.builder().apiKey("9fd7786b").apiSecret("1HBxaQvlQxGBuLXO").build();
                          String messageText = "nom: "+nom.getText()+", prenom: "+prenom.getText();
                            TextMessage message = new TextMessage("actualite de jours", "21622998186", messageText);
                            SmsSubmissionResponse response;
                            try {
                                response = client.getSmsClient().submitMessage(message);
                                if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                                    System.out.println("Message sent successfully.");
                                } else {
                                    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
                                }
                            } // Logger.getLogger(AddParascolaire.class.getName()).log(Level.SEVERE, null, ex);
                           catch (NexmoClientException ex) {
                               // Logger.getLogger(AddParascolaire.class.getName()).log(Level.SEVERE, null, ex);
                            }

ReclamationService rec= new ReclamationService();
rec.addreclamation(r,60);
Dialog.show("Success","ajout avec succée ",new Command("OK"));
    System.out.println(r);
    LocalNotification ln = new LocalNotification();
            ln.setId("LnMessage");
            ln.setAlertTitle("salut");
            ln.setAlertBody("votre reclamation est recu avec succé!");
          Display.getInstance().scheduleLocalNotification( ln,
                System.currentTimeMillis() + 10 * 10, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency);
          );
                Dialog.show("Succès", "Une notification sera envoyée ", "Ok", null);


});
    }
    
    
}
