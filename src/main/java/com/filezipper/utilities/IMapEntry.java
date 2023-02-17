package com.filezipper.utilities;

public interface IMapEntry <P, Q>{
    public void setKey(P key);
    public void setValue(P key);
    public P getKey();
    public Q getValue();
}
