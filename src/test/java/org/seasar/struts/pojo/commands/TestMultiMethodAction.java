package org.seasar.struts.pojo.commands;

public interface TestMultiMethodAction {
    
    String execute();
    
    String download();
    
    String execute(int index);
    
    String download(int index);

}
