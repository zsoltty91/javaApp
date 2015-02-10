/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.logging.Level;

/**
 *
 * @author zsolti
 */
public class Sender extends NetworkRunnable {

    @Override
    protected void doThread() {
        if (handler.hasRequestToSend()) {
            for (Request request : handler.getSendRequests()) {
                conn.send(request.getType().toString());
                conn.send(request.getObjectName());
                for (String variable : request.getValues().keySet()) {
                    conn.send(variable);
                }
                conn.send("END");
                if (request.getType().equals(RequestType.GET)) {
                    handler.addGetRequest(request);
                }
                handler.removeSendRequest(request);
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
