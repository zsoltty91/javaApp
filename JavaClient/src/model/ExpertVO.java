/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expert;

/**
 *
 * @author zsolti
 */
public class ExpertVO {

    private static final Logger logger = Logger.getLogger(ExpertVO.class.getName());

    private Boolean alive;

    private Expert metaExpert;

    private Expert localExpert;

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Expert getMetaExpert() {
        return metaExpert;
    }

    public void setMetaExpert(Expert metaExpert) {
        this.metaExpert = metaExpert;
    }

    public Expert getLocalExpert() {
        return localExpert;
    }

    public void setLocalExpert(Expert localExpert) {
        this.localExpert = localExpert;
    }

    public void removeLocalExpert() {
        this.localExpert = null;
    }

    public void removeMetaExpert() {
        this.metaExpert = null;
    }

    public void copyMetaExpertToLocalExpert() {
        logger.log(Level.FINE, "copyMetaExpertToLocalExpert");
        if (metaExpert == null) {
            logger.log(Level.FINE, "meta is null");
            return;
        }
        if (localExpert == null) {
            logger.log(Level.FINE, "create");
            localExpert = ExpertManager.getInstance().createLocalExpert();
            logger.log(Level.FINE,"perzistence "+localExpert.getPerzistence());
        }
        localExpert.copy(metaExpert);
    }

    public void copyLocalExpertToMetaExpert() {
        if (localExpert == null) {
            return;
        }
        if (metaExpert == null) {
            metaExpert = ExpertManager.getInstance().createMetaExpert();
        }
        metaExpert.copy(localExpert);
    }

    @Override
    public String toString() {
        Expert expert = localExpert;

        if (expert == null) {
            expert = metaExpert;
        }

        return expert == null ? "null" : expert.getName();
    }
}
