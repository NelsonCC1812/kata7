package model.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Histogram<T> {

    // *=> fields
    private final Map<T, Integer> map = new HashMap<>();

    // *=> getters

    public Integer get(T key) {
        return this.map.get(key);
    }

    public Set<T> keySet() {
        return this.map.keySet();
    }

    public Collection<Integer> values() {
        return this.map.values();
    }

    public void increment(T key) {
        this.map.put(key, this.map.containsKey(key) ? this.map.get(key) + 1 : 1);
    }
}
