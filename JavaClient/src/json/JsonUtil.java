/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expert;

/**
 *
 * @author zsolti
 */
public class JsonUtil {

    private static final Logger logger = Logger.getLogger(JsonUtil.class.getName());
    
    private JsonUtil() {
    }

    public static void writeObject(Expert object, String filename) {
        logger.log(Level.FINE,"writeObject");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename + ".json");
            Expert e = new Expert();
            e.copy(object);
            gson.toJson(e, writer);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static Expert readObject(String filename, Class clazz) {
        logger.log(Level.FINE,"readObject");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = null;
        try {
            reader = new FileReader(filename + ".json");
            return (Expert) gson.fromJson(reader, clazz);
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.INFO,"Object is not exist.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
