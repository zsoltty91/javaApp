/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.HashMap;
import java.util.List;
import model.ParameterTypeMapper;

/**
 *
 * @author zsolti
 */
public class RequestFactory {

    private static final RequestHandler handler;

    static {
        handler = RequestHandler.getInstance();
    }

    public static void getAllExpert() {
        Request req = new Request();
        req.setType(RequestType.AVAILABLE);
        handler.addRequest(req);
    }

    public static void getAllParameter(String expert) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(expert);
        HashMap<String, Value> variables = new HashMap<>();
        HashMap<String, Type> parameters = ParameterTypeMapper.getParameters();
        for (String variable : parameters.keySet()) {
            variables.put(variable, new Value(parameters.get(variable)));
        }
        req.setValues(variables);
        handler.addRequest(req);
    }

    public static void getParameter(String expert, String parameter) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(expert);
        HashMap<String, Value> variables = new HashMap<>();
        variables.put(parameter, new Value(ParameterTypeMapper.getType(parameter)));
        req.setValues(variables);
        handler.addRequest(req);
    }

    public static void getParameter(String expert, List<String> parameters) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(expert);
        HashMap<String, Value> variables = new HashMap<>();
        for (String parameter : parameters) {
            variables.put(parameter, new Value(ParameterTypeMapper.getType(parameter)));
        }
        req.setValues(variables);
        handler.addRequest(req);
    }
    
    public static void setParameter(String expert, String parameter, Object value) {
        Request req = new Request();
        req.setType(RequestType.SET);
        req.setObjectName(expert);
        HashMap<String, Value> variables = new HashMap<>();
        variables.put(parameter, new Value(ParameterTypeMapper.getType(parameter),value));
        
        req.setValues(variables);
        handler.addRequest(req);
    }
    
    public static void setParameter(String expert, HashMap<String,Object> parameters) {
        Request req = new Request();
        req.setType(RequestType.SET);
        req.setObjectName(expert);
        HashMap<String, Value> variables = new HashMap<>();
        for (String parameter : parameters.keySet()) {
            variables.put(parameter, new Value(ParameterTypeMapper.getType(parameter),parameters.get(parameter)));
        }
        req.setValues(variables);
        handler.addRequest(req);
    }
}
