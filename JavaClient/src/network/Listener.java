/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.Request;
import network.Value;
import network.Type;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class Listener extends NetworkRunnable {

    private void readVariables(Map<String, Value> variables) {
        String name;
        Object value;
        while (true) {
            name = conn.getString();
            if (name.equals("END")) {
                break;
            }
            if (variables.get(name) == null) {
                logger.log(Level.SEVERE, "Nem volt ilyen változó a kérésben!");
                break;
            }
            Type type = variables.get(name).getType();
            if (type == null) {
                logger.log(Level.SEVERE,"Üres típus!");
                break;
            }
            value = readVariable(type);
            variables.get(name).setValue(value);
        }
    }

    private Object readVariable(Type type) {
        switch (type) {
            case BOOLEAN: {
                return conn.getBoolean();
            }
            case DOUBLE: {
                return conn.getDouble();
            }
            case INTEGER: {
                return conn.getInteger();
            }
            case STRING: {
                return conn.getString();
            }
        }
        return null;
    }

    @Override
    protected void doThread() {
        if (handler.hasRequestToGet()) {
            String read;
            for (Request request : handler.getGetRequests()) {
                switch (request.getType()) {
                    case AVAILABLE: {
//TODO
                    }
                    case GET: {
                        read = conn.getString();
                        if (read.equals("END")) {
                            logger.log(Level.SEVERE, "Váratlan END!");
                            break;
                        }
                        if (!read.equals(request.getObjectName())) {
                            logger.log(Level.SEVERE, "Nem egyezik az objektumnév!");
                            break;
                        }
                        readVariables(request.getValues());
                        handler.addProcessRequest(request);
                        handler.removeGetRequest(request);
                        break;
                    }
                }
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
