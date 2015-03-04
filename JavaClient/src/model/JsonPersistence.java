/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import json.JsonUtil;

/**
 *
 * @author zsolti
 */
public class JsonPersistence implements Perzistence {

    ExpertManager manager;

    public static JsonPersistence per;

    private JsonPersistence() {
    }

    public static JsonPersistence getInstance() {
        if (per == null) {
            per = new JsonPersistence();
        }
        return per;
    }

    public void setManager(ExpertManager manager) {
        this.manager = manager;
    }

    @Override
    public void get(String name) {
        Expert expert = (Expert) JsonUtil.readObject(name, Expert.class);
        if (expert != null) {
            expert.setPerzistence(this);
            manager.addLocalExpert(expert);
        }
    }

    @Override
    public void getAll() {
    }

    @Override
    public void save(Expert expert) {
        JsonUtil.writeObject(expert, expert.getName());
    }

    private void doSet(String name,String variable, String value) {
        Expert expert = manager.getLocalExpert(name);

        if (expert == null) {
            throw new RuntimeException("Error while setting " + variable + " = " + value + "in expert " + name + ":\n Expert doesn't exists.");
        }

        save(expert);
    }
    
    private void doGet(String name,String variable) {
        Expert expert = (Expert) JsonUtil.readObject(name, Expert.class);

        if (expert == null) {
            throw new RuntimeException("Error while getting " + variable + "in expert " + name + ":\n Expert doesn't exists.");
        }

        manager.addLocalExpert(expert);
    }

    @Override
    public void set(String name, String variable, String value) {
        doSet(name,variable,value);
    }

    @Override
    public void set(String name, String variable, Integer value) {
        doSet(name,variable,value.toString());
    }

    @Override
    public void set(String name, String variable, Double value) {
        doSet(name,variable,value.toString());
    }

    @Override
    public void set(String name, String variable, Boolean value) {
        doSet(name,variable,value.toString());
    }

    @Override
    public void getString(String name, String variable) {
        doGet(name, variable);
    }

    @Override
    public void getInt(String name, String variable) {
        doGet(name, variable);
    }

    @Override
    public void getDouble(String name, String variable) {
        doGet(name, variable);
    }

    @Override
    public void getBoolean(String name, String variable) {
        doGet(name, variable);
    }
}
