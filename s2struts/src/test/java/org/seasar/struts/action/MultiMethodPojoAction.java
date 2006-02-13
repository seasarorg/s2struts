package org.seasar.struts.action;

public interface MultiMethodPojoAction {
    
    String exe();
    
    String download();
    
    String exe(int index);
    
    String download(int index);

}
