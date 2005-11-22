package org.seasar.struts.examples.indexed;

public class IndexedActionImpl implements IndexedAction {
    
    private IndexedDto indexedDto;

    public String execute() {
        return SUCCESS;
    }

    public IndexedDto getIndexedDto() {
        return indexedDto;
    }

    public void setIndexedDto(IndexedDto indexedDto) {
        this.indexedDto = indexedDto;
    }
    
}
