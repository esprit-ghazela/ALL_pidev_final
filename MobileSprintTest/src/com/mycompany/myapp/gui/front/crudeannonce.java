/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.mycompany.myapp.entitie.Annonce;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.services.AnnonceService;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

/**
 *
 * @author ACER
 */
public class crudeannonce extends Form{
       Form current;
  String l="";
  
    public crudeannonce(Form previous) {
       current=this;

        AnnonceService s = new AnnonceService();
     
        
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new acceuil(current).show();});
         setTitle("annonce");
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("affichage", 
                FontImage.MATERIAL_BACKUP, ev->{new afficherannonce(previous).show();});
                     Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
/***********************************************************************************/
              Label a = new Label("Ajouter Annonce");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label description  =new Label("description :");
         TextField description1  =new TextField();
        description.getAllStyles().setMargin(Component.BOTTOM,120);

        description1.getAllStyles().setMargin(Component.BOTTOM,2);

        Style butStylee=description1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   description1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   description.getAllStyles().setFgColor(0x000000);
description1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

       /* Label categorie  =new Label("categorie :");
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

        Label image  =new Label("type");
         TextArea image1  =new TextArea();
        image.getAllStyles().setMargin(Component.BOTTOM,80);

        image1.getAllStyles().setMargin(Component.BOTTOM,7);

      
        image.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   image1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   image.getAllStyles().setFgColor(0x000000);
image1.getAllStyles().setFgColor(0x000000);
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
Container c1=BoxLayout.encloseY(description,nom,image,dated);

Container c3=BoxLayout.encloseY(description1,nom1,image1,date1);


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
Annonce r=new Annonce();
r.setDescription(description1.getText());
r.setNom(nom1.getText());
r.setImage(image1.getText());
String result = StringUtil.replaceAll(date1.getText(), "/", "-");

r.setDate(result);




AnnonceService rec= new AnnonceService();
int id=r.getId_annonce();
rec.addAnnonce(r,id);
Dialog.show("Success","ajout avec succée ",new Command("OK"));
    System.out.println(r);
    LocalNotification ln = new LocalNotification();
            ln.setId("LnMessage");
            ln.setAlertTitle("salut");
            ln.setAlertBody("votre annonce est recu avec succé!");
          Display.getInstance().scheduleLocalNotification( ln,
                System.currentTimeMillis() + 10 * 10, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency);
          );
                Dialog.show("Succès", "Une notification sera envoyée ", "Ok", null);


});
    }
    
    
    
}
