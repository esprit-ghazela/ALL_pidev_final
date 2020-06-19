/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

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

/**
 *
 * @author ACER
 */
public class modifierannonce extends Form{
       Form current;
  String l="";
  
    public modifierannonce(Form previous) {
   
        current=this;
        Style eventStyle= getAllStyles();
        eventStyle.setBgColor(0x6495ED);
        setTitle("Annonce");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new afficherannonce(current).show();});
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
                    Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
  /*******************************************************************************************/
            Label a = new Label("modifier annonce");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label titre  =new Label("nom :");
         TextField titre1  =new TextField(afficherannonce.b.getNom());
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

        Label image =new Label("type:");
         TextArea image1  =new TextArea(afficherannonce.b.getImage());
        image.getAllStyles().setMargin(Component.BOTTOM,120);

        image1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyle=image1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        image.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   image1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   image1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
   image.getAllStyles().setFgColor(0x000000);
image1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

        Label dated  =new Label("date :");
         
         Picker date1 = new Picker();
      date1.setType(Display.PICKER_TYPE_DATE);
        dated.getAllStyles().setMargin(Component.BOTTOM,120);
date1.setText(afficherannonce.b.getDate());
        date1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyled=date1.getAllStyles();
        butStyled.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        dated.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   date1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   dated.getAllStyles().setFgColor(0x000000);
date1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/

       Label description  =new Label("description :");
         TextField description1 =new TextField(afficherannonce.b.getDescription());
        description.getAllStyles().setMargin(Component.BOTTOM,120);

        description1.getAllStyles().setMargin(Component.BOTTOM,2);

        Style butStylee2=description1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   description.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   description.getAllStyles().setFgColor(0x000000);
description.getAllStyles().setFgColor(0x000000);


    /**************************************************************************************************/             
                        Button valider = new Button("modifier");
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
Container c1=BoxLayout.encloseY(titre,image,dated,description);

Container c3=BoxLayout.encloseY(titre1,image1,date1,description1);


setScrollableY(false);

cnt3.add(valider);
Container c4=BoxLayout.encloseX(c1,c3);

c.getAllStyles().setMargin(Component.BOTTOM,150);
c4.getAllStyles().setMargin(Component.TOP,10);
c4.getAllStyles().setMargin(Component.LEFT,50);
c4.getAllStyles().setMargin(Component.RIGHT,50);
c4.getStyle().setBgColor(0x0000FF);
c4.getStyle().setBgTransparency(255);

                
 valider.addActionListener(e->{
 afficherannonce.b.setNom(titre1.getText());         
 afficherannonce.b.setImage(image1.getText());
 afficherannonce.b.setDescription(description1.getText());
 String result = StringUtil.replaceAll(date1.getText(), "/", "-");
String g=date1.getText();
  afficherannonce.b.setDate(result);

  
if(AnnonceService.getInstance().modifierAnnonce(afficherannonce.b,60))
{                            Dialog.show("Success","annonce modifié ",new Command("OK"));


} else                             Dialog.show("ERROR", "Server error", new Command("OK"));

});                      
addAll(c4,cnt3);
    
}
}
