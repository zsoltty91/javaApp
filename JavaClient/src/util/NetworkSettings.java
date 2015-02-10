/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class NetworkSettings {

    private static final String host = "localhost";
    private static final Integer port = 7000;

    private static final Logger logger = Logger.getLogger(NetworkSettings.class.getName());

    static {
        File f = new File("src\\main\\resources\\connection.properties");
        if (!f.exists()) {
            System.out.println("alma");
            logger.log(Level.CONFIG, "connection.properties created.");
            init();
        }
    }

    private static void init() {
        Properties prop = new Properties();
        prop.setProperty("host", host);
        prop.setProperty("port", port.toString());

        OutputStream writer = null;
        try {
            writer = new FileOutputStream(new File("src\\main\\resources\\connection.properties"));

            prop.store(writer, "");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Can not create properties.");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close properties.");
            }
        }
    }

    public static void set(String host, Integer port) {
        Properties prop = new Properties();
        InputStream input = null;
        FileWriter writer = null;
        try {
            input = new FileInputStream(new File("src\\main\\resources\\connection.properties"));
            
            //input = this.getClass().getResourceAsStream("connection.properties");
            prop.load(input);
            prop.replace("host", host.toString());
            prop.replace("port", port.toString());
            input.close();
            writer = new FileWriter(new File("src\\main\\resources\\connection.properties"));
            prop.store(writer, "");
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Connection properties not found.");
            init();
        } catch (IOException ex) {
            Logger.getLogger(NetworkSettings.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close properties.");
            }
        }
    }

    public static String getHost() {
        Properties prop = new Properties();
        InputStream input = null;
        String result = host;
        try {
            input = new FileInputStream(new File("src\\main\\resources\\connection.properties"));
            //input = this.getClass().getResourceAsStream("connection.properties");
            prop.load(input);
            result = prop.getProperty("host");
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Connection properties not found.");
            init();
            result = host;
        } catch (IOException ex) {
            Logger.getLogger(NetworkSettings.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close properties.");
            }
        };
        return result;
    }

    public static int getPort() {
        Properties prop = new Properties();
        InputStream input = null;
        Integer result = port;
        try {
            input = new FileInputStream(new File("src\\main\\resources\\connection.properties"));
            //input = this.getClass().getResourceAsStream("connection.properties");
            prop.load(input);
            result = Integer.parseInt(prop.getProperty("port"));
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Connection properties not found.");
            init();
            result = port;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close properties.");
            }
        };
        return result;
    }
}
