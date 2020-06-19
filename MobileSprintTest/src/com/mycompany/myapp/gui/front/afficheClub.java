/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temclubese file, choose Tools | Temclubeses
 * and open the temclubese in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.myapp.entitie.Club;
import com.mycompany.myapp.services.ClubService;
import java.util.ArrayList;

/**
 *
 * @author Ahmed laifi
 */
public class afficheClub extends Form{
    Form current;
   
        static Club b=new Club();

             
             ArrayList<Club> list2=new ArrayList<>();

   public afficheClub(Form previous) {
      current=this;
      Style eventStyle= getAllStyles();
        eventStyle.setBgColor(0xCD853F);
        setTitle("Club");
        setLayout(BoxLayout.y());
       
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new menuClub().show();});
        Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
/***********************************************************************************/
         ArrayList<Club> liste=ClubService.getInstance().getAllclub();

  
         Style s3=getAllStyles();
   Container listss = new Container(BoxLayout.y());
        Container lists = new Container(BoxLayout.y());
           for(Club c1 : liste) {
            listss.add(createCoursContainer(c1));
        }
      /*************************/
            Style st=lists.getAllStyles();
        st.setMargin(Component.BOTTOM,900);
        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
         t.setUIID("Tab");        
        t.addTab("ma liste", listss);
        t.setScrollableY(true);
         add(t);

        }
   private Container createCoursContainer(Club clubes) {
             
            Button bt=new Button("modifier");
            Style butStylebn=bt.getAllStyles();
        
            Button btm=new Button("affecter");
            Style butStylebnm=btm.getAllStyles();
            
           
butStylebnm.setFgColor(0x000000);   
butStylebn.setFgColor(0x000000);
                Button bt1=new Button("X");
                Style butStylebbn=bt1.getAllStyles();
butStylebbn.setFgColor(0x000000);
          
               Label titre1 = new Label("nom");
            Label description1 = new Label("fodateur");
            Label datedebut1 = new Label("date creation");
      Label solde1 = new Label("solde");

            Label titre = new Label("");
            Label description = new Label("");
            Label datedebut = new Label("");
            Label solde = new Label("");

           

            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           cnt.getStyle().setBgColor(0xFFFFFF);
        cnt.getStyle().setBgTransparency(255);
        titre.getAllStyles().setFgColor(0x000000);
        description.getAllStyles().setFgColor(0x000000);
        datedebut.getAllStyles().setFgColor(0x000000);
        solde.getAllStyles().setFgColor(0x000000);
        
            cnt.add(titre1);
            cnt.add(titre);
            cnt.add(description1);
            cnt.add(description);
            cnt.add(datedebut1);
            cnt.add(datedebut);
            cnt.add(solde1);
            cnt.add(solde);

        Style st=cnt.getAllStyles();
        st.setMargin(Component.BOTTOM,2);


            titre.setText(clubes.getNom());
            description.setText(clubes.getFondateur());
           datedebut.setText(clubes.getDate_creation());
     
        solde.setText("" + clubes.getSolde());
    

           Container c4=BoxLayout.encloseX(bt,bt1);
            bt1.addActionListener(e->{ 
                list2=ClubService.getInstance().supprimerevent(clubes.getId());
                  list2=ClubService.getInstance().getAllclub();
                    Dialog.show("Success", "Voulez-vous supprimer cette club?", new Command("OK"));

                cnt.remove();
              c4.remove();
    });
   btm.addActionListener(e->{ b=clubes ;});
bt.addActionListener(e->{ b=clubes;new modifierClub(current).show(); });
       
            /***************************/
     
          
                   return BorderLayout.center(cnt).
                add(BorderLayout.EAST,c4);
                   
                   
        }

       
   
        

    
}
