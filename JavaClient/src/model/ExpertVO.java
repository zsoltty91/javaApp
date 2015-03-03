/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.Expert;

/**
 *
 * @author zsolti
 */
public class ExpertVO {
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
    
    void removeLocalExpert() {
        this.localExpert=null;
    }

    void removeMetaExpert() {
        this.metaExpert=null;
    }

    @Override
    public String toString() {
        Expert  expert= localExpert;
        
        if(expert==null) {
        expert = metaExpert;
        }
        
        return expert==null?"null":expert.getName();
    }
}
