package com.huffmanzipper;

public interface IMap < P, Q>{
    public void put(P key, Q value);
    public void increment(P key);
    public void containsKey(P key);
    public Q get(P key);
    public IMapEntry<P, Q>[] getEntryArray();
    public Integer size();
}
