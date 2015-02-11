/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Perzistence;
import model.MetaPersistence;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author zsolti
 */
public class ExpertManager {

    private Perzistence jsonPerzistence;

    private Perzistence metaPerzistence;

    private Map<String, ExpertVO> experts;

    private static ExpertManager manager;

    private ExpertManager() {
        metaPerzistence=new MetaPersistence();
    }

    public static ExpertManager getInstance() {
        if (manager == null) {
            manager = new ExpertManager();
        }
        return manager;
    }
    
    public void getAllMetaExperts() {
        metaPerzistence.getAll();
    }

    public void getAllLocalExperts() {
    }
    
    public Expert getLocalExpert(String name) {
        return experts.get(name).getLocalExpert();
    }

    public Expert getMetaExpert(String name) {
        return experts.get(name).getMetaExpert();
    }

    public void addMetaExpert(Expert e) {
        ExpertVO expertVO = experts.get(e.getName());
        if (expertVO == null) {
            expertVO = new ExpertVO();
            experts.put(e.getName(), expertVO);
        }
        expertVO.setMetaExpert(copyMetaExpert(e));
    }

    public void addLocalExpert(Expert e) {
        ExpertVO expertVO = experts.get(e.getName());
        if (expertVO == null) {
            expertVO = new ExpertVO();
            experts.put(e.getName(), expertVO);
        }
        expertVO.setLocalExpert(copyLocalExpert(e));
    }

    public void removeLocalExpert(Expert e) {
        ExpertVO expertVO = experts.get(e.getName());
        if (expertVO != null) {
            expertVO.removeLocalExpert();
        }
    }

    public void removeMetaExpert(Expert e) {
        ExpertVO expertVO = experts.get(e.getName());
        if (expertVO != null) {
            expertVO.removeMetaExpert();
        }
    }

    private Expert copyMetaExpert(Expert expert) {
        Expert other = (Expert) expert.clone();
        other.setPerzistence(null);
        return expert;
    }

    private Expert copyLocalExpert(Expert expert) {
        Expert other = (Expert) expert.clone();
        other.setPerzistence(null);
        return expert;
    }

    private ArrayList<Expert> getMetaExperts() {
        ArrayList<Expert> result = new ArrayList<>();
        for (ExpertVO expertVO : experts.values()) {
            result.add(expertVO.getMetaExpert());
        }
        return result;
    }

    private ArrayList<Expert> getLocalExperts() {
        ArrayList<Expert> result = new ArrayList<>();
        for (ExpertVO expertVO : experts.values()) {
            result.add(expertVO.getLocalExpert());
        }
        return result;
    }

    private ArrayList<ExpertVO> getAliveExpertVOs() {
        ArrayList<ExpertVO> result = new ArrayList<>();
        for (ExpertVO expertVO : experts.values()) {
            if (expertVO.isAlive()) {
                result.add(expertVO);
            }
        }
        return result;
    }

    public void refreshField(String expert, String variable, Object value) {
    
    
    }
}
