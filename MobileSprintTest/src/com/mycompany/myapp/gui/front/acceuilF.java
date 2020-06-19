/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui.front;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entitie.Categorie;
import com.mycompany.myapp.entitie.Panier;
import com.mycompany.myapp.entitie.Produit;
import com.mycompany.myapp.entitie.User;
import com.mycompany.myapp.services.CategorieService;
import com.mycompany.myapp.services.ProduitService;
import java.util.ArrayList;
import java.util.List;

/**
 * The newsfeed form
 *
 * @author ASUS
 */
public class acceuilF extends BaseForm {

    Form current;
    public static User recupUser;
    static final List<Panier> listPanier = new ArrayList<Panier>();

    public acceuilF() {
        super("Newsfeed", BoxLayout.y());
        current = this;
        Resources res = UIManager.initNamedTheme("/theme", "Theme");

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produit");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, "TitleCommand", 5);

        tb.addCommandToRightBar(String.valueOf(listPanier.size()), icon, (e) -> {
            new panierForm().show();

        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        Label spacer3 = new Label();
        Label spacer4 = new Label();
        //addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");

        addTab(swipe, res.getImage("1.jpg"), spacer1);
        addTab(swipe, res.getImage("2.jpg"), spacer3);
        addTab(swipe, res.getImage("3.jpg"), spacer4);
        addTab(swipe, res.getImage("4.jpg"), spacer2);
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Tous", barGroup);
        all.setUIID("SelectBar");

        CategorieService categorieService = new CategorieService();
        ArrayList<Categorie> list = categorieService.getList();

        for (Categorie c : list) {

        }

       
        
        

        ProduitService produitService = new ProduitService();
        ArrayList<Produit> listp = produitService.getList();
        for (Produit p : listp) {
            String urlImage = "http://localhost/pi-dev-master/web/front_template/img/category/" + p.getImage();
            int deviceWidth = 600;
            Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            Image im = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

            addButton(im, p);
            //addButton2(im, p.getNom());

        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void addButton(Image img, Produit produit) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Label image = new Label(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(produit.getNom());
        ta.setUIID("NewsTopLine");
        ta.setSize(new Dimension(5, 5));
        ta.setEditable(false);
        Label p = new Label(produit.getPrix() + " DT  ", "NewsBottomLine");
        Label m = new Label(produit.getMarque(), "NewsTopLine");
        m.setTextPosition(RIGHT);

        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, "panierIcon", 4);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "panierIcon", 2);
        FontImage icon3 = FontImage.createMaterial(FontImage.MATERIAL_ARROW_UPWARD, "panierIcon", 2);
        Button panier = new Button(icon);
        panier.setUIID("labelPanier");
        Label l1 = new Label(icon2);
        Label l2 = new Label(icon3);
        cnt.setLeadComponent(panier);

        Container ccc1 = BoxLayout.encloseY(ta, m, p);
        Container ccc2 = BoxLayout.encloseY(panier);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseX(
                        ccc1
                ));
        cnt.add(BorderLayout.EAST,
                BoxLayout.encloseY(
                        l1,
                        ccc2,
                        l2
                ));
        add(cnt);
        // image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));

        panier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                Panier newPanier = new Panier();
                newPanier.setId(produit.getId());
                newPanier.setNom(produit.getNom());
                newPanier.setReference(produit.getReference());
                newPanier.setImage(produit.getImage());
                newPanier.setQuantite(produit.getQuantite());
                newPanier.setQtPanier(1);
                newPanier.setPrix(produit.getPrix());

                if (listPanier.size() == 0 && produit.getQuantite() != 0) {
                    listPanier.add(newPanier);
                    current.revalidate();
                    new acceuilF().show();
                    ToastBar.showMessage("Article ajouter au panier", FontImage.MATERIAL_INFO);
                } else if (produit.getQuantite() == 0) {
                    ToastBar.showMessage("Rupture de stock", FontImage.MATERIAL_INFO);
                } 
                
                else if (check(produit.getId()) &&   produit.getQuantite() > getQt(produit.getId()) ) {
                    setQt(produit.getId()) ;
                    ToastBar.showMessage("Quantiter ajouter", FontImage.MATERIAL_INFO);
                } 
                else if (produit.getQuantite() != 0 && !check(produit.getId())) {
                    listPanier.add(newPanier);
                    current.revalidate();
                    new acceuilF().show();
                    ToastBar.showMessage("Article ajouter au panier", FontImage.MATERIAL_INFO);
                }
                else {
                    ToastBar.showMessage("Erreur d'ajouter au panier", FontImage.MATERIAL_INFO);
                 
                    /*
                     for (int i = 0; i < listPanier.size(); i++) {
                        
                        
                     System.out.println("check : "+!check(produit.getId()));
                     if (!check(produit.getId()) && listPanier.get(i).getQuantite() != 0 ) {
                     int qta = listPanier.get(i).getQtPanier();
                     if (qta < listPanier.get(i).getQuantite()) {
                     qta++;
                     listPanier.get(i).setQtPanier(qta);
                     ToastBar.showMessage("Quantiter ajouter", FontImage.MATERIAL_INFO);
                     }else if (listPanier.get(i).getQuantite() != 0){
                     ToastBar.showMessage("Rupture de stock insufusant", FontImage.MATERIAL_INFO);
                     }
                            
                     else{
                     ToastBar.showMessage("Stock insufusant", FontImage.MATERIAL_INFO);
                     }
                     } else {
                     listPanier.add(newPanier);
                     ToastBar.showMessage("Article ajouter au panier", FontImage.MATERIAL_INFO);
                     }
                     }*/

                }
                /*
                 System.out.println(produit.getId());
                 System.out.println(produit.getNom());
                 System.out.println(produit.getReference());
                 System.out.println(produit.getImage());
                 System.out.println(produit.getQuantite());
                 System.out.println(1);
                 System.out.println(produit.getPrix());
                 System.out.println("===================================================") ;*/

            }
        });
    }

    private boolean check(int id) {
        boolean ck = false;
        for (int i = 0; i < listPanier.size(); i++) {
            if (listPanier.get(i).getId() == id) {
                System.out.println("check : ");
                System.out.println("check 1: " + listPanier.get(i).getId());
                System.out.println("check 2: " + id);
                return true;
            }
        }
        return ck;
    }

    private int getQt(int id) {
        for (int i = 0; i < listPanier.size(); i++) {
            if (listPanier.get(i).getId() == id) {
                System.out.println("check : ");
                System.out.println("check 1: " + listPanier.get(i).getId());
                System.out.println("check 2: " + id);
                return listPanier.get(i).getQtPanier();
            }
        }
        return 0;
    }

    private void setQt(int id) {
        for (int i = 0; i < listPanier.size(); i++) {
            if (listPanier.get(i).getId() == id) {
                int qta = listPanier.get(i).getQtPanier() ;
                qta++;
                listPanier.get(i).setQtPanier(qta);
            }
        }

    }

    private void addButton2(Image img, String title) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);

        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);
        Button test = new Button();
        cnt.setLeadComponent(test);

        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta, test
                )
        );
        add(cnt);

        test.addActionListener(e -> {
            System.out.println("Title : " + title);
        });
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
