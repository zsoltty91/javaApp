/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

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
        if (handler.hasRequestToProcess()) {
            for (Request request : handler.getProcessRequests()) {
                handler.processResponse(request);
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
