/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.front;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




public class SendSms {
   
    public static final String SID="AC8ed54f1e2be30fb122829f00c1e60cf8";
    public static final String AUTH_TOKEN="7ee5cc2f5e294d505845b6729601f372";
    public static String to="+216 29 079 524"; 
           
    public static String from= "+12029157457";
    //private static String msg= "hello";
    
    //trial account can only send messages to verified accounts
    public static void sendSMS(String content){
        Twilio.init(SID, AUTH_TOKEN);
        Message sms = Message.creator(new PhoneNumber(to), new PhoneNumber(from), content).create();
    }
}
