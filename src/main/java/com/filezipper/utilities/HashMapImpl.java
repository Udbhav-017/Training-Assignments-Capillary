package com.filezipper.utilities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class HashMapImpl<M, N> implements IMap<M,N>, Serializable {
    private final Map<M, N> hmap;

    public HashMapImpl() {
        hmap = new java.util.HashMap<>();
    }

    @Override
    public void put(M key, N value) {
        hmap.put(key, value);
    }

    @Override
    public void increment(M key, int value) {
        if(hmap.containsKey(key)) {
            hmap.put(key, (N)(Integer.valueOf((int)hmap.get(key)+value)));
        }
        else hmap.put(key, (N)(Integer.valueOf(1)));
    }

    @Override
    public boolean containsKey(M key) {
        return hmap.containsKey(key);
    }

    @Override
    public N get(M key) {
        return hmap.get(key);
    }

    @Override
    public IMapEntry<M, N>[] getEntryArray() {
        IMapEntry<M, N>[] array = new IMapEntry[hmap.size()];
        int ind = 0;
        for (Map.Entry<M, N> entry : hmap.entrySet()) {
            array[ind++] = new MapEntryImpl<M, N>(entry.getKey(), entry.getValue());
        }
        return array;
    }

    @Override
    public int size() {
        return hmap.size();
    }

    @Override
    public IMap<N, M> reverse() {
        IMap<N, M> rev = new HashMapImpl<>();

        for (IMapEntry<M, N> entry : this.getEntryArray()){
            rev.put(entry.getValue(), entry.getKey());
        }
        return rev;
    }

    public Object[] sortKeyByValues(){
        Object[] sortedKeys = new Object[this.size()];
        int ind = 0;
        for (Map.Entry<M, N> entry : hmap.entrySet()) {
            sortedKeys[ind++] = entry.getKey();
        }
        Arrays.sort(sortedKeys, new Comparator<Object>(){
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer)hmap.get(o2) - (Integer)hmap.get(o1);
            }
        });
        return sortedKeys;
    }
}
