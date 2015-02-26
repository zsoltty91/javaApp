/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.Map;

/**
 *
 * @author zsolti
 */
public class Request {

    private String objectName;

    private Map<String, Value> values;

    private RequestType type;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public Map<String, Value> getValues() {
        return values;
    }

    public void setValues(Map<String, Value> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Request other = (Request) obj;
        if (objectName == null) {
            if (other.objectName != null) {
                return false;
            }
        } else if (!objectName.equals(other.objectName)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        /*if (values == null) {
            if (other.values != null) {
                return false;
            }
        } else if (!values.equals(other.values)) {
            return false;
        }*/
        return true;
    }

    @Override
    public String toString() {
        return "Request{"+ "type=" + type==null?null:type.toString() + ",objectName=" + objectName + ", values=" + values + '}';
    }
}
