/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    private ParameterTypeMapper() {
    }
    
    static {
         prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(new File("src\\main\\resources\\"+parametersProperties));
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
    }
    
    public static Type getType(String parameter) {
        String type = prop.getProperty(parameter,"");
        
        switch(type) {
            case "int": return network.Type.INTEGER;
            case "bool": return network.Type.BOOLEAN;
            case "double": return network.Type.DOUBLE;
            case "string": return network.Type.STRING;
        }
        return null;
    }
}
