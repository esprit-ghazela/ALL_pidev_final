/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.mycompany.myapp.entitie.Annonce;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.services.AnnonceService;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class afficherannonce extends Form {
        Form current;
        
        static Annonce b=new Annonce();

             
             ArrayList<Annonce> list2=new ArrayList<>();

   public afficherannonce(Form previous) {
      current=this;
  
        setTitle("annonce");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new crudeannonce(current).show();});
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
                    Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
         /**************/
         /*******************************************************************************************/
            Label a = new Label("Vos Annonces");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/
         ArrayList<Annonce> liste=AnnonceService.getInstance().getAllAnnonce();

  
         Style s3=getAllStyles();
  
      
            /*************************************/
                           Container listss = new Container(BoxLayout.y());
   
                           
        Container lists = new Container(BoxLayout.y());
           for(Annonce c1 : liste) {
            listss.add(createCoursContainer(c1));
        }
      /*************************/

        //listss.setScrollableY(true);
          
            Style st=lists.getAllStyles();
        st.setMargin(Component.BOTTOM,900);

        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
                      t.setUIID("Tab");
                     
        t.addTab("annonce", listss);
t.setScrollableY(true);

         add(t);
       

        
        
                 
     // lists.setScrollableY(true);
       
  
        }
        private Container createCoursContainer(Annonce plat) {
                
            Button bt=new Button("modifier");
            Style butStylebn=bt.getAllStyles();

butStylebn.setFgColor(0x000000);
                Button bt1=new Button("X");
                Style butStylebbn=bt1.getAllStyles();

butStylebbn.setFgColor(0x000000);

           // Label name = new Label(imgserv);
               Label description1 = new Label("description");
            Label nom1 = new Label("nom");
            Label image1 = new Label("type");
            Label date1 = new Label("date"); 
      
           
            Label description = new Label("");
            Label nom = new Label("");
            Label image = new Label("");
            Label date = new Label("");
            
            
           
           

            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           cnt.getStyle().setBgColor(0x0000FF);
        cnt.getStyle().setBgTransparency(255);
           // name.getAllStyles().setBgTransparency(0);
        //name.getAllStyles().setFgColor(0xB1D7E8);
          // name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            //email.getAllStyles().setBgTransparency(0);
        nom1.getAllStyles().setFgColor(0x000000);
        image1.getAllStyles().setFgColor(0x000000);
        date1.getAllStyles().setFgColor(0x000000);
        description1.getAllStyles().setFgColor(0x000000);
       
      
        
       
       // b=plat;
            cnt.add(nom1);
            cnt.add(nom);
            cnt.add(image1);
            cnt.add(image);
            cnt.add(date1);
            cnt.add(date);
            cnt.add(description1);
            cnt.add(description);
           


           
           
        //cnt.setScrollVisible(true);
        Style st=cnt.getAllStyles();
        st.setMargin(Component.BOTTOM,2);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_MITER, 1);

         // name.setText(person.getNom()+" "+person.getPrenom() );
            nom.setText(plat.getNom());
            image.setText(plat.getImage());
            date.setText(plat.getDate());
            description.setText(plat.getDescription());
           
            
           Container c4=BoxLayout.encloseX(bt,bt1);
  bt1.addActionListener(e->{ 
                list2=AnnonceService.getInstance().supprimerAnnonce(plat.getId_annonce());
           list2=AnnonceService.getInstance().getAllAnnonce();
                    Dialog.show("Success", "Voulez-vous supprimer cette annonce ?", new Command("OK"));

        cnt.remove();
        c4.remove();
    });
    
bt.addActionListener(e->{
                b=plat;
                new modifierannonce(current).show();});
        
            /***************************/
            return BorderLayout.center(cnt).
                add(BorderLayout.EAST,c4);
        }
    
}
