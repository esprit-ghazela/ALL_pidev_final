/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edito
 */
package com.mycompany.myapp.gui.front;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;

/**
 *
 * @author aziz khbou
 */
public class SendMail {
    public static void sendMessage(String[] recipients, String subject, Message msg) {
        
        Message m = new Message("Paiement validé avec succés");

        Display.getInstance().sendMessage(new String[]{"azizkhbou@gmail.com"}, "Paiement", m);
       
    }
}
