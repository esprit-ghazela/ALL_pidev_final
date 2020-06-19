/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.mycompany.myapp.entitie.Reclamation;
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
import com.mycompany.myapp.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class affichereclamation extends Form{
    Form current;
        
        static Reclamation b=new Reclamation();

             
             ArrayList<Reclamation> list2=new ArrayList<>();

   public affichereclamation(Form previous) {
      current=this;
    
        setTitle("reclamation");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new crudreclamation(current).show();});
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
              getTitleArea().setUIID("Container");
        setUIID("SignIn");
         /**************/
         /*******************************************************************************************/
            Label a = new Label("Vos reclamations");
         a.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        a.getAllStyles().setFgColor(0x0000FF);
        Container c=new Container(new FlowLayout(Container.CENTER));
        c.add(a);
        c.getAllStyles().setMargin(Component.TOP,70);
        add(c);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
/***********************************************************************************/
         ArrayList<Reclamation> liste=ReclamationService.getInstance().getAllReclamation();

  
         Style s3=getAllStyles();
  
      
            /*************************************/
                           Container listss = new Container(BoxLayout.y());
   
                           
        Container lists = new Container(BoxLayout.y());
           for(Reclamation c1 : liste) {
            listss.add(createCoursContainer(c1));
        }
      /*************************/

        //listss.setScrollableY(true);
          
            Style st=lists.getAllStyles();
        st.setMargin(Component.BOTTOM,900);

        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
                      t.setUIID("Tab");
                     
        t.addTab("reclamation", listss);
t.setScrollableY(true);

         add(t);
       

        
        
                 
     // lists.setScrollableY(true);
       
  
        }
        private Container createCoursContainer(Reclamation plat) {
                
            Button bt=new Button("modifier");
            Style butStylebn=bt.getAllStyles();

butStylebn.setFgColor(0x000000);
                Button bt1=new Button("X");
                Style butStylebbn=bt1.getAllStyles();

butStylebbn.setFgColor(0x000000);

           // Label name = new Label(imgserv);
               Label reclamation1 = new Label("Reclamation");
            Label nom1 = new Label("nom");
            Label prenom1 = new Label("prenom");
            Label date1 = new Label("date"); 
      
           
            Label reclamation = new Label("");
            Label nom = new Label("");
            Label prenom = new Label("");
            Label date = new Label("");
            
            
           
           

            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           cnt.getStyle().setBgColor(0x0000FF);
        cnt.getStyle().setBgTransparency(255);
           // name.getAllStyles().setBgTransparency(0);
        //name.getAllStyles().setFgColor(0xB1D7E8);
          // name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            //email.getAllStyles().setBgTransparency(0);
        nom1.getAllStyles().setFgColor(0x000000);
        prenom1.getAllStyles().setFgColor(0x000000);
        date1.getAllStyles().setFgColor(0x000000);
        reclamation1.getAllStyles().setFgColor(0x000000);
       
      
        
       
       // b=plat;
            cnt.add(nom1);
            cnt.add(nom);
            cnt.add(prenom1);
            cnt.add(prenom);
            cnt.add(date1);
            cnt.add(date);
            cnt.add(reclamation1);
            cnt.add(reclamation);
           


           
           
        //cnt.setScrollVisible(true);
        Style st=cnt.getAllStyles();
        st.setMargin(Component.BOTTOM,2);
        Stroke borderStroke = new Stroke(2, Stroke.CAP_BUTT, Stroke.JOIN_MITER, 1);

         // name.setText(person.getNom()+" "+person.getPrenom() );
            nom.setText(plat.getNom());
            prenom.setText(plat.getPrenom());
            date.setText(plat.getDate());
            reclamation.setText(plat.getReclamation());
           
            
           Container c4=BoxLayout.encloseX(bt,bt1);
  bt1.addActionListener(e->{ 
                list2=ReclamationService.getInstance().supprimerreclamation(plat.getId_reclamation());
           list2=ReclamationService.getInstance().getAllReclamation();
                    Dialog.show("Success", "Voulez-vous supprimer cette reclamation?", new Command("OK"));

        cnt.remove();
        c4.remove();
    });
    
bt.addActionListener(e->{
                b=plat;
                new modifierreclamation(current).show();});
        
            /***************************/
            return BorderLayout.center(cnt).
                add(BorderLayout.EAST,c4);
        }
    
}
