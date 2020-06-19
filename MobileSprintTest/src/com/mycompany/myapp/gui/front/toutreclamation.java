/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import  com.mycompany.myapp.entitie.Reclamation;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.services.ReclamationService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class toutreclamation  extends Form {
   Form current;
   Image imgs;
ArrayList<Reclamation> list2=new ArrayList<>();
    public toutreclamation() {
    }
    
    public toutreclamation(Form previous) {
      current=this;
      Style eventStyle= getAllStyles();
        eventStyle.setBgColor(0x6495ED);
        ReclamationService s = new ReclamationService();
      List data0= s.getid_reclamation();
      List data= s.getnom();
      List data2= s.getprenom();
      List data3=s.getdate();
      List data4=s.getreclamation();
     
        setTitle("Reclamation");
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new acceuil(current).show();});
          getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
                 Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
                        TextField Username = new TextField("", "Rechercher");
                        Style userStyle = Username.getAllStyles();
Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
userStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120));
userStyle.setBgColor(0xffffff);
userStyle.setBgTransparency(255);
userStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
userStyle.setMargin(Component.BOTTOM, 3);       
          userStyle.setMargin(Component.TOP,1); 
          current.add(Username);
          for(int i=0; i<data.size(); i++){
  /**********************************************************************************/  
             Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Container c6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            // Label a=new Label("date debut");
             Label titre = new Label(data.get(i).toString());
              
               
               
              Label k=new Label("nom");
                Label kk=new Label("date");
               Label nombre = new Label(data3.get(i).toString());
               Label a=new Label(data.get(i).toString());
               Label b=new Label("prenom:");
               Label d=new Label(data2.get(i).toString());
               c4.add(titre);
  
  Style aStyle=k.getAllStyles();
  aStyle.setFgColor(0x000000);
  Style bStyle=b.getAllStyles();
  bStyle.setFgColor(0x000000);
  Style dStyle=kk.getAllStyles();
  dStyle.setFgColor(0x000000);
  Style titreStyle=titre.getAllStyles();
  Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
  titreStyle.setFont(largeBoldSystemFont);
  titreStyle.setFgColor(0x000000);
  titreStyle.setMargin(Component.TOP,10); 
  titreStyle.setMargin(Component.LEFT,450); 
  titreStyle.setMargin(Component.BOTTOM,3);
                
               current.add(c4);
               

/*Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_STRINGS);


datePicker.setDate(new Date());/*



  /**********************************************************************************/      
               
               
   Container c = new Container();
              
   
             c6.add(kk);
             c6.add(nombre);
             c6.add(k);
             c6.add(a);
             c6.add(b);
             c6.add(d);
              c.add(c6);
             
             current.add(c);
 /**********************************************************************************/
            Label l = new Label("Reclamtion:");
            Style lStyle=l.getAllStyles();
  lStyle.setFgColor(0x000000);
            SpanLabel sp = new SpanLabel();
            sp.setText(data4.get(i).toString());
        Style spStyle=sp.getAllStyles();
  Font largeBoldSystemFontt = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
  spStyle.setFont(largeBoldSystemFontt);
  spStyle.setFgColor(0x000000);
  spStyle.setMargin(Component.TOP,10); 
  spStyle.setMargin(Component.LEFT,2); 
  spStyle.setMargin(Component.BOTTOM,3);
               current.add(l);
              current.add(sp);
 
          
           
             
            /**********************************************************************************/ 
          }
          


    }
      
}
