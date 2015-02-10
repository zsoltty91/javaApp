/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.ResponseWorker;
import java.util.ArrayList;

/**
 *
 * @author zsolti
 */
public class RequestHandler {

    private static RequestHandler handler;

    private volatile ArrayList<Request> sendRequests;

    private volatile ArrayList<Request> getRequests;

    private volatile ArrayList<Request> processRequests;

    private ResponseWorker worker;

    private RequestHandler() {
        sendRequests = new ArrayList<>();
        getRequests = new ArrayList<>();
    }

    public static RequestHandler getInstance() {
        if (handler == null) {
            handler = new RequestHandler();
        }
        return handler;
    }

    public void setResponseWorker(ResponseWorker worker) {
        this.worker = worker;
    }

    public void addRequest(Request request) {
        sendRequests.add(request);
    }

    ArrayList<Request> getGetRequests() {
        return getRequests;
    }

    void addGetRequest(Request request) {
        getRequests.add(request);
    }

    void removeGetRequest(Request request) {
        getRequests.remove(request);
    }

    ArrayList<Request> getSendRequests() {
        return sendRequests;
    }

    void removeSendRequest(Request request) {
        sendRequests.remove(request);
    }

    public boolean hasRequestToSend() {
        return !sendRequests.isEmpty();
    }

    boolean hasRequestToGet() {
        return !getRequests.isEmpty();
    }

    boolean hasRequestToProcess() {
        return !processRequests.isEmpty();
    }

    void removeProcessRequest(Request request) {
        processRequests.remove(request);
    }

    void addProcessRequest(Request request) {
        processRequests.add(request);
    }

    public ArrayList<Request> getProcessRequests() {
        return processRequests;
    }

    void processResponse(Request response) {
        worker.processResponse(response);
    }
}
