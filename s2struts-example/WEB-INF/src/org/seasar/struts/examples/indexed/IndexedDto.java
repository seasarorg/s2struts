package org.seasar.struts.examples.indexed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexedDto {

    private List<ChildDto> children = new ArrayList<ChildDto>();

    private String[] array = new String[3];
    
    private Map map = new HashMap();

    private List<String> values = new ArrayList<String>();
    
    public ChildDto getChild(int index) {
        while (children.size() < index + 1) {
            children.add(new ChildDto());
        }
        return children.get(index);
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
    
    public Map getMap() {
        return map;
    }
    
    public String getValues(int index) {
        while (values.size() < index + 1) {
            values.add("");
        }
        return values.get(index);
    }
    
    public void setValues(int index, String value) {
        while (values.size() < index + 1) {
            values.add("");
        }
        values.remove(index);
        values.add(index, value);
    }
    
    public String[] getValues() {
        return values.toArray(new String[values.size()]);
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        buf.append(children).append(",");
        buf.append(array).append(",");
        buf.append(map).append(",");
        buf.append(values).append("]");
        return buf.toString();
    }

}
