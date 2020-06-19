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
import com.mycompany.myapp.services.ClubService;

/**
 *
 * @author Ahmed laifi
 */
public class modifierClub extends Form{
       Form current;
  String l="";
  
    public modifierClub(Form previous) {
   
        current=this;
        Style eventStyle= getAllStyles();
        eventStyle.setBgColor(0x1876CA);
        setTitle(" Club");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new afficheClub(current).show();});
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
  /*******************************************************************************************/
            Label a = new Label("modifier Club");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x1876CA);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label titre  =new Label("nom club :");
         TextArea titre1  =new TextArea(afficheClub.b.getNom());
        titre.getAllStyles().setMargin(Component.BOTTOM,120);

        titre1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyle=titre1.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        titre.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   titre1.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   titre1.getAllStyles().setFont(Font.createSystemFont(TOP, TOP, 500));
   titre.getAllStyles().setFgColor(0x000000);
titre1.getAllStyles().setFgColor(0x000000);
/***********************************************************************************/
      
        Label desc  =new Label("fondateur :");
         TextArea desc1  =new TextArea(afficheClub.b.getFondateur());
        desc.getAllStyles().setMargin(Component.BOTTOM,120);

        desc1.getAllStyles().setMargin(Component.BOTTOM,7);

        Style butStyle1=desc1.getAllStyles();
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
date1.setText(afficheClub.b.getDate_creation());
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


    /**************************************************************************************************/             
                        Button valider = new Button("modifier");
Style butStyleb=valider.getAllStyles();
butStyleb.setBorder(RoundRectBorder.create().
        strokeColor(0x1876CA).
        strokeOpacity(120).
        stroke(borderStroke));
butStyleb.setBgColor(0xA0522D);
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
Container c1=BoxLayout.encloseY(titre,desc,dated);

Container c3=BoxLayout.encloseY(titre1,desc1,date1);


setScrollableY(false);

cnt3.add(valider);
Container c4=BoxLayout.encloseX(c1,c3);

c.getAllStyles().setMargin(Component.BOTTOM,150);
c4.getAllStyles().setMargin(Component.TOP,10);
c4.getAllStyles().setMargin(Component.LEFT,50);
c4.getAllStyles().setMargin(Component.RIGHT,50);
c4.getStyle().setBgColor(0x1876CA);
c4.getStyle().setBgTransparency(255);

                
      valider.addActionListener(e->{
 afficheClub.b.setNom(titre1.getText());         
afficheClub.b.setFondateur(desc1.getText());
String result = StringUtil.replaceAll(date1.getText(), "/", "-");
 String g=date1.getText();
  afficheClub.b.setDate_creation(result);
 

  
if(ClubService.getInstance().modifierClub(afficheClub.b))
{                            Dialog.show("Success","evenement modifié ",new Command("OK"));


} else                             Dialog.show("ERROR", " Server error", new Command("OK"));

});                      
addAll(c4,cnt3);
/***********************************************************************************/
  /*******************************************************************************************/       
         /*********************************/
          


    }
}
