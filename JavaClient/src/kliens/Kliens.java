/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kliens;

import model.MetaPersistence;
import network.CommunicationHandler;
import network.RequestHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expert;

/**
 *
 * @author zsolti
 */
public class Kliens {

    public static void main(String[] args) throws IOException {
        //Connection conn = Connection.getInstance();
        //System.out.println(AbstractNetworkObject.RequestType.GET.toString());
        Integer a= 10;
        
        Object b=a;
        //RequestHandler.getInstance().setResponseWorker(new MetaPersistence());
        System.out.println(((Integer)a).floatValue());
        CommunicationHandler.getInstance();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kliens.class.getName()).log(Level.SEVERE, null, ex);
        }
        CommunicationHandler.getInstance().stopListen();
        CommunicationHandler.getInstance().stopSend();
        
        String name = new String("alma");
        
        Expert e = new Expert(), e2 = new Expert();
        
        e.setName(name);
        
        e2.setName(name);
   //     e2.se
        
    }

}
