/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.ConnectionWrapper;
import network.RequestHandler;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public abstract class NetworkRunnable implements Runnable {

    protected RequestHandler handler;
    
    boolean stop = false;

    protected ConnectionWrapper conn;
    
    protected Logger logger;


    protected NetworkRunnable() {
        handler = RequestHandler.getInstance();
        conn = new ConnectionWrapper();
        logger=Logger.getLogger("Network:");
    }

    protected abstract void doThread();

    @Override
    public void run() {
        while (!stop) {
            doThread();
        }
    }

}
