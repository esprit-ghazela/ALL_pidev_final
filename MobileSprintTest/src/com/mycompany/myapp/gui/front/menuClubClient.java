/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;


/**
 *
 * @author Ahmed laifi
 */
public class menuClubClient extends Form
{
    Form current;
        public menuClubClient()
    {
        current=this;
        setTitle("Menu Club partie Client");
        setLayout(BoxLayout.y());

      getTitleArea().setUIID("Container");
        setUIID("SignIn");

      
        getToolbar().addCommandToOverflowMenu("Exit",
                                              null, ev-> {Display.getInstance().exitApplication();});
               getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new acceuilF().show();});
    
        
         /**************************************************************************************************/
        Button btnvalclub = new Button(" les clubs a participé ");
        Style butStyle3=btnvalclub.getAllStyles();
        butStyle3.setBorder(RoundRectBorder.create().
                            strokeColor(0x1876CA).
                            strokeOpacity(120)
                           );
        butStyle3.setBgColor(0x1876CA);
        butStyle3.setFgColor(0x000000);
        butStyle3.setBgTransparency(255);
        butStyle3.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle3.setMargin(Component.BOTTOM, 3);

        butStyle3.setMargin(Component.TOP,50);


        butStyle3.setMargin(Component.LEFT,10);
        butStyle3.setMargin(Component.RIGHT,10);



         /**************************************************************************************************/
        Button btnvalcomp = new Button(" les competition disponible ");
        Style butStyle4=btnvalcomp.getAllStyles();
        butStyle4.setBorder(RoundRectBorder.create().
                            strokeColor(0x1876CA).
                            strokeOpacity(120)
                           );
        butStyle4.setBgColor(0x1876CA);
        butStyle4.setFgColor(0x000000);
        butStyle4.setBgTransparency(255);
        butStyle4.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle4.setMargin(Component.BOTTOM, 5);

        butStyle4.setMargin(Component.TOP,5);


        butStyle4.setMargin(Component.LEFT,10);
        butStyle4.setMargin(Component.RIGHT,10);
        addAll(btnvalclub,btnvalcomp);

        btnvalclub.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                new afficheClubClient(current).show();
            }
        });
        btnvalcomp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                new afficheCompetition(current).show();
            }
        });
 /**************************************************/


        getToolbar().addCommandToOverflowMenu("Exit",
                                              null, ev-> {Display.getInstance().exitApplication();});
}
}
