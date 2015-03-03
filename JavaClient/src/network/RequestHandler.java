/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import model.MetaPersistence;

/**
 *
 * @author zsolti
 */
public class RequestHandler {

    private static RequestHandler handler;

    private volatile ArrayList<Request> sendRequests;

    private volatile ArrayList<Request> getRequests;

    private volatile ArrayList<Request> processRequests;

    private ArrayList<ResponseWorker> workers;

    private RequestHandler() {
        sendRequests = new ArrayList<>();
        getRequests = new ArrayList<>();
        processRequests = new ArrayList<>();

        workers = new ArrayList<>();

        workers.add(new LogProcessResponse());
        workers.add(MetaPersistence.getInstance());
    }

    public static RequestHandler getInstance() {
        if (handler == null) {
            handler = new RequestHandler();
        }
        return handler;
    }

    public void addResponseWorker(ResponseWorker worker) {
        this.workers.add(worker);
    }

    public void removeResponseWorker(ResponseWorker worker) {
        this.workers.remove(worker);
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

    boolean hasRequestToSend() {
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

    ArrayList<Request> getProcessRequests() {
        return processRequests;
    }

    void processResponse(Request response) {
        for (ResponseWorker worker : workers) {
            worker.processResponse(response);
        }
    }
}
