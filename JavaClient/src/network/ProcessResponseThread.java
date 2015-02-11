/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import network.Request;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class ProcessResponseThread extends NetworkRunnable {
    
    @Override
    protected void doThread() {
        System.out.println("PROCESS: "+handler.getProcessRequests().toString());
        if (handler.hasRequestToProcess()) {
            int size = handler.getProcessRequests().size();
            ArrayList<Request> requests = handler.getProcessRequests();
            Request request = null;
            for (int i = 0; i<size; i++) {
                request = requests.get(0);
                handler.processResponse(request);
                handler.removeProcessRequest(request);
            }
        } else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

}
