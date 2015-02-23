/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.NetworkSettings;

/**
 *
 * @author zsolti
 */
public class Connection {

    private static Connection conn;
    private static final Logger logger = Logger.getLogger(Connection.class.getName());

    private Socket echoSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String host;
    private Integer port;

    private Connection() {
        try {
            host = NetworkSettings.getHost();
            port = NetworkSettings.getPort();
            echoSocket = new Socket(Inet4Address.getByName(host), port);
            echoSocket.setKeepAlive(true);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        }catch (UnknownHostException ex) {
            logger.log(Level.SEVERE, "Unknown host: " + host);
        } catch (SocketException ex) {
            logger.log(Level.SEVERE, "Opening socket exception. " + ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getInstance() {
        if (conn == null) {
            conn = new Connection();
        }
        return conn;
    }

    public void freeConnection() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (echoSocket != null) {
                echoSocket.close();
            }
            conn=null;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String message) {
        out.println(message+"\0");
        out.flush();
        try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
    }

    public String readMessage() {
        try {
            String sor = in.readLine();
            Thread.sleep(200);
            return sor;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
