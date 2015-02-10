/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import network.Request;
import network.RequestType;
import network.RequestHandler;
import network.Value;
import network.Type;
import network.ResponseWorker;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zsolti
 */
public class MetaPersistence implements Perzistence, ResponseWorker {

    private final RequestHandler handler;

    private ExpertManager manager;

    public MetaPersistence() {
        handler = RequestHandler.getInstance();
    }

    public void setManager(ExpertManager manager) {
        this.manager = manager;
    }

    private Map<String, Value> setAll() {
        Map<String, Value> variables = new HashMap<>();

        variables.put("step", new Value(Type.DOUBLE));
        variables.put("maximum", new Value(Type.DOUBLE));
        variables.put("lots", new Value(Type.DOUBLE));
        variables.put("takeProfit", new Value(Type.INTEGER));
        variables.put("stopLoss", new Value(Type.INTEGER));

        variables.put("isOpenMa", new Value(Type.BOOLEAN));
        variables.put("maOpenPeriod", new Value(Type.INTEGER));
        variables.put("maOpenDiff", new Value(Type.INTEGER));

        variables.put("isOpenWpr", new Value(Type.BOOLEAN));
        variables.put("wprOpenPeriod", new Value(Type.INTEGER));
        variables.put("wprOpenBottom", new Value(Type.INTEGER));
        variables.put("wprOpenTop", new Value(Type.INTEGER));

        variables.put("isCloseWprTakeProfit", new Value(Type.BOOLEAN));
        variables.put("wprClosePeriod", new Value(Type.INTEGER));
        variables.put("wprCloseTakeProfitBottom", new Value(Type.INTEGER));
        variables.put("wprCloseTakeProfitTop", new Value(Type.INTEGER));

        variables.put("isCloseMaTakeProfit", new Value(Type.BOOLEAN));
        variables.put("maClosePeriod", new Value(Type.INTEGER));
        variables.put("maCloseTakeProfitDiff", new Value(Type.DOUBLE));

        variables.put("isCloseRSIStopLoss", new Value(Type.BOOLEAN));
        variables.put("rsiClosePeriod", new Value(Type.INTEGER));
        variables.put("rsiCloseStopLossTop", new Value(Type.INTEGER));
        variables.put("rsiCloseStopLossBottom", new Value(Type.INTEGER));
        return variables;
    }

    @Override
    public void get(String name) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(name);
        req.setValues(setAll());
        handler.addRequest(req);
    }

    @Override
    public void getAll() {
        Request req = new Request();
        req.setType(RequestType.AVAILABLE);
        handler.addRequest(req);
    }

    @Override
    public void save(Expert expert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(String name, String variable, String value) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(name);
        Map<String, Value> variables = new HashMap<>();
        variables.put(variable, new Value(Type.STRING, value));
        req.setValues(variables);
        handler.addRequest(req);
    }

    @Override
    public void set(String name, String variable, Integer value) {
        set(name, variable, value.toString());
    }

    @Override
    public void set(String name, String variable, Double value) {
        set(name, variable, value.toString());
    }

    @Override
    public void set(String name, String variable, Boolean value) {
        set(name, variable, value.toString());
    }

    public void get(String name, String variable, Type type) {
        Request req = new Request();
        req.setType(RequestType.GET);
        req.setObjectName(name);
        Map<String, Value> variables = new HashMap<>();
        variables.put(variable, new Value(type));
        req.setValues(variables);
        handler.addRequest(req);
    }

    @Override
    public void getString(String name, String variable) {
        get(name, variable, Type.STRING);
    }

    @Override
    public void getInt(String name, String variable) {
        get(name, variable, Type.INTEGER);
    }

    @Override
    public void getDouble(String name, String variable) {
        get(name, variable, Type.DOUBLE);
    }

    @Override
    public void getBoolean(String name, String variable) {
        get(name, variable, Type.BOOLEAN);
    }

    @Override
    public void processResponse(Request request) {
        Expert expert = manager.getMetaExpert(request.getObjectName());
        if (expert != null) {
            for (String variable : request.getValues().keySet()) {
                refresVariable(expert, variable, request.getValues().get(variable));
            }
        }
    }

    private void refresVariable(Expert expert, String variable, Value value) {
        switch (variable) {
            case "step":
                expert.refreshStep((Double) value.getValue());
                break;
            case "maximum":
                expert.refreshMaximum((Double) value.getValue());
                break;
            case "lots":
                expert.refreshLots((Double) value.getValue());
                break;
            case "takeProfit":
                expert.refreshTakeProfit((Integer) value.getValue());
                break;
            case "stopLoss":
                expert.refreshStopLoss((Integer) value.getValue());
                break;

            case "isOpenMa":
                expert.refreshIsOpenMa((Boolean) value.getValue());
                break;
            case "maOpenPeriod":
                expert.refreshMaOpenPeriod((Integer) value.getValue());
                break;
            case "maOpenDiff":
                expert.refreshMaOpenDiff((Double) value.getValue());
                break;

            case "isOpenWpr":
                expert.refreshIsOpenWpr((Boolean) value.getValue());
                break;
            case "wprOpenPeriod":
                expert.refreshWprOpenPeriod((Integer) value.getValue());
                break;
            case "wprOpenBottom":
                expert.refreshWprOpenBottom((Integer) value.getValue());
                break;
            case "wprOpenTop":
                expert.refreshWprOpenTop((Integer) value.getValue());
                break;

            case "isCloseWprTakeProfit":
                expert.refreshIsCloseWprTakeProfit((Boolean) value.getValue());
                break;
            case "wprClosePeriod":
                expert.refreshWprClosePeriod((Integer) value.getValue());
                break;
            case "wprCloseTakeProfitBottom":
                expert.refreshWprCloseTakeProfitBottom((Integer) value.getValue());
                break;
            case "wprCloseTakeProfitTop":
                expert.refreshWprCloseTakeProfitTop((Integer) value.getValue());
                break;

            case "isCloseMaTakeProfit":
                expert.refreshIsCloseMaTakeProfit((Boolean) value.getValue());
                break;
            case "maClosePeriod":
                expert.refreshMaClosePeriod((Integer) value.getValue());
                break;
            case "maCloseTakeProfitDiff":
                expert.refreshMaCloseTakeProfitDiff((Double) value.getValue());
                break;

            case "isCloseRSIStopLoss":
                expert.refreshIsCloseRSIStopLoss((Boolean) value.getValue());
                break;
            case "rsiClosePeriod":
                expert.refreshRsiClosePeriod((Integer) value.getValue());
                break;
            case "rsiCloseStopLossTop":
                expert.refreshRsiCloseStopLossTop((Integer) value.getValue());
                break;
            case "rsiCloseStopLossBottom":
                expert.refreshRsiCloseStopLossBottom((Integer) value.getValue());
                break;
        }
    }

}
