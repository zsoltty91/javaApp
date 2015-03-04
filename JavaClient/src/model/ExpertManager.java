/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zsolti
 */
public class ExpertManager {

    private Perzistence jsonPerzistence;

    private final Perzistence metaPerzistence;

    private Map<String, ExpertVO> experts;

    private static ExpertManager manager;

    private ExpertManager() {
        experts = new HashMap<>();
        metaPerzistence = MetaPersistence.getInstance();
        jsonPerzistence = JsonPersistence.getInstance();
    }

    public Expert createMetaExpert() {
        return new Expert(metaPerzistence);
    }

    public Expert createLocalExpert() {
        return new Expert(jsonPerzistence);
    }

    private Perzistence getMetaPerzistence() {
        return metaPerzistence;
    }

    private Perzistence getJsonPerzistence() {
        return jsonPerzistence;
    }

    public void saveExpert(Expert expert) {
        jsonPerzistence.save(expert);
    }

    public static ExpertManager getInstance() {
        if (manager == null) {
            manager = new ExpertManager();
            manager.getMetaPerzistence().setManager(manager);
            manager.getJsonPerzistence().setManager(manager);
        }
        return manager;
    }

    public List<ExpertVO> getExpertVOs() {
        ArrayList<ExpertVO> result = new ArrayList<>();
        result.addAll(experts.values());
        return result;
    }

    public void getAllMetaExperts() {
        metaPerzistence.getAll();
    }

    public void getAllLocalExperts() {
    }

    public Expert getLocalExpert(String name) {
        return experts.get(name) == null ? null : experts.get(name).getLocalExpert();
    }

    public Expert getMetaExpert(String name) {
        return experts.get(name) == null ? null : experts.get(name).getMetaExpert();
    }

    public void addMetaExpert(Expert e) {
        ExpertVO expertVO = experts.get(e.getName());
        if (expertVO == null) {
            expertVO = new ExpertVO();
            experts.put(e.getName(), expertVO);
        }
        // if (expertVO.getMetaExpert() == null) {
        {
            expertVO.setMetaExpert(copyMetaExpert(e));
            //expertVO.setMetaExpert(e);
        }
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
}
