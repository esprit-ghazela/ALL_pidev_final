/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.mycompany.myapp.gui.front.ChartDemosForm;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;


/**
 *
 * @author Ahmed laifi
 */
public class menuClub extends Form
{
    Form current;


    public menuClub()
    {
        current=this;
        setTitle("Menu Club");
        setLayout(BoxLayout.y());


        getToolbar().addCommandToOverflowMenu("Exit",
                                              null, ev-> {Display.getInstance().exitApplication();});
  
      // getToolbar().addMaterialCommandToLeftBar("back", 
         //       FontImage.MATERIAL_BACKUP, ev->{new menuprincipale().show();});
       
        ImageViewer backgrounde=new ImageViewer(MyApplication.theme.getImage("back.jpeg"));
Style loginStyle = getAllStyles();

        loginStyle.setBgImage(MyApplication.theme.getImage("unnamed.jpg"));
        /**************************************************************************************************/
        Button btnval = new Button(" ajouter un club ");
        Style butStyle=btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                           strokeColor(0x1876CA).
                           strokeOpacity(120)
                          );
        butStyle.setBgColor(0x1876CA);
        butStyle.setFgColor(0x000000);
        butStyle.setBgTransparency(255);
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);
        butStyle.setMargin(Component.TOP,50);
        butStyle.setMargin(Component.LEFT,10);
        butStyle.setMargin(Component.RIGHT,10);
        /**************************************************************************************************/
        Button btnval1 = new Button(" gérer les clubs ");
        Style butStyle1=btnval1.getAllStyles();
        butStyle1.setBorder(RoundRectBorder.create().
                            strokeColor(0x1876CA).
                            strokeOpacity(120)
                           );
        butStyle1.setBgColor(0x1876CA);
        butStyle1.setFgColor(0x000000);
        butStyle1.setBgTransparency(255);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.BOTTOM, 5);

        butStyle1.setMargin(Component.TOP,5);


        butStyle1.setMargin(Component.LEFT,10);
        butStyle1.setMargin(Component.RIGHT,10);


        /**************************************************************************************************/
        Button btnvalchart = new Button(" statistique ");
        Style butStyle2=btnvalchart.getAllStyles();
        butStyle2.setBorder(RoundRectBorder.create().
                            strokeColor(0x1876CA).
                            strokeOpacity(120)
                           );
        butStyle2.setBgColor(0x1876CA);
        butStyle2.setFgColor(0x000000);
        butStyle2.setBgTransparency(255);
        butStyle2.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle2.setMargin(Component.BOTTOM, 3);

        butStyle2.setMargin(Component.TOP,5);


        butStyle2.setMargin(Component.LEFT,10);
        butStyle2.setMargin(Component.RIGHT,10);


       
        addAll(btnval,btnval1,btnvalchart);

        /**************************************************************************************************/
        btnvalchart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                new ChartDemosForm().show();
            }
        });
        btnval.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                new ajoutclub(current).show();
            }
        });
        btnval1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                new afficheClub(current).show();
            }
        });
       

        /**************************************************/


        getToolbar().addCommandToOverflowMenu("Exit",
                                              null, ev-> {Display.getInstance().exitApplication();});



    }
}
