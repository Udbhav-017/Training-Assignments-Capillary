package com.filezipper.utilities;

public class MapEntryImpl<P, Q> implements IMapEntry{

    private P first;
    private Q second;

    MapEntryImpl(Object first,Object second){
        this.first = (P) first;
        this.second= (Q) second;
    }
    @Override
    public void setKey(Object first) {
        this.first = (P) first;
    }

    @Override
    public void setValue(Object second) {
        this.second = (Q) second;
    }

    @Override
    public Object getKey() {
        return first;
    }

    @Override
    public Object getValue() {
        return second;
    }
}
