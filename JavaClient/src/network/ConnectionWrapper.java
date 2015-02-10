/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class ConnectionWrapper {

    public static final Connection conn;
    private static final Logger logger = Logger.getLogger(ConnectionWrapper.class.getName());

    static {
        conn = Connection.getInstance();
    }

    public void close() {
        conn.freeConnection();
    }

    public Integer getInteger() {
        Integer number = null;
        try {
            Integer.parseInt(conn.readMessage());
        } catch (NumberFormatException nfe) {
            logger.log(Level.WARNING, nfe.getMessage());
        }
        return number;
    }
    
    public Double getDouble() {
        Double number = null;
        try {
            Double.parseDouble(conn.readMessage());
        } catch (NumberFormatException nfe) {
            logger.log(Level.WARNING, nfe.getMessage());
        }
        return number;
    }
    
    public Boolean getBoolean() {
        Boolean number = null;
        try {
            Boolean.parseBoolean(conn.readMessage());
        } catch (NumberFormatException nfe) {
            logger.log(Level.WARNING, nfe.getMessage());
        }
        return number;
    }
    
    public String getString() {
        return conn.readMessage();
    }
    
    public void send(Integer number) {
        conn.sendMessage(number.toString());
    }
    
    public void send(Double number) {
        conn.sendMessage(number.toString());
    }
    
    public void send(String message) {
        conn.sendMessage(message);
    }
}
