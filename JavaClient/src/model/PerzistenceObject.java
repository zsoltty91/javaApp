/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author zsolti
 */
public class PerzistenceObject {

    private Perzistence per;

    private String name;

    protected PerzistenceObject(Perzistence per) {
        this.per = per;
    }
    
    protected PerzistenceObject() {
    }

    protected void set(String variable, String value) {
        if (per != null) {
            per.set(name, variable, value);
        }
    }

    protected void set(String variable, Integer value) {
        if (per != null) {
            per.set(name, variable, value);
        }
    }

    protected void set(String variable, Double value) {
        if (per != null) {
            per.set(name, variable, value);
        }
    }

    protected void set(String variable, Boolean value) {
        if (per != null) {
            per.set(name, variable, value);
        }
    }

    protected void getString(String variable) {
        if (per != null) {
            per.getString(name, variable);
        }
    }

    protected void getInt(String variable) {
        if (per != null) {
            per.getInt(name, variable);
        }
    }

    protected void getDouble(String variable) {
        if (per != null) {
            per.getDouble(name, variable);
        }
    }

    protected void getBoolean(String variable) {
        if (per != null) {
            per.getBoolean(name, variable);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setPerzistence(Perzistence per) {
        this.per = per;
    }
    
    public Perzistence getPerzistence() {
        return per;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerzistenceObject other = (PerzistenceObject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
