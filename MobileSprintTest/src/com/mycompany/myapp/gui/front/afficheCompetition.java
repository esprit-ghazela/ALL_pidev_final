/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entitie.Club;
import com.mycompany.myapp.entitie.Competition;

import com.mycompany.myapp.services.CompService;
import java.util.ArrayList;

/**
 *
 * @author Ahmed laifi
 */
public class afficheCompetition extends Form{
    Form current;
   
        static Competition b=new Competition();

             
             ArrayList<Competition> list2=new ArrayList<>();

   public afficheCompetition(Form previous) {
      current=this;
 
        setTitle("Competition");
        setLayout(BoxLayout.y());
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new menuClubClient().show();});

/***********************************************************************************/
         
         
         
         ArrayList<Competition> liste=CompService.getInstance().allcomp();

  
         
   Container listss = new Container(BoxLayout.y());
        Container lists = new Container(BoxLayout.y());
        
        
      for(Competition c1 : liste) {
            listss.add(createCoursContainer(c1));
        }
           
           
           
      /*************************/
            Style st=lists.getAllStyles();
        st.setMargin(Component.BOTTOM,900);
        Tabs t = new Tabs();
    
         t.setUIID("Tab");        
        t.addTab("Liste des competitions ", listss);
        t.setScrollableY(true);
         add(t);

        }
   private Container createCoursContainer(Competition comps) {
             


          
               Label nomcomp1 = new Label("nom ");
            Label region1 = new Label("region");
            Label datedebut1 = new Label("debut");
            Label datefinal1 = new Label("final");
            Label price1 = new Label("prime");
    
            Label nomcomp = new Label("");
            Label region = new Label("");
            Label datedebut = new Label("");
             Label datefinal = new Label("");
                Label price = new Label("");

            Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
           cnt.getStyle().setBgColor(0xFFFFFF);
        cnt.getStyle().setBgTransparency(255);
        nomcomp.getAllStyles().setFgColor(0x000000);
        region.getAllStyles().setFgColor(0x000000);
        datedebut.getAllStyles().setFgColor(0x000000);
          datefinal.getAllStyles().setFgColor(0x000000);
            price.getAllStyles().setFgColor(0x000000);

            cnt.add(price1);
            cnt.add(nomcomp1);
    
            cnt.add(datedebut1);

             cnt.add(datefinal1);
            cnt.add(region1);
            
    
            nomcomp.setText(comps.getNomcomp());
            region.setText(comps.getRegion());
           datedebut.setText(comps.getDebut());
             datefinal.setText(comps.getDfinal());
            price.setText("" + comps.getPrime());
    

           Container c4=BoxLayout.encloseX(price,nomcomp,datedebut,datefinal,region);
          
 
                   return BorderLayout.north(cnt).add(BorderLayout.EAST,c4);
                   
        }

       
   
        

    
}
