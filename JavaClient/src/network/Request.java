/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import network.RequestType;
import network.Value;
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
        if (obj == null) {
            return false;
        }
        Request other = (Request) obj;
        if (!this.type.equals(other.getType()) || !this.getObjectName().equals(other.getObjectName())
                || this.getValues().size() != other.getValues().size()) {
            return false;
        }
        if (!this.values.keySet().containsAll(other.getValues().keySet())
                || !other.getValues().keySet().containsAll(this.values.keySet())) {
            return false;
        }
        return true;
    }

}
