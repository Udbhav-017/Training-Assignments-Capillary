package com.filezipper.utilities;

import java.io.Serializable;

public interface IMap < P, Q> extends Serializable {
    public void put(P key, Q value);
    public void increment(P key, int value);
    public boolean containsKey(P key);
    public Q get(P key);
    public IMapEntry<P, Q>[] getEntryArray();
    public Integer size();
    public IMap<Q, P> reverse();
}
