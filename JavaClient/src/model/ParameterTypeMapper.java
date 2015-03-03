/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.Type;

/**
 *
 * @author zsolti
 */
public class ParameterTypeMapper {

    private static final Logger logger = Logger.getLogger(ParameterTypeMapper.class.getName());

    private static final String parametersProperties = "parameters.properties";

    private static final Properties prop;

    private static HashMap<String, Type> parameters;

    private ParameterTypeMapper() {
    }

    static {
        prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(new File("src\\main\\resources\\" + parametersProperties));
            //input = this.getClass().getResourceAsStream("connection.properties");
            prop.load(input);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Parameters properties not found.");
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close parameters.properties.");
            }
        };
        initParameters();
    }

    public static final void initParameters() {
        parameters = new HashMap<>();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(new File("src\\main\\resources\\" + parametersProperties)));
            //input = this.getClass().getResourceAsStream("connection.properties");
            String line,variable;
            while (true) {
                line = input.readLine();
                if(line==null) {
                    break;
                }
                variable=line.substring(0,line.indexOf("="));
                parameters.put(variable, getType(variable));
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Parameters properties not found.");
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Can not close parameters.properties.");
            }
        };
    }

    public static Type getType(String parameter) {
        String type = prop.getProperty(parameter, "");

        switch (type) {
            case "int":
                return network.Type.INTEGER;
            case "bool":
                return network.Type.BOOLEAN;
            case "double":
                return network.Type.DOUBLE;
            case "string":
                return network.Type.STRING;
        }
        return null;
    }

    public static HashMap<String, Type> getParameters() {
        return parameters;
    }
}
