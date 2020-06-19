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

import com.mycompany.myapp.services.ClubService;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.util.ArrayList;

/**
 *
 * @author Ahmed laifi
 */
public class afficheClubClient extends Form{
    Form current;
   
        static Club b=new Club();

             
             ArrayList<Club> list2=new ArrayList<>();

   public afficheClubClient(Form previous) {
      current=this;

        setTitle("Club");
        setLayout(BoxLayout.y());
         getTitleArea().setUIID("Container");
        setUIID("SignIn");

         getToolbar().addCommandToOverflowMenu("Exit",
            null, ev->{Display.getInstance().exitApplication();});
         getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new menuClubClient().show();});
        
/***********************************************************************************/
         
         
         
         ArrayList<Club> liste=ClubService.getInstance().getAllclub();

  
         
   Container listss = new Container(BoxLayout.y());
        Container lists = new Container(BoxLayout.y());
        
        
      for(Club c1 : liste) {
            listss.add(createCoursContainer(c1));
        }
           
           
           
      /*************************/
            Style st=lists.getAllStyles();
        st.setMargin(Component.BOTTOM,900);
        Tabs t = new Tabs();
    
         t.setUIID("Tab");        
        t.addTab("ma liste", listss);
        t.setScrollableY(true);
         add(t);

        }
   private Container createCoursContainer(Club clubes) {
             
            Button bt1=new Button("participé");
            Style butStylebn=bt1.getAllStyles();
             Button bt2=new Button("trier");
            Style butStylebn2=bt2.getAllStyles();
            butStylebn.setFgColor(0x000000);
              

          
            Label titre1 = new Label("nom");
            Label description1 = new Label("fodateur");
            Label datedebut1 = new Label("date creation");


            Label titre = new Label("");
            Label description = new Label("");
            Label datedebut = new Label("");
     

           

            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           cnt.getStyle().setBgColor(0xFFFFFF);
        cnt.getStyle().setBgTransparency(255);
        titre.getAllStyles().setFgColor(0x000000);
        description.getAllStyles().setFgColor(0x000000);
        datedebut.getAllStyles().setFgColor(0x000000);
     
            cnt.add(titre1);
            cnt.add(titre);
            cnt.add(description1);
            cnt.add(description);
            cnt.add(datedebut1);
            cnt.add(datedebut);


        Style st=cnt.getAllStyles();
        st.setMargin(Component.BOTTOM,2);


            titre.setText(clubes.getNom());
            description.setText(clubes.getFondateur());
           datedebut.setText(clubes.getDate_creation());
     
       
    

           Container c4=BoxLayout.encloseX(bt1,bt2);
           
             bt2.addActionListener(e->{   
             
              new afficheClubClientTrier(current).show();
                  
                    

             
    });
   
           
            bt1.addActionListener(e->{  
                list2=ClubService.getInstance().rembourser(clubes.getId());
                  list2=ClubService.getInstance().getAllclub();
                  
                           NexmoClient client = NexmoClient.builder().apiKey("1182a7be").apiSecret("D4qUh1FcfiJ5at1L").build();
                          String messageText = "+ fondateur: "+clubes.getFondateur()+", Date: "+clubes.getDate_creation();
                            TextMessage message = new TextMessage("VeloTn", "21621313222", messageText);

                            SmsSubmissionResponse response;
                            try {
                                response = client.getSmsClient().submitMessage(message);
                                if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                                    System.out.println("Message sent successfully.");
                                } else {
                                    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
                                }
                            } // Logger.getLogger(AddParascolaire.class.getName()).log(Level.SEVERE, null, ex);
                           catch (NexmoClientException ex) {
                             //   Logger.getLogger(AddParascolaire.class.getName()).log(Level.SEVERE, null, ex);
                           }
                    Dialog.show("Frais d'inscription 10 DT", "Voulez-vous s'inscrire a cette club ?", new Command("OK"));
                    

             
    });
   
          
 
                   return BorderLayout.center(cnt).
                add(BorderLayout.EAST,c4);
        }

       
   
        

    
}
