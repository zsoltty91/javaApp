/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zsolti
 */
public class LogProcessResponse implements ResponseWorker{

    private static final Logger logger = Logger.getLogger(LogProcessResponse.class.getName());
    
    @Override
    public void processResponse(Request request) {
        logger.log(Level.INFO,"process request: "+request.toString());
    }
    
}
