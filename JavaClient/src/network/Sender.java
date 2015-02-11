/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author zsolti
 */
public class Sender extends NetworkRunnable {

    @Override
    protected void doThread() {
        System.out.println("SENDER: "+handler.getSendRequests().toString());
        if (handler.hasRequestToSend()) {
            int size = handler.getSendRequests().size();
            ArrayList<Request> requests = handler.getSendRequests();
            Request request = null;
            for (int i = 0; i<size; i++) {
                request = requests.get(0);
                conn.send(request.getType().toString());
                if (!request.getType().equals(RequestType.AVAILABLE)) {
                    conn.send(request.getObjectName());
                    for (String variable : request.getValues().keySet()) {
                        conn.send(variable);
                    }
                }
                conn.send("END");
                if (request.getType().equals(RequestType.GET)) {
                    handler.addGetRequest(request);
                }
                System.out.println("sent, remove következik");
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
