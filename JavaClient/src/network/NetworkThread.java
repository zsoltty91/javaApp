/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class NetworkThread {

    private Thread thread;

    Class<NetworkRunnable> clazz;

    NetworkRunnable runnable;

    public NetworkThread(Class<NetworkRunnable> clazz) {
        this.clazz = clazz;
    }

    public void stopThread() {
        runnable.stop = true;
    }

    public void startThread() {
        if (!runnable.stop) {
            try {
                runnable = clazz.newInstance();
                runnable.stop = false;
                thread.start();
            } catch (InstantiationException ex) {
                Logger.getLogger(NetworkThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(NetworkThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
