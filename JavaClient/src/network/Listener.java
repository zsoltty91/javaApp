/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import model.ParameterTypeMapper;

/**
 *
 * @author zsolti
 */
public class Listener extends NetworkRunnable {

    private void readVariables(Map<String, Value> variables, RequestType rType) {
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
            if (rType.equals(RequestType.GET)) {
                Type type = variables.get(name).getType();
                if (type == null) {
                    logger.log(Level.SEVERE, "Üres típus!");
                    break;
                }
                value = readVariable(type);
                variables.get(name).setValue(value);
            }
        }
    }

    private Object readVariable(Type type) {
        Object result = null;
        switch (type) {
            case BOOLEAN: {
                result = conn.getBoolean();break;
            }
            case DOUBLE: {
                result = conn.getDouble();break;
            }
            case INTEGER: {
                result = conn.getInteger();break;
            }
            case STRING: {
                result = conn.getString();break;
            }
        }
        System.out.println("result: "+result);
        return result;
    }

    @Override
    protected void doThread() {
        System.out.println("LISTENER: " + handler.getGetRequests().toString());
        if (handler.hasRequestToGet()) {
            System.out.println("van listener requests to get");
            String read;
            int size = handler.getGetRequests().size();
            ArrayList<Request> requests = handler.getGetRequests();
            Request request = null;
            System.out.println("hallo");
            boolean exit = false;
            while(true) {
                if(!requests.isEmpty()) {
                    request = requests.get(0);
                } else {
                    read = conn.getString();
                    if(!read.equalsIgnoreCase("fine")) {
                        logger.log(Level.SEVERE,"requests has gone, but not read fine");
                    } else {
                        logger.log(Level.INFO,"requests has gone, and read FINE");
                    }
                }
                if (request.getType().equals(RequestType.AVAILABLE)) {
                    while(true) {
                        read = conn.getString();
                        if(read.equals("AVAILABLE_FINE")) {
                            break;
                        }
                        if(read.equals("FINE")) {
                            System.out.println("read fine, exit listen");
                            exit=true;
                            break;
                        }
                        Request newRequest = new Request();
                        newRequest.setType(RequestType.valueOf(read));
                        
                        read = conn.getString();
                        newRequest.setObjectName(read);
                        
                        HashMap<String,Value> variables = new HashMap<>();
                        String variable;
                        Object value;
                        Type type;
                        while(true) {
                            variable = conn.getString();
                            if(variable.equals("END")) {
                                break;
                            }
                            
                            type = ParameterTypeMapper.getType(variable);
                            //System.out.println("type: "+type);
                            value = readVariable(type);
                            //System.out.println("value: "+value);
                            variables.put(variable, new Value(type, value));
                        }
                        newRequest.setValues(variables);
                        handler.addProcessRequest(newRequest);
                    }
                    handler.removeGetRequest(request);
                } else {
                    read = conn.getString();
                    System.out.println("read: " + read);
                    if(read.equals("FINE")) {
                            System.out.println("read fine, exit listen");
                            exit=true;
                            break;
                        }
                    if (read.equals("END")) {
                        logger.log(Level.SEVERE, "Váratlan END!");
                        break;
                    }
                    if (!read.equalsIgnoreCase(request.getType().toString())) {
                        logger.log(Level.SEVERE, "Nem egyezik a request típusa! "+read+" : "+request.getType().toString());
                    }
                    read = conn.getString();
                    if (!read.equals(request.getObjectName())) {
                        logger.log(Level.SEVERE, "Nem egyezik az objektumnév! "+read);
                        break;
                    }
                    readVariables(request.getValues(),request.getType());
                    handler.addProcessRequest(request);
                    handler.removeGetRequest(request);
                    break;
                }
                if(exit) {
                    break;
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
