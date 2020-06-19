/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.capture.Capture;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entitie.Club;
import com.mycompany.myapp.services.ClubService;
import java.io.IOException;

/**
 *
 * @author Ahmed laifi
 */


public class ajoutclub extends Form{
     Form current;
  String l="";

    boolean imageselected = false;
    public ajoutclub() {
    }
    public ajoutclub(Form previous ) {
       current=this;
      Style eventStyle= getAllStyles();
        eventStyle.setBgColor(0x1876CA);
      ClubService s = new ClubService();
     
        
        setLayout(BoxLayout.y());

         setTitle("Ajouter club");
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new menuClub().show();});
          Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
/***********************************************************************************/
              Label a = new Label("saisir les cordonée du Club");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x1876CA);
        
        
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label titre  =new Label("nom :");
         TextField titre1  =new TextField();
        titre.getAllStyles().setMargin(Component.BOTTOM,120);

        titre1.getAllStyles().setMargin(Component.BOTTOM,2);

        Style butStylee=titre1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        titre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   titre1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   titre.getAllStyles().setFgColor(0x000000);
titre1.getAllStyles().setFgColor(0x000000);

/***********************************************************************************/

        Label desc  =new Label("fondateur :");
         TextArea desc1  =new TextArea();
        desc.getAllStyles().setMargin(Component.BOTTOM,120);

        desc1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyle=desc1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        desc.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   desc1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   desc1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
   desc.getAllStyles().setFgColor(0x000000);
desc1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

        Label dated  =new Label("date creation :");
         
         Picker date1 = new Picker();
      date1.setType(Display.PICKER_TYPE_DATE);
        dated.getAllStyles().setMargin(Component.BOTTOM,120);

        date1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyled=desc1.getAllStyles();
        butStyled.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        dated.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   date1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   dated.getAllStyles().setFgColor(0x000000);
date1.getAllStyles().setFgColor(0x000000);

/***********************************************************************************/

        Label nb  =new Label("solde  :");
         TextArea nb1  =new TextArea();
        nb.getAllStyles().setMargin(Component.BOTTOM,80);

        nb1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStylenb=nb1.getAllStyles();
        butStylenb.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        nb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   nb1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   nb.getAllStyles().setFgColor(0x000000);
nb1.getAllStyles().setFgColor(0x000000);
    /**************************************************************************************************/

                        Button valider = new Button("valider");
Style butStyleb=valider.getAllStyles();
butStyleb.setBorder(RoundRectBorder.create().
        strokeColor(0x1876CA).
        strokeOpacity(120).
        stroke(borderStroke));
butStyleb.setBgColor(0x0B5394);
butStyleb.setFgColor(0x000000);
butStyleb.setBgTransparency(255);
butStyleb.setMarginUnit(Style.UNIT_TYPE_DIPS);
butStyleb.setMargin(Component.BOTTOM, 3);       

          butStyleb.setMargin(Component.TOP,2);  
              
          butStyleb.setMargin(Component.LEFT,10);  
           butStyleb.setMargin(Component.RIGHT,10); 


     
  /**************************************************************************************************/   

                Container cnt3=new Container(new FlowLayout(Container.CENTER));
/***********************************************************************************/
Container c1=BoxLayout.encloseY(titre,desc,dated,nb);

Container c3=BoxLayout.encloseY(titre1,desc1,date1,nb1);


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
Club ee=new Club();
ee.setNom(titre1.getText());

ee.setFondateur(desc1.getText());

String result = StringUtil.replaceAll(date1.getText(), "/", "-");

ee.setDate_creation(result);

ee.setSolde(Integer.parseInt(nb1.getText()));


ClubService ev1= new ClubService();
ev1.addclub(ee);
    System.out.println(ee);


});
    }
    
    
}
