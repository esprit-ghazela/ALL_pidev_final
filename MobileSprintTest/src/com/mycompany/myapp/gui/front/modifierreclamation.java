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
import com.mycompany.myapp.services.ReclamationService;

/**
 *
 * @author ACER
 */
public class modifierreclamation extends Form{
       Form current;
  String l="";
  
    public modifierreclamation(Form previous) {
   
        current=this;
          getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        setTitle(" Reclamation");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new affichereclamation(current).show();});
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
  /*******************************************************************************************/
            Label a = new Label("modifier reclamation");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/

        Label titre  =new Label("nom :");
         TextField titre1  =new TextField(affichereclamation.b.getNom());
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

        Label desc  =new Label("prenom :");
         TextArea desc1  =new TextArea(affichereclamation.b.getPrenom());
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

        Label dated  =new Label("date :");
         
         Picker date1 = new Picker();
      date1.setType(Display.PICKER_TYPE_DATE);
        dated.getAllStyles().setMargin(Component.BOTTOM,120);
date1.setText(affichereclamation.b.getDate());
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

       Label reclamation  =new Label("reclamation :");
         TextField reclamation1 =new TextField(affichereclamation.b.getReclamation());
        reclamation.getAllStyles().setMargin(Component.BOTTOM,120);

        reclamation1.getAllStyles().setMargin(Component.BOTTOM,2);

        Style butStylee2=reclamation1.getAllStyles();
        butStylee.setBorder(RoundRectBorder.create().
                strokeColor(0).
                strokeOpacity(120).
                stroke(borderStroke));
        reclamation.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   reclamation.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
   reclamation.getAllStyles().setFgColor(0x000000);
reclamation.getAllStyles().setFgColor(0x000000);


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
Container c1=BoxLayout.encloseY(titre,desc,dated,reclamation);

Container c3=BoxLayout.encloseY(titre1,desc1,date1,reclamation1);


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
 affichereclamation.b.setNom(titre1.getText());         
 affichereclamation.b.setPrenom(desc1.getText());
 affichereclamation.b.setReclamation(reclamation1.getText());
 String result = StringUtil.replaceAll(date1.getText(), "/", "-");
String g=date1.getText();
  affichereclamation.b.setDate(result);

  
if(ReclamationService.getInstance().modifierreclamation(affichereclamation.b,60))
{                            Dialog.show("Success","reclamation modifié ",new Command("OK"));


} else                             Dialog.show("ERROR", "Server error", new Command("OK"));

});                      
addAll(c4,cnt3);
/***********************************************************************************/
  /*******************************************************************************************/       
         /*********************************/
          

    

    }
    
}
