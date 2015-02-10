/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

/**
 *
 * @author zsolti
 */
public interface Perzistence {
    
    public abstract void get(String name);
    
    public abstract void getAll();
    
    public abstract void save(Expert expert);

    public abstract void set(String name, String variable, String value);

    public abstract void set(String name, String variable, Integer value);

    public abstract void set(String name, String variable, Double value);

    public abstract void set(String name, String variable, Boolean value);

    public abstract void getString(String name, String variable);

    public abstract void getInt(String name, String variable);

    public abstract void getDouble(String name, String variable);

    public abstract void getBoolean(String name, String variable);
}
