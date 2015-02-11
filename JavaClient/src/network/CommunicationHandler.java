/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author zsolti
 */
public class CommunicationHandler {

    private boolean startedListen = false;

    private boolean startedSend = false;

    private boolean startedProcess = false;

    private static CommunicationHandler handler;

    NetworkThread<Listener> listener;

    NetworkThread<Sender> sender;

    NetworkThread<ProcessResponseThread> process;

    private CommunicationHandler() {
        listener = new NetworkThread<>(Listener.class);
        sender = new NetworkThread<>(Sender.class);
        process = new NetworkThread<>(ProcessResponseThread.class);
        if (!startedListen) {
            startListen();
        }
        if (!startedSend) {
            startSend();
        }
        if (!startedProcess) {
            startProcess();
        }
    }

    public final void startListen() {
        listener.startThread();
        startedListen = true;
    }

    public final void startSend() {
        sender.startThread();
        startedSend = true;
    }

    public final void startProcess() {
        process.startThread();
        startedProcess = true;
    }

    public final void stopListen() {
        listener.stopThread();
        startedListen = false;
    }

    public final void stopSend() {
        sender.stopThread();
        startedSend = false;
    }

    public final void stopProcess() {
        process.stopThread();
        startedProcess = false;
    }

    public final boolean isListen() {
        return startedListen;
    }

    public final boolean isSend() {
        return startedSend;
    }

    public final boolean isProcess() {
        return startedProcess;
    }

    public static CommunicationHandler getInstance() {
        if (handler == null) {
            handler = new CommunicationHandler();
        }
        return handler;
    }
}
