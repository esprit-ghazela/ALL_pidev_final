package com.mycompany.myapp.gui.front;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entitie.Panier;
import com.mycompany.myapp.entitie.User;
import static com.mycompany.myapp.gui.front.acceuilF.listPanier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yousra
 */
public class panierForm extends BaseForm {

    Form current;
    List<Panier> listPanier = new ArrayList<Panier>();

    public panierForm() {
        super("Newsfeed", BoxLayout.y());
        listPanier = acceuilF.listPanier;
        current = this;
        Resources res = UIManager.initNamedTheme("/theme", "Theme");
    getTitleArea().setUIID("Container");
        setUIID("SignIn");
        getToolbar().addMaterialCommandToLeftBar("back", 
                FontImage.MATERIAL_BACKUP, ev->{new BaseForm().show();});
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Panier");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        Image img = res.getImage("6.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        System.out.println("Panire : Size " + listPanier.size());
        for (int i = 0; i < listPanier.size(); i++) {
            System.out.println("Panire : Size " + listPanier.size());
            System.out.println("Identifient : " + listPanier.get(i).getId());
            System.out.println("Nom : " + listPanier.get(i).getNom());
            System.out.println("Reference : " + listPanier.get(i).getReference());
            System.out.println("Image : " + listPanier.get(i).getImage());
            System.out.println("Prix : " + listPanier.get(i).getPrix());
            System.out.println("Quntiter : " + listPanier.get(i).getQuantite());
            System.out.println("Quntiter panier : " + listPanier.get(i).getQtPanier());
            System.out.println("----------------------------------------------------------------");

        }

        Label vide = new Label("Votre panier est vide");
        vide.getAllStyles().setMargin(Component.TOP, 550);

        Button achat = new Button("Consulter artictle");
        Style butStyleb = achat.getAllStyles();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        butStyleb.setBorder(RoundRectBorder.create().
                strokeColor(0x008000).
                strokeOpacity(120).
                stroke(borderStroke));
        butStyleb.setBgColor(0x008000);
        butStyleb.setFgColor(0x000000);
        butStyleb.setBgTransparency(255);
        butStyleb.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyleb.setMargin(Component.BOTTOM, 3);
        butStyleb.setMargin(Component.TOP, 3);
        butStyleb.setMargin(Component.LEFT, 10);
        butStyleb.setMargin(Component.RIGHT, 10);

        achat.addActionListener(ev -> {
            new acceuilF().show();
        });

        if (listPanier.size() == 0) {
            add(LayeredLayout.encloseIn(
                    sl, vide
            ));
            add(achat);
        } else {
            Label vide2 = new Label("");
            vide2.getAllStyles().setMargin(Component.TOP, 550);

            add(LayeredLayout.encloseIn(
                    sl, vide2
            ));

            Container listss = new Container(BoxLayout.y());
            Container lists = new Container(BoxLayout.y());
            for (Panier c1 : listPanier) {
                {
                    listss.add(createContainer(c1));
                }
            }

            listss.setScrollableY(true);
            Style st = lists.getAllStyles();
            st.setMargin(Component.BOTTOM, 900);

            Label totall = new Label("Total :");
            Label totall1 = new Label(getTotal() + "Dt");

            Button cachat = new Button("Continuer vos achat");
            Style butStylebc = cachat.getAllStyles();
            Stroke borderStrokec = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            butStylebc.setBorder(RoundRectBorder.create().
                    strokeColor(0x008000).
                    strokeOpacity(120).
                    stroke(borderStroke));
            butStylebc.setBgColor(0x008000);
            butStylebc.setFgColor(0x000000);
            butStylebc.setBgTransparency(255);
            butStylebc.setMarginUnit(Style.UNIT_TYPE_DIPS);
            butStylebc.setMargin(Component.BOTTOM, 1);
            butStylebc.setMargin(Component.TOP, 1);
            butStylebc.setMargin(Component.LEFT, 10);
            butStylebc.setMargin(Component.RIGHT, 10);

            Button pac = new Button("Passer voter commande");
            Style butStylebpac = pac.getAllStyles();
            Stroke borderStrokepac = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            butStylebpac.setBorder(RoundRectBorder.create().
                    strokeColor(0x008000).
                    strokeOpacity(120).
                    stroke(borderStroke));
            butStylebpac.setBgColor(0xFF0000);
            butStylebpac.setFgColor(0x000000);
            butStylebpac.setBgTransparency(255);
            butStylebpac.setMarginUnit(Style.UNIT_TYPE_DIPS);
            butStylebpac.setMargin(Component.BOTTOM, 1);
            butStylebpac.setMargin(Component.TOP, 1);
            butStylebpac.setMargin(Component.LEFT, 10);
            butStylebpac.setMargin(Component.RIGHT, 10);

            cachat.addActionListener(ev -> {
                new acceuilF().show();
            });
            pac.addActionListener(ev -> {
                new addComForm().show();
            });
            Container ct = BoxLayout.encloseX(totall, totall1);
            Container cb = BoxLayout.encloseY(achat, pac);

            Container cnt2 = BoxLayout.encloseY(ct, cb);

            cnt2.getStyle().setBgColor(0x808080);
            cnt2.getStyle().setBgTransparency(255);
            Style st2 = cnt2.getAllStyles();
            st2.setMargin(Component.BOTTOM, 5);

            listss.add(BorderLayout.centerEastWest(null, null, cnt2));

            Tabs t = new Tabs();
            Style s = UIManager.getInstance().getComponentStyle("Tab");
            t.setUIID("Tab");

            t.addTab("Produit en stock", listss);
            t.setScrollableY(true);
            add(t);
        }
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private Component createContainer(Panier c1) {
        Container cn1 = new Container(new FlowLayout(Container.CENTER));
        Container cn2 = new Container(new FlowLayout(Container.CENTER));

        Label image = new Label("Image :");
        Label imn = new Label("");

        int deviceWidth = 300;
        String urlImage = "http://localhost/pi-dev-master/web/front_template/img/category/" + c1.getImage();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
        Image imgs = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
        ImageViewer imageV = new ImageViewer(imgs);

        Label ref = new Label("Réf.");
        Label nom = new Label("Nom");
        Label prix = new Label("Prix (unité)");
        Label qt = new Label("Quntiter");
        Label totaleu = new Label("Total");

        ref.getAllStyles().setBgColor(0x00000);
        nom.getAllStyles().setBgColor(0x00000);
        prix.getAllStyles().setBgColor(0x00000);
        qt.getAllStyles().setBgColor(0x00000);

        Label ref1 = new Label(c1.getReference());
        Label nom1 = new Label(c1.getNom());
        double prixx = c1.getPrix();
        Label prix1 = new Label(prixx + " DT");
        Label totaleu1 = new Label();

        double calct = c1.getQtPanier() * c1.getPrix();
        totaleu1.setText(calct + " DT");

        Picker qt1 = new Picker();
        String[] qtli = new String[c1.getQuantite()];
        qtli[0] = "0";
        int r = 0;
        if (c1.getQuantite() > 0) {
            for (int i = 1; i < c1.getQuantite(); i++) {
                qtli[i] = String.valueOf(i);
            }

            qt1.setStrings(qtli);
            qt1.setSelectedString(qtli[c1.getQtPanier()]);

            qt1.addActionListener(e -> {
                c1.setQtPanier(qt1.getSelectedStringIndex());
                System.out.println("SELEC / " + qt1.getSelectedStringIndex());
                for (int i = 0; i < listPanier.size(); i++) {
                    if (listPanier.get(i).getId() == c1.getId()) {
                        int qta = listPanier.get(i).getQtPanier();
                        listPanier.get(i).setQtPanier(qt1.getSelectedStringIndex());
                        current.revalidate();
                        new panierForm().show();
                    }
                }
                ToastBar.showMessage("Quantiter modifier", FontImage.MATERIAL_INFO);
            });
        }

        //Label qt = new Label("Quntiter");
        cn1 = BoxLayout.encloseY(imageV);
        cn2 = BoxLayout.encloseY(ref, nom, prix, qt);

        Container cn3 = new Container(new FlowLayout(Container.CENTER));

        if (c1.getQuantite() == 0) {
            Label stock = new Label("Rupture de stock");
            cn3 = BoxLayout.encloseY(ref1, nom1, prix1, stock);
        } else {
            cn3 = BoxLayout.encloseY(ref1, nom1, prix1, qt1);
            cn2.revalidate();
        }

        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, "TitleCommand", 3);

        Button btn1 = new Button(icon);

        Container cnt = BoxLayout.encloseX(cn1, cn2, cn3, btn1);
        cnt.getStyle().setBgColor(0xE5F2FE);
        cnt.getStyle().setBgTransparency(255);

        Style st = cnt.getAllStyles();
        st.setMargin(Component.BOTTOM, 5);
        Stroke borderStroke = new Stroke(3, Stroke.CAP_BUTT, Stroke.JOIN_MITER, 3);

        btn1.addActionListener(ev -> {
            listPanier.remove(c1);
            current.revalidate();
            new panierForm().show();
        });

        return cnt;

    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < listPanier.size(); i++) {
            total = total + listPanier.get(i).getQtPanier() * listPanier.get(i).getPrix();
        }
        return total;
    }
}
