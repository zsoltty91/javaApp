/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.Type;

/**
 *
 * @author zsolti
 */
public class Value {

    Type type;
    Object value;

    public Value(Type type) {
        this.type = type;
        this.value = null;
    }
    public Value(Type type, Object value) {
        this.type = type;
        this.value = null;
    }
    

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
